package neo.ehsanodyssey.java.spring.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;

    public ImageService(GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
    }

    @PostConstruct
    public void init() {
        List<GridFSFile> fileList = new ArrayList<>();
        gridFsTemplate.find(new Query()).into(fileList);
        for (GridFSFile file : fileList) {
            gridFsTemplate.delete(new Query(Criteria.where("filename").is(file.getFilename())));
        }
    }

    public Map storeFileWithMetadata() {
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "neo");
        InputStream inputStream = null;
        String id = "";
        try {
            Resource resource = new ClassPathResource("Rachael.jpg");
            inputStream = resource.getInputStream();
            id = gridFsTemplate.store(inputStream, "rachael.jpg", "image/jpg", metaData).toString();
        } catch (IOException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    System.out.println("Failed to close");
                    ex.printStackTrace();
                }
            }
        }
        metaData.put("fileId", id);
        return metaData.toMap();
    }

    public GridFsResource getFile(String id) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        return gridFsOperations.getResource(gridFSFile);
    }

    public List<Map> storeFiles() {
        List<Map> metaDataList = new ArrayList<>();
        DBObject metaDataUser1 = new BasicDBObject();
        metaDataUser1.put("user", "neo");
        DBObject metaDataUser2 = new BasicDBObject();
        metaDataUser2.put("user", "odyssey");
        InputStream inputStream = null;
        String id = "";
        try {
            Resource resource = new ClassPathResource("Rachael.jpg");
            inputStream = resource.getInputStream();
            id = gridFsTemplate.store(inputStream, "rachael.jpg", "image/jpg", metaDataUser1).toString();
            metaDataUser1.put("fileId", id);
            metaDataList.add(metaDataUser1.toMap());
            resource = new ClassPathResource("Rachael_2.jpg");
            inputStream = resource.getInputStream();
            id = gridFsTemplate.store(inputStream, "rachael_2.jpg", "image/jpg", metaDataUser2).toString();
            metaDataUser2.put("fileId", id);
            metaDataList.add(metaDataUser2.toMap());
        } catch (IOException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    System.out.println("Failed to close");
                    ex.printStackTrace();
                }
            }
        }
        return metaDataList;
    }

    public List<GridFSFile> getFileByUser(String user) {
        List<GridFSFile> gridFSFiles = new ArrayList<>();
        return gridFsTemplate.find(new Query(Criteria.where("metadata.user").is(user))).into(gridFSFiles);
    }

    public List<GridFsResource> getFiles() {
        List<GridFSFile> gridFSFiles = new ArrayList<>();
        gridFsTemplate.find(new Query()).into(gridFSFiles);
        return gridFSFiles.parallelStream().map(gridFsOperations::getResource).collect(Collectors.toList());
    }
}
