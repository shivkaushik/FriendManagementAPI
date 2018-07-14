package org.cg.friendmanagement.service;

import java.util.Map;

import org.cg.friendmanagement.request.BlockUserRequestEntity;
import org.springframework.http.ResponseEntity;

public interface BlockUserService {

	ResponseEntity<Map<String, Object>> addBlockUser(BlockUserRequestEntity BlockUserRequestEntity);
}
