package com.c7.corrida.resourceTests;

import com.c7.corrida.entities.Category;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.enums.CategoryRule;
import com.c7.corrida.resources.UserResource;
import com.c7.corrida.security.SecurityConfig;
import com.c7.corrida.services.UserService;
import com.c7.corrida.services.exceptions.ResourceExistsException;
import com.c7.corrida.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Security config OFF

//@WebMvcTest(UserResource.class)
public class UserResourceTest {
    private static final String END_POINT_PATH = "/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testGet_ShouldReturn_404Message() throws Exception{
        Long userId = 100L;

        Mockito.when(userService.findById(userId)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(
                get(END_POINT_PATH + "/{code}", userId)
        )
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    public void testGet_ShouldReturn_200Message() throws Exception{
        Long userId = 1L;

        Mockito.when(
                userService.findById(userId)
        ).thenReturn(
                new User(1L,"Claudior", "9999","claudior@gmail.com",20,"VASCO")
        );

        mockMvc.perform(
                get(END_POINT_PATH + "/{code}", userId)
        )
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testPost_ShouldReturn_409Message() throws Exception{
        Category cc1 = new Category(1L, CategoryRule.ADMIN);
        User user = new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        user.setCategory(cc1);

        String userToRequestBody = "{\n" +
                "        \"name\": \"È O USEER NE VIDA ?\",\n" +
                "        \"password\": \"VASCO\",\n" +
                "        \"email\": \"claudior@gmail.com\",\n" +
                "        \"rankPoints\": 25,\n" +
                "        \"bio\": \"VASCO\",\n" +
                "        \"category\": {\n" +
                "            \"id\" : 1,\n" +
                "            \"categoryRule\" : \"ADMIN\"\n" +
                "        }\n" +
                "    }";

        Mockito.when(userService.insert(user)
        ).thenThrow(ResourceExistsException.class);

        mockMvc.perform(
                post(END_POINT_PATH)
                        .contentType("application/json")
                        .content(userToRequestBody)
        )
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void testPost_ShouldReturn_200Message() throws Exception{

        User user = new User(null,"Claudior", "9999","claudior@gmail.com",20,"VASCO");
        String userToRequestBody = "{\n" +
                "        \"name\": \"È O USEER NE VIDA ?\",\n" +
                "        \"password\": \"VASCO\",\n" +
                "        \"email\": \"claudior@gmail.com\",\n" +
                "        \"rankPoints\": 25,\n" +
                "        \"bio\": \"VASCO\",\n" +
                "        \"category\": {\n" +
                "            \"id\" : 1,\n" +
                "            \"categoryRule\" : \"ADMIN\"\n" +
                "        }\n" +
                "    }";

        Mockito.when(
                userService.insert(user)
        ).thenReturn(user);
        mockMvc.perform(
                post(END_POINT_PATH).contentType("application/json")
                        .contentType("application/json")
                        .content(userToRequestBody)
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType("application/json")
                )
                .andExpect(
                        jsonPath("$.rankPoints", is(25))
                )
                .andDo(print());

    }


}
