package com.rebit.services;

import com.rebit.model.User;

public interface UserService {

//	Creating User
	public User createUser(User user) throws Exception;
	
//	Getting user using username
	public User getUser(String username);
	
//	Deleting user using id
	public void deleteUser(Long id);
	
}
