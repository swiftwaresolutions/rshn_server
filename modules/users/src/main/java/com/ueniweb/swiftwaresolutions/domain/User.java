package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Entity(name = "Users")
@Table(name = "admin_users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pass")
    private String pass;

    @Column(name = "is_block")
    private int isBlock;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "sex")
    private String sex;

    @Column(name = "is_married")
    private int isMarried;

    @Column(name = "dom")
    private Date dom;

    @Column(name = "is_msg_displayed")
    private int isMsgDisplayed;

    @Column(name = "is_doctor")
    private int isDoctor;

    @Column(name = "doctor_id")
    private Long doctor_id;

    @Column(name = "is_admin")
    private int is_admin;

    @Column(name = "is_nurse")
    private int isNurse;

    public UserDTO to() {
        UserDTO userDTO = new UserDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(this, userDTO);
        userDTO.setIsDoctor(isDoctor);
        return userDTO;
    }
}
