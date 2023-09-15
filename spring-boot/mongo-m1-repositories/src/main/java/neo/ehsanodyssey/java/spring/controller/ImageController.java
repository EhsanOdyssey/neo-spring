package neo.ehsanodyssey.java.spring.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import neo.ehsanodyssey.java.spring.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/img")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/gridfs")
    public ResponseEntity createFile() {
        return ResponseEntity.ok(imageService.storeFileWithMetadata());
    }

    @GetMapping("/gridfs/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        final HttpHeaders headers = new HttpHeaders();
        byte[] media = new byte[0];
        try {
            GridFsResource resource = imageService.getFile(id);
            media = IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }

    @GetMapping("/gridfs/img/{id}")
    public void getImageAsByteArray(@PathVariable String id, HttpServletResponse response) throws IOException {
        GridFsResource resource = imageService.getFile(id);
        final InputStream in = resource.getInputStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/get")
    @ResponseBody
    public byte[] getImageAsByteArray() throws IOException {
        final InputStream in = new ClassPathResource("Rachael.jpg").getInputStream();
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/gridfs/files")
    public ResponseEntity createFiles() {
        return ResponseEntity.ok(imageService.storeFiles());
    }

    @GetMapping("/gridfs/files/{user}")
    public ResponseEntity getFileByUser(@PathVariable String user) {
        return ResponseEntity.ok(
                imageService.getFileByUser(user).parallelStream()
                        .map(GridFSFile::getFilename)
                        .collect(Collectors.toList())
        );
    }
}
