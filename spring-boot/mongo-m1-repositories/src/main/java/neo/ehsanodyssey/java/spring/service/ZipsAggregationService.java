package neo.ehsanodyssey.java.spring.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import neo.ehsanodyssey.java.spring.model.StatePopulation;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
public class ZipsAggregationService {

    private MongoTemplate mongoTemplate;

    public ZipsAggregationService(MongoTemplate mongoTemplate) throws Exception {
        this.mongoTemplate = mongoTemplate;
        MongoDatabase testDB = this.mongoTemplate.getDb();
        MongoCollection<Document> zipsCollection = testDB.getCollection("zips");
        if (!mongoTemplate.collectionExists(zipsCollection.getNamespace().getCollectionName())) {
            InputStream zipsJsonStream = ZipsAggregationService.class.getResourceAsStream("/zips.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipsJsonStream));
            reader.lines()
                    .forEach(line -> zipsCollection.insertOne(Document.parse(line)));
            reader.close();
        }
    }

    public List<StatePopulation> getStatesHavePopGreaterThanAndSorted(int limit) {
        GroupOperation groupByStateAndSumPop = group("state").sum("pop").as("statePop");
        MatchOperation filterStates = match(new Criteria("statePop").gt(limit));
        SortOperation sortByPopDesc = sort(new Sort(Sort.Direction.DESC, "statePop"));

        Aggregation aggregation = newAggregation(groupByStateAndSumPop, filterStates, sortByPopDesc);
        AggregationResults<StatePopulation> result = mongoTemplate.aggregate(aggregation, "zips", StatePopulation.class);

//        List<StatePopulation> actualList = StreamSupport.stream(result.spliterator(), false)
//                .collect(Collectors.toList());
//
//        List<StatePopulation> expectedList = new ArrayList<>(actualList);
//        Collections.sort(expectedList, (sp1, sp2) -> sp2.getStatePop() - sp1.getStatePop());

        return result.getMappedResults();
    }

    public List<StatePopulation> getStateWithLowestAvgCityPopIsND(int limit) {
        GroupOperation sumTotalCityPop = group("state", "city").sum("pop").as("cityPop");
        GroupOperation averageStatePop = group("_id.state").avg("cityPop").as("avgCityPop");
        SortOperation sortByAvgPopAsc = sort(new Sort(Sort.Direction.ASC, "avgCityPop"));
        ProjectionOperation projectToMatchModel = project().andExpression("_id").as("state")
                .andExpression("avgCityPop").as("statePop");
        LimitOperation limitToOnlyFirstDoc = limit(limit);

        Aggregation aggregation = newAggregation(sumTotalCityPop, averageStatePop, sortByAvgPopAsc, limitToOnlyFirstDoc, projectToMatchModel);

        AggregationResults<StatePopulation> result = mongoTemplate.aggregate(aggregation, "zips", StatePopulation.class);
//        StatePopulation smallestState = result.getUniqueMappedResult();

        return result.getMappedResults();
    }

    public Document getMaxTXAndMinDC() {
        GroupOperation sumZips = group("state").count().as("zipCount");
        SortOperation sortByCount = sort(Sort.Direction.ASC, "zipCount");
        GroupOperation groupFirstAndLast = group().first("_id").as("minZipState")
                .first("zipCount").as("minZipCount").last("_id").as("maxZipState")
                .last("zipCount").as("maxZipCount");

        Aggregation aggregation = newAggregation(sumZips, sortByCount, groupFirstAndLast);

        AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, "zips", Document.class);
        return result.getUniqueMappedResult();
    }

}
