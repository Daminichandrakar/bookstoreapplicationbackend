package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.UserEntity;
import com.bridgelabz.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("http://localhost:3000")
@Slf4j
@RestController
public class UserController {
	
	
	@Autowired
	UserService userService;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path="/signup")
	public ResponseEntity<ResponseDTO> post(@Valid @RequestBody UserDto userregisterdto) {
		ResponseDTO userRegisterResponceDto = new ResponseDTO("Added successfully!", userService.add(userregisterdto));
		return new ResponseEntity<ResponseDTO>(userRegisterResponceDto , HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<ResponseDTO> getAllEmployeeData() {
		List<UserEntity> List = userService.getall();
		ResponseDTO userRegisterResponceDto = new ResponseDTO("All the Users!", List);
		return new ResponseEntity<ResponseDTO>(userRegisterResponceDto, HttpStatus.OK);
	}
	
//	@PutMapping("/reset-password")
//	public ResponseEntity<ResponseDTO> resetpassword(@RequestBody NewPassword newPassword) {
//		ResponseDTO userRegisterResponceDto = new ResponseDTO("NewPassWord:", userService.newPassword(newPassword.getToken(), newPassword.getNewPassword()));
//		return new ResponseEntity<ResponseDTO>(userRegisterResponceDto, HttpStatus.OK);
//	}

	@GetMapping("/id/{token}")
	public ResponseEntity<ResponseDTO> getUser(@PathVariable String token ) {
		ResponseDTO userRegisterResponceDto = new ResponseDTO("All the Users!", userService.getUserbyToken(token));
		return new ResponseEntity<ResponseDTO>(userRegisterResponceDto, HttpStatus.OK);
	}
}
