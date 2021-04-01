package zone.god.blogpjfirebase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zone.god.blogpjfirebase.model.User;
import zone.god.blogpjfirebase.repository.UserRepository;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("*")
@RequestMapping("users/")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("add")
    public ResponseEntity<?> addNewUser() throws ExecutionException, InterruptedException {
        User user = User.builder()
                .username("zonesama")
                .password("380617")
                .build();
        if (userRepository.saveUser(user))  return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
