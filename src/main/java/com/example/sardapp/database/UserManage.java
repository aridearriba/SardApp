package com.example.sardapp.database;

import com.example.sardapp.api.dao.UserDAO;
import com.example.sardapp.api.dao.UserDAOImpl;
import com.example.sardapp.entities.User;
import com.ja.security.PasswordHash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class UserManage
{
    private UserManage(){
        throw new IllegalStateException("Utility class");
    }

    public static void signUp(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        String hashedPassword = new PasswordHash().createHash(password);
        // create user
        User user = new User();
        user.setEmail(user.getEmail());
        user.setPassword(hashedPassword);
        // save user
        UserDAO users = new UserDAOImpl();
        users.save(user);
    }

    public static boolean login(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        User user = new UserDAOImpl().findByEmail(email);
        try {
            return new PasswordHash().validatePassword(password, user.getPassword());
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public static List<User> findByFilters(List<String> events)
    {
        List<User> users = new UserDAOImpl().findAll();
        return getUsersByEvents(users, events);
    }

    private static List<User> getUsersByEvents(List<User> users, List<String> events)
    {
        List<User> filteredList = new ArrayList<>();

        boolean add = false;

        for (User user : users)
        {
            if (events.contains("aplecs"))
            {
                if (user.getAplecs()) add = true;
                else add = false;
            }
            if (events.contains("ballades"))
            {
                if (user.getBallades()) add = true;
                else add = false;
            }
            if (events.contains("concerts"))
            {
                if (user.getConcerts()) add = true;
                else add = false;
            }
            if (events.contains("concursos"))
            {
                if (user.getConcursos()) add = true;
                else add = false;
            }
            if (events.contains("cursets"))
            {
                if (user.getCursets()) add = true;
                else add = false;
            }
            if (events.contains("altres"))
            {
                if (user.getAltres()) add = true;
                else add = false;
            }
            if (add)
            {
                filteredList.add(user);
                add = false;
            }
        }
        return filteredList;
    }

    private static List<User> getUsersByPreferences(List<User> users, List<String> preferences)
    {
        List<User> filteredList = new ArrayList<>();

        boolean add = false;

        for (User user : users)
        {
            if (preferences.contains("aplecs"))
            {
                if (user.getAplecs()) add = true;
                else add = false;
            }
            if (preferences.contains("ballades"))
            {
                if (user.getBallades()) add = true;
                else add = false;
            }
            if (preferences.contains("concerts"))
            {
                if (user.getConcerts()) add = true;
                else add = false;
            }
            if (preferences.contains("concursos"))
            {
                if (user.getConcursos()) add = true;
                else add = false;
            }
            if (preferences.contains("cursets"))
            {
                if (user.getCursets()) add = true;
                else add = false;
            }
            if (preferences.contains("altres"))
            {
                if (user.getAltres()) add = true;
                else add = false;
            }
            if (add)
            {
                filteredList.add(user);
                add = false;
            }
        }
        return filteredList;
    }

    public static boolean checkEventNames(List<String> events)
    {
        Boolean nameOK = false;
        for (String event: events)
        {
            if (event.equals("aplecs") || event.equals("ballades") || event.equals("concerts") ||
                    event.equals("concursos") || event.equals("cursets") || event.equals("altres"))
                nameOK = true;
            else {
                nameOK = false;
                break;
            }
        }
        return nameOK;
    }
}

