package com.example.webserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;

@Entity
@Data
@Table(name = "usr", schema = "public")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "user_ln",nullable = false)
    private String userLN;
    @Column
    private String password;
    @Column
    private String  code;

    @Column(name = "user_fn",nullable = false)
    private String userFN;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    transient private String matchingPassword;

    public String printRole(){
        Iterator<Role> iterator = roles.iterator();
        String text = "";
        while (iterator.hasNext()) {
            text = iterator.next().toString();
        }
        return text;
    }

    public User(Long id, String userName, String password, String code, String matchingPassword,String userLN,String email) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.code = code;
        this.matchingPassword = matchingPassword;
        this.userLN = userLN;
        this.userFN = email;
    }

    public User(Long id, String userName, String password, String code,String userLN,String email) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.code = code;
        this.userLN = userLN;
        this.userFN = email;
    }

    public User(String userName, String password, String code,String userLN,String email) {
        this.username = userName;
        this.password = password;
        this.code = code;
        this.userLN = userLN;
        this.userFN = email;
    }

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", userLN='" + userLN + '\'' +
                ", email='" + userFN + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }
}
