package org.cg.friendmanagement.service;

import java.util.Map;

import org.cg.friendmanagement.request.SubscribeUserRequestEntity;
import org.springframework.http.ResponseEntity;

public interface SubscribeUserService {

	ResponseEntity<Map<String, Object>> addSubscribeUser(SubscribeUserRequestEntity subscribeUserRequestEntity);
}
