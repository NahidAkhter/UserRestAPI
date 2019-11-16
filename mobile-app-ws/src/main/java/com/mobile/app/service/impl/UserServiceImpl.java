package com.mobile.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobile.app.dto.UserDto;
import com.mobile.app.entity.UserEntity;
import com.mobile.app.repo.UserRepository;
import com.mobile.app.service.UserService;
import com.mobile.app.shared.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
		
	@Override
	public UserDto getUser(String email) {
		UserDto returnValued = new UserDto();
		
		if(userRepository.findByEmail(email) == null) throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());	
		
			UserEntity userEntity = userRepository.findByEmail(email);			
			BeanUtils.copyProperties(userEntity, returnValued);
		
		return returnValued;
	}
	
	@Override
	public UserDto createUser(UserDto user) {
		if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exits!");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		UserEntity storeUserEntity = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storeUserEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto updateUser(String email, UserDto userDto) {
		UserDto returnValued = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());

		UserEntity updateUserDetails = userRepository.save(userEntity);
		BeanUtils.copyProperties(updateUserDetails, returnValued);
		return returnValued;
	}
	
	@Override
	public void deleteUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		List<UserDto> returnValue = new ArrayList<>();
		Pageable pageRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageRequest);
		List<UserEntity> users = usersPage.getContent();
		
		
		for(UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		
		return returnValue;
	}
}