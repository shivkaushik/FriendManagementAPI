package org.cg.frientmanagement.service;

import java.util.Map;

import org.cg.friendmanagement.request.UserFriendsListRequestEntity;
import org.cg.friendmanagement.request.UserFriendsRequestEntity;
import org.springframework.http.ResponseEntity;

public interface UserFriendsService {

	ResponseEntity<Map<String, Object>> addUserFriends(UserFriendsRequestEntity userFriendsRequestEntity);

	ResponseEntity<Map<String, Object>> getUserFriendsList(UserFriendsListRequestEntity userFriendsListRequestEntity);

	ResponseEntity<Map<String, Object>> getCommonUserFriends(UserFriendsRequestEntity userFriendsRequestEntity);
}
