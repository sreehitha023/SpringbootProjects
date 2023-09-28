package com.msf.test.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.msf.test.entity.User;
import com.msf.test.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    User user1;
    User user2;
    List<User> userList= new ArrayList<>();

    @BeforeEach
    void setUp() {
        user1 = new User(1L,"Sreehitha","USA","8328660757");
        user2 = new User(2L,"Chinni","UK","9949412585");
        userList.add(user1);
        userList.add(user2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserDetails() throws Exception {
        when(userService.getUser(1L)).thenReturn(user1);
        this.mockMvc.perform(get("/User/" + 1L)).andDo(print()).andExpect(status().isOk());
    }


    @Test
    void getAllUserDetails() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        this.mockMvc.perform(get("/User"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createUserDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(user1);

        when(userService.createUser(user1)).thenReturn("Success");
        this.mockMvc.perform(post("/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateUserDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(user1);

        when(userService.updateUser(user1))
                .thenReturn("User Updated Successfully");
        this.mockMvc.perform(put("/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteUserDetails() throws Exception {
        when(userService.deleteUser(1L))
                .thenReturn("User Deleted Successfully");
        this.mockMvc.perform(delete("/User/" + 1L))
                .andDo(print()).andExpect(status().isOk());

    }
}