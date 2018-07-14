package org.cg.friendmanagement.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.cg.friendmanagement.dao.UserFriendDAO;
import org.cg.friendmanagement.entity.User;
import org.cg.friendmanagement.request.BlockUserRequestEntity;
import org.cg.friendmanagement.service.BlockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BlockUserServiceImpl implements BlockUserService {

	@Autowired
	private UserFriendDAO userFriendDAO;

	@Override
	public ResponseEntity<Map<String, Object>> addBlockUser(BlockUserRequestEntity blockUserRequestEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (blockUserRequestEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		if (blockUserRequestEntity.getRequestor() == null || blockUserRequestEntity.getTarget() == null) {
			result.put("Error : ", "Requester or Target can not be empty");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		
		String email1 = blockUserRequestEntity.getRequestor();
		String email2 = blockUserRequestEntity.getTarget();

		if (email1.equals(email2)) {
			result.put("Info : ", "Cannot subscribe , if users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		
		User user1 = this.userFriendDAO.findByEmail(email1);
		User user2 = this.userFriendDAO.findByEmail(email2);

		user1.addBlockUsers(user2);
		this.userFriendDAO.save(user1);

		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}
