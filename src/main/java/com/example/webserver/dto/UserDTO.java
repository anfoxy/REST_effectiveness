package com.example.webserver.dto;

import com.example.webserver.model.User;
import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String matchingPassword;

    private String code;

    private String userLN;

    private String userFN;


    public UserDTO(Long id, String userName,String password,  String matchingPassword, String code, String userLN,String email) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.code = code;
        this.userLN = userLN;
        this.userFN = email;
    }

    public String getCode() {
        return code;
    }

    public UserDTO(User c) {
        this.id = c.getId();
        this.username = c.getUsername();
        this.password = c.getPassword();
        this.matchingPassword = c.getMatchingPassword();
        this.code = c.getCode();
        this.userLN = c.getUserLN();
        this.userFN = c.getUserFN();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User convertToEntity() {
        User c = new User();
        c.setId(id);
        c.setUsername(username);
        c.setPassword(password);
        c.setMatchingPassword(matchingPassword);
        c.setCode(code);
        c.setUserLN(userLN);
        c.setUserFN(userFN);
        return c;
    }
}
