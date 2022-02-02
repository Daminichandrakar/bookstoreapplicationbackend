package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.UserEntity;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRegisterRepository;

//	@Autowired
//	private BookRepository bookRepository;

	@Autowired
	private EmailSenderService service;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDeatilsService customUserDeatilsService;

	public UserEntity add(UserDto userDto) {
		UserEntity registedUser = userRegisterRepository.findByEmailId(userDto.emailId);
		UserEntity userEntity = new UserEntity(userDto);
		if (registedUser == null) {

			userRegisterRepository.save(userEntity);
			return userEntity;
		}
		return null;
	}

	public List<UserEntity> getall() {
		log.info("Printing All data");
		return userRegisterRepository.findAll();
	}

	public String newPassword(String token, String newPassword) {

		String emailId = jwtUtil.extractUsername(token);
		UserDetails userDetails = customUserDeatilsService.loadUserByUsername(emailId);
		if (jwtUtil.validateToken(token, userDetails)) {
			UserEntity userEntity = userRegisterRepository.findByEmailId(emailId);
			userEntity.setPassword(newPassword);
			userRegisterRepository.save(userEntity);
			return "success";
		} else {
			return "failed";
		}

	}

	public String forgetPasswordMailSender(String emailId) {
		if (userRegisterRepository.existsByEmailId(emailId)) {
			UserDetails userDetails = customUserDeatilsService.loadUserByUsername(emailId);
			String token = jwtUtil.generateToken(userDetails);

			System.out.println("trying to send");
			service.sendSimpleEmail(emailId, "Your Reset Password Link For BookStore Is http://localhost:4200/reset-password/" + token, "Reset Your Password for BootStore");
			return ("Sent");
		}
		return "Not Sent";
	}

	

	public UserEntity getUserbyToken(String Token) {
		UserEntity entity = userRegisterRepository.findByEmailId(jwtUtil.extractUsername(Token));
		return entity;
	}

	
}
