package com.axonactive.kiss.TDD.service;

import com.axonactive.kiss.TDD.model.User;
import com.axonactive.kiss.TDD.repository.BookRepository;
import com.axonactive.kiss.TDD.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;

	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private UserServiceImpl userService;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllUser(){
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1,"Nguyen Van A","Quang Nam"));
		userList.add(new User(2,"Nguyen Van B","Quang Nam"));
		userList.add(new User(3,"Nguyen Van C","Quang Nam"));
		when(userRepository.findAll()).thenReturn(userList);
		
		List<User> result = userService.getAllUser();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetUserById(){
		User user = new User(1,"Nguyen Van D","Quang Nam");
		when(userRepository.findOne(1L)).thenReturn(user);
		User result = userService.getUserById(1);
		assertEquals(1, result.getId());
		assertEquals("Nguyen Van D", result.getUsername());
		assertEquals("Quang Nam", result.getAddress());
	}
	
	@Test
	public void saveUser(){
		User user = new User(8,"Le Thi F","Quang Nam");
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.saveUser(user);
		assertEquals(8, result.getId());
		assertEquals("Le Thi F", result.getUsername());
		assertEquals("Quang Nam", result.getAddress());
	}
	
	@Test
	public void removeUser(){
		User user = new User(8,"Le Thi F","Quang Nam");
		userService.removeUser(user);
        verify(userRepository, times(1)).delete(user);
	}

	@Test
	public void userRankA(){
		long userId = 1L;
		when(bookRepository.countByUserId(userId)).thenReturn(40);
		String rank = userService.getUserRank(userId);
		assertEquals("A", rank);
	}

    @Test
    public void userRankB(){
        long userId = 1L;
        when(bookRepository.countByUserId(userId)).thenReturn(35);
        String rank = userService.getUserRank(userId);
        assertEquals("B", rank);
    }

    @Test
    public void userRankC(){
        long userId = 1L;
        when(bookRepository.countByUserId(userId)).thenReturn(17);
        String rank = userService.getUserRank(userId);
        assertEquals("C", rank);
    }

    @Test
    public void userRankD(){
        long userId = 1L;
        when(bookRepository.countByUserId(userId)).thenReturn(5);
        String rank = userService.getUserRank(userId);
        assertEquals("D", rank);
    }

    @Test
    public void userNonRank(){
        long userId = 1L;
        when(bookRepository.countByUserId(userId)).thenReturn(0);
        String rank = userService.getUserRank(userId);
        assertEquals("NON", rank);
    }
}

