package com.movieFlix.mapper;

import com.movieFlix.entity.User;
import com.movieFlix.entity.dto.UserRequest;
import com.movieFlix.entity.dto.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserRequest request){
        return User.builder().name(request.name()).email(request.email()).password(request.password()).build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }
}
