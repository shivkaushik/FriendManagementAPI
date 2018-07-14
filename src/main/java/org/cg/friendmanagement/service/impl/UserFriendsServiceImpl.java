package org.cg.friendmanagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.cg.friendmanagement.dao.UserFriendDAO;
import org.cg.friendmanagement.entity.User;
import org.cg.friendmanagement.request.UserFriendsListRequestEntity;
import org.cg.friendmanagement.request.UserFriendsRequestEntity;
import org.cg.friendmanagement.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserFriendsServiceImpl implements UserFriendsService {

	@Autowired
	private UserFriendDAO userFriendDAO;

	private User saveIfNotExist(String email) {

		User existingUser = this.userFriendDAO.findByEmail(email);
		if (existingUser == null) {
			existingUser = new User();
			existingUser.setEmail(email);
			return this.userFriendDAO.save(existingUser);
		} else {
			return existingUser;
		}

	}

	@Override
	public ResponseEntity<Map<String, Object>> addUserFriends(UserFriendsRequestEntity userFriendsRequestEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (userFriendsRequestEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		if (CollectionUtils.isEmpty(userFriendsRequestEntity.getFriends())) {
			result.put("Error : ", "Friend list cannot be empty");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		if (userFriendsRequestEntity.getFriends().size() != 2) {
			result.put("Info : ", "Please provide 2 emails to make them friends");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		String email1 = userFriendsRequestEntity.getFriends().get(0);
		String email2 = userFriendsRequestEntity.getFriends().get(1);

		if (email1.equals(email2)) {
			result.put("Info : ", "Cannot make friends, if users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		User user1 = null;
		User user2 = null;
		user1 = this.saveIfNotExist(email1);
		user2 = this.saveIfNotExist(email2);

		if (user1.getUserFriends().contains(user2)) {
			result.put("Info : ", "Can't add, they are already friends");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		if (user1.getBlockUsers().contains(user2)) {
			result.put("Info : ", "Can't add, friends are blocked ");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		user1.addUserFriends(user2);
		this.userFriendDAO.save(user1);
		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getUserFriendsList(UserFriendsListRequestEntity userFriendsListRequestEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (userFriendsListRequestEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		User user = this.userFriendDAO.findByEmail(userFriendsListRequestEntity.getEmail());
		List<String> friendList = user.getUserFriends().stream().map(User::getEmail).collect(Collectors.toList());

		result.put("success", true);
		result.put("friends", friendList);
		result.put("count", friendList.size());

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Map<String, Object>> getCommonUserFriends(UserFriendsRequestEntity userFriendsRequestEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (userFriendsRequestEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		User user1 = null;
		User user2 = null;
		user1 = this.userFriendDAO.findByEmail(userFriendsRequestEntity.getFriends().get(0));
		user2 = this.userFriendDAO.findByEmail(userFriendsRequestEntity.getFriends().get(1));

		if (user1.getEmail().equals(user2.getEmail())) {
			result.put("Info : ", "Both users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		Set<User> friends = null;
		friends = user1.getUserFriends();
		friends.retainAll(user2.getUserFriends());

		result.put("success", true);
		result.put("friends", friends.stream().map(User::getEmail).collect(Collectors.toList()));
		result.put("count", friends.size());

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
