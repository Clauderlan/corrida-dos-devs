package com.c7.corrida.resourceTests;

import com.c7.corrida.entities.Category;
import com.c7.corrida.entities.User;
import com.c7.corrida.entities.enums.CategoryRule;
import com.c7.corrida.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void User_FindAll_Test() throws Exception{
        mockMvc.perform(
                get("/users")
        )
                .andExpect(status().isOk());
    }
    

    @Test
    public void User_Post_Test() throws Exception{
        Category cc1 = new Category(1L, CategoryRule.ADMIN);
        User user1 = new User(null,"Vascaino", "9999","clau@gmail.com",20,"VASCO");
        user1.setCategory(cc1);

        mockMvc.perform(
                post("/users")
                        .contentType("application/json")
                        .content(
                                objectMapper.writeValueAsString(
                                        user1
                                )
                        )
        ).andExpect(status().isOk());
    }
}
