// UserServiceImpl.java
package com.example.MVC.Implementation;

import com.example.MVC.Dtos.Request.UserRequest;
import com.example.MVC.Dtos.Response.UserResponse;
import com.example.MVC.Entities.User;
import com.example.MVC.Mappers.UserMapper;
import com.example.MVC.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImplementation implements UserService {

    private final MongoTemplate mongoTemplate;
    private final UserMapper UserMapper;
    private final UserMapper userMapper;


    @Override
    public List<UserResponse> getUsers(List<String> roles, String sortField) {
        Query query = new Query();

        if (roles != null && !roles.isEmpty()) {
            query.addCriteria(Criteria.where("roleById").in(roles));
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = "name";
        }
        query.with(Sort.by(Sort.Direction.ASC, sortField));

        List<User> users = mongoTemplate.find(query, User.class, "User");

        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = mongoTemplate.findById(id, User.class, "User");
        if (user == null) return null;
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        mongoTemplate.save(user, "User");
        System.out.println(user);
        return userMapper.toDto(user);
    }

}