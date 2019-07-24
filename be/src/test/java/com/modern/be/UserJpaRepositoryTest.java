package com.modern.be;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.modern.be.user.entity.UserDto;
import com.modern.be.user.repository.UserJpaRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaRepositoryTest {

	@Autowired
	private UserJpaRepo userJpaRepo;
	
	
	public void findByUserId() {
		
		String userId = "1122";
		
		UserDto userDto = userJpaRepo.findByUserId(userId);
		
	}
	
}
