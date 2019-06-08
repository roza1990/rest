package com.example.restapi.rest;

import com.example.restapi.dto.JwtAuthRequestDto;
import com.example.restapi.dto.JwtAuthResponseDto;
import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;

import com.example.restapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class AuthEndpoint {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

 // @PostMapping("/api/token/signIn")
//  public ResponseEntity auth(@RequestBody JwtAuthRequestDto authRequestDto) {
//    //data transfer object,mez petqakan dashtern enq grum dto-i mej
//    String email = authRequestDto.getEmail();
//    Optional<User> byEmail = userRepository.findByEmail(email);
//    if (byEmail.isPresent()) {
//      User user = byEmail.get();
//      if (passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
//        String token = jwtTokenUtil.generateToken(user.getEmail());
//        JwtAuthResponseDto response = JwtAuthResponseDto.builder()
//          .token(token)
//          .user(user)
//          .build();
//        return ResponseEntity.ok(response);
//      }
//    }
//    return ResponseEntity
//      .status(HttpStatus.UNAUTHORIZED)
//      .build();
//  }


}
