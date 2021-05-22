package com.vijay.SpringKafkaMessaging.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.SpringKafkaMessaging.auth.AuthRepository;
import com.vijay.SpringKafkaMessaging.persistence.repository.CacheRepository;
import com.vijay.SpringKafkaMessaging.persistence.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    CacheRepository cacheRepository;

    @RequestMapping(value = "/getcode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getCode(@Valid @RequestBody ActivationRequest activationRequest) {

        // TODO

        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        // TODO

        return null;
    }
}
