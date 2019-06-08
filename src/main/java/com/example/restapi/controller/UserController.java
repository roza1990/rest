package com.example.restapi.controller;

import com.example.restapi.dto.JwtAuthRequestDto;
import com.example.restapi.model.User;
import com.example.restapi.security.CurrentUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Value("${hostname}")
    private String hostName;


//    @GetMapping("/loginSuccess")
//    public String loginSuccess(@AuthenticationPrincipal
//                                       CurrentUser springUser, HttpServletRequest request) {
//        String url = hostName + "/api/token/signIn";
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<JwtAuthRequestDto> req = new HttpEntity<>(new JwtAuthRequestDto().builder()
//                .email(springUser.getUser().getEmail()).build());
//
//        ResponseEntity<User> response = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                req,
//                User.class);
//
//        User user = response.getBody();
//        if (user == null || !springUser.getUser().getPassword().equals(user.getPassword())) {
//            request.getSession().setAttribute("user", user);
//            return "redirect:/login";
//        }
//        request.getSession().setAttribute("user", user);
//        return "redirect:/";
//
//    }
}
