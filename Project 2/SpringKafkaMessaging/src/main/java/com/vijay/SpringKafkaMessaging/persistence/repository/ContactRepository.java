package com.vijay.SpringKafkaMessaging.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vijay.SpringKafkaMessaging.persistence.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findAllByUserId(Long userId);

    Contact findByContactUserId(Long contactUserId);

    void deleteByContactUserId(Long contactUserId);
}