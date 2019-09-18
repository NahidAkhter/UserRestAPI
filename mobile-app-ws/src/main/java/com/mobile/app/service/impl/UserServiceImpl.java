package com.mobile.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mobile.app.dto.UserDto;
import com.mobile.app.entity.UserEntity;
import com.mobile.app.exceptions.UserServiceException;
import com.mobile.app.repo.UserRepository;
import com.mobile.app.response.model.ErrorMessages;
import com.mobile.app.service.UserService;
import com.mobile.app.shared.Utils;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder byCryptPasswordEncoder;


	@Override
	public UserDto createUser(UserDto user) {

		if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exits!");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		userEntity.setUserId(utils.generatedUserId(30));
		userEntity.setEncryptedPassword(byCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity storeUserEntity = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storeUserEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if(userEntity == null) throw 
		new UsernameNotFoundException(email + "not found in our record");

		return new User(userEntity.getEmail(),
				userEntity.getEncryptedPassword(),
				new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if(userEntity == null) throw new UsernameNotFoundException(email + "Not found in our record");

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByEmail(userId);

		if(userEntity == null) throw new UsernameNotFoundException(userId + "Not found in our record");

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserDto returnvalue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		if(userEntity == null) throw new UsernameNotFoundException(userId + "Not found in our record");

		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());

		UserEntity updatedUserDetails = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnvalue);


		return returnvalue;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);

		if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMesage());

		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		List<UserDto> returnValue = new  ArrayList<>();

		if(page > 0) page = page -1;

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();

		for(UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}
}