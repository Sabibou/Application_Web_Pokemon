package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserCore {

    public static ArrayList<UserEntity> getAllUsers() {
        return new UserDAO().getAllUsers();
    }

    public static void createNewUser(String pseudo, String pwd, Timestamp t){

        UserEntity user = new UserEntity(pseudo, pwd, t);
        new UserDAO().create(user);
    }

}
