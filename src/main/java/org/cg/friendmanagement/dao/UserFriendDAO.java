package org.cg.friendmanagement.dao;

import java.util.List;
import java.util.Set;

import org.cg.friendmanagement.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserFriendDAO extends CrudRepository<User, Long> {

	User findByEmail(String email);
	List<User> findAllByEmail(Set<String> emails);
}