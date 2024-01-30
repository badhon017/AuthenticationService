package com.JwtAuthentication.jwtAuthService.service;

import com.JwtAuthentication.jwtAuthService.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "badhon", "badhon@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Rahul", "rahul@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Roton", "roton@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Minhaz", "minhaz@gmail.com"));
    }

    public List<User> getUsers() {
        return this.store;
    }
}
