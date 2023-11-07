package com.rebit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rebit.model.User;
import com.rebit.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User createUser(User user) throws Exception {

		User local=repository.findByEmail(user.getEmail());
		
		if(local!=null) {
			System.out.println("User already present");
			throw new Exception("User already present");
		}else {
			
			user=repository.save(user);
			System.out.println(local);
		}
		
		return user;
	}

	@Override
	public User getUser(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

}
