package com.vijay.SpringKafkaMessaging.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vijay.SpringKafkaMessaging.persistence.model.AccessToken;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

    AccessToken findByUserId(Long userId);

    void deleteByUserId(Long userId);

}