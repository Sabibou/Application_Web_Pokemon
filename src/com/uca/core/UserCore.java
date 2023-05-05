package com.uca.core;

import com.uca.dao.UserDAO;
import com.uca.entity.UserEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserCore {

    public static ArrayList<UserEntity> getAllUsersExceptMainUser(int userId) {
        return new UserDAO().getAllUsersExceptMainUser(userId);
    }

    public static UserEntity createNewUser(String pseudo, String pwd, Timestamp t){

        UserEntity user = new UserEntity(pseudo, pwd, t);
        return new UserDAO().create(user);
    }

    public static UserEntity getUserById(int id){

        return new UserDAO().getUserById(id);
    }

    public static UserEntity getUserByPseudo(String pseudo){

        return new UserDAO().getUserByPseudo(pseudo);
    }

    public static boolean verifyPassword(String pwd1, String pw2){

        return new UserDAO().verifyPassword(pwd1, pw2);
    }

    public static boolean canLvlUp(int user_id){

        return new UserDAO().canLvlUp(user_id);
    }

    public static int lvlUp(int user_id){

        return new UserDAO().lvlUp(user_id);
    }

    public static boolean isOtherDay(Timestamp newT, int id){

        return new UserDAO().isOtherDay(newT, id );
    }

    public static void setNewConnection(Timestamp newT, int user_id){

        new UserDAO().setNewConnection(newT, user_id);
    }

    public static void resetNbXp(int user_id){

        new UserDAO().resetNbXp(user_id);
    }

    public static boolean doesUserExist(int userId){

        return new UserDAO().getUserById(userId) != null;
    }

    public static boolean doesPseudoExist(String pseudo){

        return new UserDAO().getUserByPseudo(pseudo) != null;
    }





}
