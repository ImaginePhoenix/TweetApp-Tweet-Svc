package com.tweetapp.user.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tweetapp.user.entity.User;
import com.tweetapp.user.repository.UserRepository;

@SpringBootTest(classes = UserService.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;

	@Test
	public void testRegister() {
		User user = new User();
		List<User> list = new ArrayList<>();
		user.setStatus(1);
		list.add(user);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(list);
		userService.register(user);
	}

}
