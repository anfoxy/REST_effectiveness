package com.example.webserver.repository;


import com.example.webserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT id From public.usr " +
            "WHERE username IN(:username) " +
            "AND   password IN(:password);",nativeQuery = true)
    List<String> searchUserByLoginAndPassword(@Param("username") String username,
                                              @Param("password") String password);



    @Query(value = "SELECT id From public.usr " +
            "WHERE  (username IN(:username)" +
            "AND   user_fn IN(:userFN) " +
            "AND   code IN(:code)"+
            "AND   user_ln IN(:userLN))",nativeQuery = true)
    List<String> findUserForRegister(@Param("username") String username,
                                     @Param("userFN") String userFN,
                                     @Param("code") String code,
                                     @Param("userLN") String userLN);


    User findUserByUsername(String username);

    ArrayList<User> findAll();

    @Modifying
    @Query(value = "DELETE FROM public.user_role WHERE id = :id",nativeQuery = true)
    void deleteUserRoleByIdUser(@Param("id") Long id);
}
