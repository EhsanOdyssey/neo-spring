package neo.ehsanodyssey.java.spring.controller;

import neo.ehsanodyssey.java.spring.dto.PageRequestImpl;
import neo.ehsanodyssey.java.spring.dto.UserModel;
import neo.ehsanodyssey.java.spring.model.User;
import neo.ehsanodyssey.java.spring.service.UserTransactionReactiveService;
import neo.ehsanodyssey.java.spring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final UserTransactionReactiveService userTransactionReactiveService;

    public UserController(UserService userService, UserTransactionReactiveService userTransactionReactiveService) {
        this.userService = userService;
        this.userTransactionReactiveService = userTransactionReactiveService;
    }

    @GetMapping("/user/{name}")
    public ResponseEntity findUser(@PathVariable String name) {
        return ResponseEntity.ok(userService.findUsersByName(name));
    }

    @GetMapping("/user/excludeId/{name}")
    public ResponseEntity findUserExcludeId(@PathVariable String name) {
        return ResponseEntity.ok(userService.findNameAndAgeExcludeId(name));
    }

    @GetMapping("/user/query/{name}")
    public ResponseEntity findUserByQuery(@PathVariable String name) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }

    @GetMapping("/users")
    public ResponseEntity findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/users/pagination")
    public ResponseEntity findAllUsersPagination(PageRequestImpl pageRequest) {
        return ResponseEntity.ok(userService.findAllUsersPagination(pageRequest.getInstance()));
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(userService.insert(userModel));
    }

    @PostMapping("/user/reactive")
    public Flux<User> createUser(@RequestBody List<UserModel> userModels) {
        return userTransactionReactiveService.insertReactive(userModels);
    }
}
