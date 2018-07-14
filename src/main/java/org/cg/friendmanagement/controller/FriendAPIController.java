package org.cg.friendmanagement.controller;

import java.util.Map;

import org.cg.friendmanagement.request.BlockUserRequestEntity;
import org.cg.friendmanagement.request.SubscribeUserRequestEntity;
import org.cg.friendmanagement.request.UserFriendsListRequestEntity;
import org.cg.friendmanagement.request.UserFriendsRequestEntity;
import org.cg.friendmanagement.service.BlockUserService;
import org.cg.friendmanagement.service.SubscribeUserService;
import org.cg.friendmanagement.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendAPIController {

	@Autowired
	private UserFriendsService userFriendsService;

	@Autowired
	private SubscribeUserService subscribeUserService;

	@Autowired
	private BlockUserService blockUserService;

	@RequestMapping(value = "/userFriendRequest", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> userFriendRequest(@RequestBody UserFriendsRequestEntity userFriendsRequestEntity) {
		return this.userFriendsService.addUserFriends(userFriendsRequestEntity);
	}

	@RequestMapping(value = "/getUserFriendList", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getUserFriendList(@RequestBody UserFriendsListRequestEntity userFriendsListRequestEntity) {
		return this.userFriendsService.getUserFriendsList(userFriendsListRequestEntity);
	}

	@RequestMapping(value = "/getCommonUserFriends", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getCommonUserFriends(@RequestBody UserFriendsRequestEntity userFriendsRequestEntity) {
		return this.userFriendsService.getCommonUserFriends(userFriendsRequestEntity);
	}

	@RequestMapping(value = "/subscribeUserRequest", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> subscribeUserRequest(@RequestBody SubscribeUserRequestEntity subscribeUserRequestEntity) {
		return this.subscribeUserService.addSubscribeUser(subscribeUserRequestEntity);
	}

	@RequestMapping(value = "/blockUserRequest", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> blockUserRequest(@RequestBody BlockUserRequestEntity blockUserRequestEntity) {
		return this.blockUserService.addBlockUser(blockUserRequestEntity);
	}
}
