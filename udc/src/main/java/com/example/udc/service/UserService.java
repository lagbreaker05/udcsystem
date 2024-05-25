package com.example.udc.service;

import com.example.udc.dto.UserDto;
import com.example.udc.model.User;

public interface UserService {

	User save(UserDto userDto);
	
}
 