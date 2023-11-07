package com.rebit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rebit.model.User;
@Repository 
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String useremail);
	
    public User findByUsername(String username);

	
}
