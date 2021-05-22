package com.vijay.SpringKafkaMessaging.persistence.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="access_token")
public class AccessToken implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long token_id;

    @Column(name="token")
    private String token;

    @Column(name="user_id")
    private Long userId;

    @Column(name="created_at")
    private Date createdAt;

    public AccessToken(String token,Long userId,Date createdAt) {
		this.token = token;
		this.userId = userId;
		this.createdAt = createdAt;
	}
}
