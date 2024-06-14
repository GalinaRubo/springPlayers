package com.pro.springPlayers.service;


import java.util.List;

import com.pro.springPlayers.models.User;

import dto.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
	User findUserByEmail(String email);
	User findUserById(Long id);
	User findUserByName(String Name);
	List<User> getAllUsers();
	
}
