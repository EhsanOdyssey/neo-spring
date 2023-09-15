package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.dto.ActionModel;
import neo.ehsanodyssey.java.spring.service.ActionService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/action/{id}")
    public ResponseEntity findUser(@PathVariable String id) {
        return ResponseEntity.ok(actionService.findActionById(id));
    }

    @PostMapping("/action")
    public ResponseEntity createUser(@RequestBody ActionModel model) {
        return ResponseEntity.ok(actionService.insert(model));
    }
}
