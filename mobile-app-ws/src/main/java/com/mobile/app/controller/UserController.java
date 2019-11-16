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
import com.mobile.app.request.model.UserDetailsRequestModel;
import com.mobile.app.response.model.UserDetailsResponseModel;
import com.mobile.app.service.UserService;
import com.mobile.app.shared.ErrorMessages;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path= "/{email}")
	public UserDetailsResponseModel getUserDetails(@PathVariable String email) {
		UserDetailsResponseModel userReponse = new UserDetailsResponseModel();

		UserDto userDto = userService.getUser(email);
		BeanUtils.copyProperties(userDto, userReponse);

		return userReponse;
	}


	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		UserDetailsResponseModel  userResponse = new UserDetailsResponseModel();

		if (userDetails.getFirstName().isEmpty())
			throw new NullPointerException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, userResponse);

		return userResponse;		
	}

	@PutMapping(path="/{email}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel updateUser(@PathVariable String email, @RequestBody UserDetailsRequestModel userDetails) {
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updatedUser = userService.updateUser(email, userDto);
		BeanUtils.copyProperties(updatedUser, userResponse);

		return userResponse;
	}

	@DeleteMapping(path="/{email}",
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public void deleteUser(@PathVariable String email){

		userService.deleteUser(email);
		System.out.println("Delete the users");		
	}

	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserDetailsResponseModel> getUsers(@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit){
		
			List<UserDetailsResponseModel> returnValue = new ArrayList<>();
			
			List<UserDto> users = userService.getUsers(page, limit);
			for(UserDto userDto : users) {				
				UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
				BeanUtils.copyProperties(userDto, userDetailsResponseModel);
				returnValue.add(userDetailsResponseModel);
			}
		return returnValue;
	}
}