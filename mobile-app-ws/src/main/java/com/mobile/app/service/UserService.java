package com.mobile.app.service;

import java.util.List;

import com.mobile.app.dto.UserDto;

public interface UserService {
	
	UserDto getUser(String email);
	UserDto createUser(UserDto user);
	UserDto updateUser(String email, UserDto userDto);
	void deleteUser(String email);
	List<UserDto> getUsers(int page, int limit);
}
