package com.SWE.OnlineStorePlatform.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.SWE.OnlineStorePlatform.models.User;
import com.SWE.OnlineStorePlatform.models.UserRepo;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepo repo;

	public List<User> listAll() {
		return repo.findAll();
	}

	public void save(User user) {
		repo.save(user);
	}

	public List<User> getUserByEmail(String email) {
		return repo.findUserByEmail(email);
	}

	public List<User> getUserByEmail(int email) {
		return null;
	}

	public User get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
