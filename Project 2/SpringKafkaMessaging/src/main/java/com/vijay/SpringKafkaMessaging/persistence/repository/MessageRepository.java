package com.vijay.SpringKafkaMessaging.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vijay.SpringKafkaMessaging.persistence.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}