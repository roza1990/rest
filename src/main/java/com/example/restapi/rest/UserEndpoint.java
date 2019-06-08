package com.example.restapi.rest;

import com.example.restapi.dto.JwtAuthRequestDto;
import com.example.restapi.dto.JwtAuthResponseDto;
import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import com.example.restapi.security.CurrentUser;
import com.example.restapi.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserEndpoint {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @CrossOrigin
  @PostMapping("/api/token/signIn")
  public ResponseEntity auth(@RequestBody JwtAuthRequestDto authRequestDto) {
    //data transfer object,mez petqakan dashtern enq grum dto-i mej
    String email = authRequestDto.getEmail();
    Optional<User> byEmail = userRepository.findByEmail(email);
    if (byEmail.isPresent()) {
      User user = byEmail.get();
      if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
        String token = jwtTokenUtil.generateToken(user.getEmail());
        JwtAuthResponseDto response = JwtAuthResponseDto.builder()
                .token(token)
                .user(user)
                .build();
        return ResponseEntity.ok(response);
      }
    }
    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .build();
  }

  @PostMapping("/api/token/signUp")
  @ApiOperation(value = "Create User", response = User.class)
  @ApiResponses({
    @ApiResponse(code = 409, message = "User with email already exists"),
    @ApiResponse(code = 200, message = "User created")
  })
  public ResponseEntity add(@RequestBody User user) {
    Optional<User> userRepositoryByEmail=userRepository.findByEmail(user.getEmail());

    if (userRepositoryByEmail.isPresent()) {
      return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .build();
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return ResponseEntity.ok().build();
  }


  @GetMapping("/getUserDetails/{id}")
  public ResponseEntity getById(@PathVariable("id") int id) {
    Optional<User> byId = userRepository.findById(id);
    if (byId.isPresent()) {
      return ResponseEntity.ok(byId.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/user/update")
  public ResponseEntity update(@RequestBody User user) {
    if (userRepository.findById(user.getId()).isPresent()) {
      userRepository.save(user);
      return ResponseEntity
              .ok(user);
    }
    return ResponseEntity.notFound().build();
  }

//  @GetMapping("/getAll")
//  public ResponseEntity getAll() {
//    return ResponseEntity.ok(userRepository.findAll());
//  }

//  @DeleteMapping("/user/{id}")
//  public ResponseEntity deleteById(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
//    Optional<User> byId = userRepository.findById(id);
//    if (byId.isPresent()) {
//      userRepository.deleteById(id);
//      return ResponseEntity
//        .ok()
//        .build();
//    }
//    return ResponseEntity.notFound().build();
//  }

}
