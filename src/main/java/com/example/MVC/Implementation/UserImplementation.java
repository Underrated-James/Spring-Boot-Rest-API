// UserServiceImpl.java
package com.example.MVC.Implementation;

import com.example.MVC.Dtos.UserDto;
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


    @Override
    public List<UserDto> getUsers(List<String> roles, String sortField) {
        Query query = new Query();

        if (roles != null && !roles.isEmpty()) {
            query.addCriteria(Criteria.where("roleById").in(roles));
        }

        if (sortField == null || sortField.isEmpty()) {
            sortField = "name";
        }
        query.with(Sort.by(Sort.Direction.ASC, sortField));

        List<User> users = mongoTemplate.find(query, User.class, "users");

        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserDto getUserById(String id) {
        User user = mongoTemplate.findById(id, User.class, "users");
        if (user == null) return null;
        return UserMapper.toDto(user);
    }
}