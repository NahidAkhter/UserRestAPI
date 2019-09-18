package com.mobile.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.app.dto.UserDto;
import com.mobile.app.request.model.RequestOperationSatus;
import com.mobile.app.request.model.UserDetailsRequestModel;
import com.mobile.app.response.model.OperationalStatusModel;
import com.mobile.app.response.model.UserDetailsResponseModel;
import com.mobile.app.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel getUser(@PathVariable String id) {
		UserDetailsResponseModel returnedValued = new UserDetailsResponseModel();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnedValued);
		return returnedValued;
	}

	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();

		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("UserDefined : Object is NULL");

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, userResponse);

		return userResponse;
	}

	@PutMapping(path="/{id}",
				consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updatedUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updatedUser, userResponse);

		return userResponse;
	}

	@DeleteMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationalStatusModel deleteUser(@PathVariable String id) {
		
		OperationalStatusModel returnedValue = new OperationalStatusModel();
		returnedValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		
		returnedValue.setOperationResult(RequestOperationSatus.SUCCESS.name());
		return returnedValue;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserDetailsResponseModel> getUsers(@RequestParam(value="page", defaultValue="0") int page,
													@RequestParam(value="limit", defaultValue="25") int limit){
		
		List<UserDetailsResponseModel> returnValue = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);
		
		for(UserDto userDto : users) {
			UserDetailsResponseModel userModel = new UserDetailsResponseModel();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}	
		return returnValue;
	}
}