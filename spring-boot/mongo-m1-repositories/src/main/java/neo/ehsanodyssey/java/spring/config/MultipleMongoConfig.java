package neo.ehsanodyssey.java.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MultipleMongoProperties.class)
public class MultipleMongoConfig {
    private final MultipleMongoProperties mongoProperties;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
//        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
        return new PrimaryMongoConfig().mongoTemplate();
    }

    @Bean(name = "secondaryMongoTemplate")
    public ReactiveMongoOperations secondaryMongoTemplate() throws Exception {
//        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
        return new ReactiveMongoConfig().reactiveMongoTemplate();
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(final MongoProperties mongo) throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
//                mongo.getDatabase());
        return new PrimaryMongoConfig().mongoDbFactory();
    }

    @Bean
    public ReactiveMongoDatabaseFactory secondaryFactory(final MongoProperties mongo) throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
//                mongo.getDatabase());
        return new ReactiveMongoConfig().reactiveMongoDbFactory();
    }
}
