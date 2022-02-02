package com.bridgelabz.bookstore.model;


import com.bridgelabz.bookstore.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Data
@Entity
@Table(name="User") 
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fullName;
	private String emailId;
	private String password;
	private Long phoneNumber;
//	private String role;
	
//	@CreatedDate
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@CreationTimestamp
	@JsonFormat(pattern="dd MM yyyy")
	private LocalDate updatedDate;
	
	private boolean status;


	public UserEntity(int id, UserDto userbookdto) {
		super();
		this.id = id;
		this.fullName=userbookdto.fullName;
		this.emailId=userbookdto.emailId;
		this.password=userbookdto.password;
		this.phoneNumber=userbookdto.phoneNumber;
		}

	 public UserEntity(UserDto userbookdto) {
		super();
		this.id = id;
		this.fullName=userbookdto.fullName;
		this.emailId=userbookdto.emailId;
		this.password=userbookdto.password;
		this.phoneNumber=userbookdto.phoneNumber;
		}


}



