package com.huertos.comunidad_huertos_api.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.huertos.comunidad_huertos_api.UserAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
 @Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private Long id;
	@Column(nullable = false,unique = true,length = 10)
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	private UserAuthority authority;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date joinedAt;

    
    
    
	
}
