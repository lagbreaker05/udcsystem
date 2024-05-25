package com.example.udc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.udc.dto.UserDto;
import com.example.udc.model.User;
import com.example.udc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(), userDto.getFullName());
		return userRepository.save(user);
	}

	
}
