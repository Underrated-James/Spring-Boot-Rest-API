// UserServiceImpl.java
package com.example.MVC.Implementation;

import com.example.MVC.Dtos.Request.UserRequest;
import com.example.MVC.Dtos.Response.UserResponse;
import com.example.MVC.Entities.User;
import com.example.MVC.Mappers.UserMapper;
import com.example.MVC.Repository.UserRepository;
import com.example.MVC.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImplementation implements UserService {

    private final MongoTemplate mongoTemplate;
    private final UserMapper UserMapper;
    private final UserRepository userRepository;

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
        User user = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponse updateUser(String id, UserRequest userRequest) {
        Query query = new Query(Criteria.where("_id").is(id));

        Update update = new Update();
        setIfNotNull(update, "name", userRequest.getName());
        setIfNotNull(update, "email", userRequest.getEmail());
        setIfNotNull(update, "phoneNumber", userRequest.getPhoneNumber());
        setIfNotNull(update, "roleById", userRequest.getRoleById());

        update.set("updatedAt", LocalDateTime.now());

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);

        User updatedUser = mongoTemplate.findAndModify(
                query,
                update,
                options,
                User.class,
                "User"
        );

        if (updatedUser == null) return null;

        return UserMapper.toDto(updatedUser);
    }

    private void setIfNotNull(Update update, String field, Object value) {
        if (value != null) {
            update.set(field, value);
        }
    }

    @Override
    public UserResponse deleteUser(String id) {
        User user = mongoTemplate.findById(id, User.class, "User");
        if(user == null) return null;
        mongoTemplate.remove(user, "User");
        return UserMapper.toDto(user);
    }

}