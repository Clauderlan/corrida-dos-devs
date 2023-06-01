package com.c7.corrida.serviceTests;
import com.c7.corrida.entities.Category;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.enums.CategoryRule;
import com.c7.corrida.repositories.CategoryRepository;
import com.c7.corrida.repositories.UserRepository;
import static org.mockito.Mockito.*;
import com.c7.corrida.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Security config OFF
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindById_ShouldReturnOk() throws Exception{
        User user = new User(1L,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Long userId = 1L;
        User user1 = userService.findById(userId);
        Assertions.assertEquals(user1.getId(), userId);

    }

    @Test
    public void testFindAll_ShouldReturnOk() {
        List<User> users = List.of(new User());
        when(userService.findAll()).thenReturn(users);
        List<User> usersRep = userService.findAll();

        Assertions.assertEquals(users, usersRep);
    }

    @Test
    public void testLoadByUsernameShouldReturnOk(){
        String username = "Claudior";

        User user = new User(1L,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        when(userRepository.findByName(username)).thenReturn(user);
        User userRep = userService.loadUserByUsername(username);

        Assertions.assertEquals(user.getName(), userRep.getName());
    }

    @Test
    public void testSave_ShouldReturnOk() throws Exception{
        Category cc1 = new Category(null, CategoryRule.ADMIN);
        User user = new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        user.setCategory(cc1);

        when(categoryRepository.save(cc1)).thenReturn(cc1);
        when(userRepository.save(user)).thenReturn(user);
        User userRep = userService.insert(user);

        Assertions.assertEquals(userRep,user);
    }

    @Test
    public void testUpdate_ShouldReturnOk() throws Exception{
        Long userId = 1L;
        User userToUpdate = new User(1L,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        User user = new User(1L,"Raphao", "9999","claudior@gmail.com",20,"VASCO");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(userToUpdate);


        User updatedUser = userService.update(userId, userToUpdate);
        Assertions.assertNotEquals(updatedUser.getName(), userToUpdate.getName());
    }

}
