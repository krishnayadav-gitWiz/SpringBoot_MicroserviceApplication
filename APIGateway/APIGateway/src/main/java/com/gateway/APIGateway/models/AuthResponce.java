package com.gateway.APIGateway.models;

import java.util.Collection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "Hotel_Service")
public class AuthResponce {
	
	private String userId;
	private String accessToken;
	private String refreshToken;
	
	private long expireAt;
	
	private Collection<String> authorities;
	

}
