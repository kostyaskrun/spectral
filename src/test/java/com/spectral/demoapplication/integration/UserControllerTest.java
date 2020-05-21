package com.spectral.demoapplication.integration;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectral.demoapplication.DemoApplication;
import com.spectral.demoapplication.config.TestConfig;
import com.spectral.demoapplication.model.dto.UserDto;
import com.spectral.demoapplication.model.form.UserCreateForm;
import com.spectral.demoapplication.model.form.UserUpdateForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class, TestConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Sql(scripts = "classpath:script.sql")
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void onSetUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void createUserTest() throws Exception {
        UserCreateForm createForm = new UserCreateForm();
        String name = "Anhel";
        createForm.setName(name);
        createForm.setEmail("anhel@gmail.com");
        createForm.setCountryName("ALGERIA");
        createForm.setRoleNames(Collections.singletonList("admin"));

        MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createForm)))
                .andReturn().getResponse();
        assertEquals(201, response.getStatus());

        response = mockMvc.perform(get("/users?name=" + name))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
        assertEquals(userDtos.get(0).getName(), name);
    }

    @Test
    public void updateUserTest() throws Exception {
        UserUpdateForm updateForm = new UserUpdateForm();
        String name = "Anhel";

        updateForm.setUserId(2L);
        updateForm.setName(name);
        updateForm.setEmail("anhel@gmail.com");
        updateForm.setCountryName("ALGERIA");
        updateForm.setRoleNames(Collections.singletonList("admin"));

        MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateForm)))
                .andReturn().getResponse();
        assertEquals(201, response.getStatus());

        response = mockMvc.perform(get("/users?name=" + name))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
        assertEquals(userDtos.get(0).getName(), name);
    }

    @Test
    public void deleteUserTest() throws Exception {
        String name = "Vasya";
        //check if user exists which will be deleted later
        MockHttpServletResponse response = mockMvc.perform(get("/users?name=" + name))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
        assertEquals(userDtos.get(0).getName(), name);

        //delete specific user
        response = mockMvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(204, response.getStatus());

        //check if user has been successfully deleted
        response = mockMvc.perform(get("/users?name=" + name))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        object = response.getContentAsString();
        userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {});
        assertEquals(0, userDtos.size());
    }

    @Test
    public void getUsersWithoutFilter() throws Exception {
        //get without
        MockHttpServletResponse response = mockMvc.perform(get("/users"))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(2, userDtos.size());
    }

    @Test
    public void filterUsersByName() throws Exception {
        String name = "Vasya";
        MockHttpServletResponse response = mockMvc.perform(get("/users?name=" + name))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
        assertEquals(userDtos.get(0).getName(), name);
    }

    @Test
    public void filterUsersByEmail() throws Exception {
        String email = "vasya@gmail.com";
        MockHttpServletResponse response = mockMvc.perform(get("/users?email=" + email))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
        assertEquals(userDtos.get(0).getEmail(), email);
    }

    @Test
    public void filterUsersByCountryName() throws Exception {
        String countryName = "AFGHANISTAN";
        MockHttpServletResponse response = mockMvc.perform(get("/users?countryName=" + countryName))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
        assertEquals(userDtos.get(0).getCountry().getName(), countryName);
    }

    @Test
    public void filterUsersByRole() throws Exception {
        String role = "admin";
        MockHttpServletResponse response = mockMvc.perform(get("/users?roleName=" + role))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
    }

    @Test
    public void filterUsersByDateFrom() throws Exception {
        LocalDateTime dateFrom = LocalDate.of(2020, 5, 21).atStartOfDay();
        MockHttpServletResponse response = mockMvc.perform(get("/users?dateFrom=" + dateFrom))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(1, userDtos.size());
    }

    @Test
    public void filterUsersByDateTo() throws Exception {
        LocalDateTime dateTo = LocalDate.of(2020, 5, 21).atTime(LocalTime.MAX);
        MockHttpServletResponse response = mockMvc.perform(get("/users?dateTo=" + dateTo))
                .andReturn().getResponse();
        assertEquals(200, response.getStatus());
        String object = response.getContentAsString();
        List<UserDto> userDtos = objectMapper.readValue(object, new TypeReference<List<UserDto>>() {
        });
        assertEquals(2, userDtos.size());
    }
}
