package service;

import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetAll(){
        List<User> testUsers = userService.getAll();
        Assert.assertNotNull(testUsers);
    }

    @Test
    public void testGetById(){
        long testId=1;
        User user=userService.getById(testId);
        Assert.assertNotNull(user);
    }

    @Test
    public void testGetByIdWithInvalidId(){
        long testId=-999;
        User testUser=userService.getById(testId);
        Assert.assertNull(testUser);
    }

    @Test
    public void testFindByUsername(){
        String testUsername="testuser";
        User testUser=userService.findByUsername(testUsername);
        Assert.assertNotNull(testUser);
    }

    @Test
    public void testFindByUsernameWithInvalidUsername(){
        String testUsername= "";
        User testUser=userService.findByUsername(testUsername);
        Assert.assertNull(testUser);
    }

    @Test
    public void testSave(){
        User testUser = new User();
        testUser.setUsername("testuser1");
        testUser.setPassword("12345678");
        testUser.setEmail("test@mail.ru");
        userService.save(testUser);
    }

}