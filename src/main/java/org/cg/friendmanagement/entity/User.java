package org.cg.friendmanagement.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email")
	private String email;

	@ManyToMany
	@JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "userId") , inverseJoinColumns = @JoinColumn(name = "friendId") )
	private Set<User> userFriends;

	@ManyToMany
	@JoinTable(name = "subscribe_users", joinColumns = @JoinColumn(name = "subscribeUserId") , inverseJoinColumns = @JoinColumn(name = "targetUserId") )
	private Set<User> subscribeUsers;

	@ManyToMany
	@JoinTable(name = "block_users", joinColumns = @JoinColumn(name = "blockUserId") , inverseJoinColumns = @JoinColumn(name = "targetUserId") )
	private Set<User> blockUsers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<User> getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(Set<User> userFriends) {
		this.userFriends = userFriends;
	}

	public Set<User> getSubscribeUsers() {
		return subscribeUsers;
	}

	public void setSubscribeUsers(Set<User> subscribeUsers) {
		this.subscribeUsers = subscribeUsers;
	}

	public Set<User> getBlockUsers() {
		return blockUsers;
	}

	public void setBlockUsers(Set<User> blockUsers) {
		this.blockUsers = blockUsers;
	}

	public void addUserFriends(User user) {
		if (CollectionUtils.isEmpty(this.userFriends)) {
			this.userFriends = new HashSet<>();
		}
		this.userFriends.add(user);
	}

	public void addSubscribeUsers(User user) {
		if (CollectionUtils.isEmpty(this.subscribeUsers)) {
			this.subscribeUsers = new HashSet<>();
		}
		this.subscribeUsers.add(user);
	}

	public void addBlockUsers(User user) {
		if (CollectionUtils.isEmpty(this.blockUsers)) {
			this.blockUsers = new HashSet<>();
		}
		this.blockUsers.add(user);
	}
	
}
