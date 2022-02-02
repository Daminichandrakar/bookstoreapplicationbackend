package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.JwtRequest;
import com.bridgelabz.bookstore.dto.JwtResponse;
import com.bridgelabz.bookstore.service.CustomUserDeatilsService;
import com.bridgelabz.bookstore.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class JwtController {

	@Autowired
	private CustomUserDeatilsService customUserDeatilsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	@CrossOrigin(origins = "*")
	@RequestMapping(value= "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					jwtRequest.getEmailId(),
					jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			System.out.println(jwtRequest);
			throw new Exception("worng");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			System.out.println(jwtRequest);
			throw new Exception("worng!");
		}
		
		UserDetails userDetails = this.customUserDeatilsService.loadUserByUsername(jwtRequest.getEmailId());
		String token=this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	
	}
}

