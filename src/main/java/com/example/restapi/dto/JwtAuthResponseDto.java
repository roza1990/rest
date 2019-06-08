package com.example.restapi.dto;

import com.example.restapi.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponseDto {

  private String token;
  private User user;

}
