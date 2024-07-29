package com.ueniweb.swiftwaresolutions.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

   private Long id;

   private String name;

   private String accessToken;

   private int isDoctor;

   private int isNurse;

}
