package com.vijay.SpringKafkaMessaging.auth.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.SpringKafkaMessaging.auth.AuthService;
import com.vijay.SpringKafkaMessaging.cache.repository.CacheRepository;
import com.vijay.SpringKafkaMessaging.persistence.model.User;
import com.vijay.SpringKafkaMessaging.persistence.repository.UserRepository;
import com.vijay.SpringKafkaMessaging.util.StringHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  AuthService authService;

	  @Autowired
	  CacheRepository cacheRepository;

	  @RequestMapping(value = "/getcode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public ResponseEntity<Object> getCode(@Valid @RequestBody ActivationRequest activationRequest) {

	    int code = StringHelper.generateRandomNumber(6);

	    // save the activation code to the cache repository (cached auth token)
	    cacheRepository.putActivationCode(activationRequest.getMobile(), String.valueOf(code));

	    ActivationResponse activationResponse = new ActivationResponse();
	    activationResponse.setMobile(activationRequest.getMobile());
	    activationResponse.setActivationCode(String.valueOf(code));

	    return new ResponseEntity<>(activationResponse,HttpStatus.OK);
	  }

	  @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
	    String mobile = cacheRepository.queryMobileActivationCode(loginRequest.getMobile(), loginRequest.getActivationCode());

	    if (mobile == null) {
	      return new ResponseEntity<>("Mobile number not found!",HttpStatus.NOT_FOUND);
	    } else {
	      Long userId = 0L;
	      User user = userRepository.findByMobile(loginRequest.getMobile());
	      if (user == null) {
	        // save user in persistence
	    	user = new User();
	    	user.setMobile(loginRequest.getMobile());
	        userRepository.save(user);
	        user = userRepository.findByMobile(loginRequest.getMobile());
	      }
	      
	      userId = user.getUserId();
	      String accessToken = UUID.randomUUID().toString();
	      authService.putAccessToken(accessToken, userId);

	      LoginResponse loginResponse = new LoginResponse();
	      loginResponse.setAccessToken(accessToken);
	      return new ResponseEntity<>(loginResponse,HttpStatus.OK);
	    }
	  }
}
