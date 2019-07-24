package com.example.demo.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.bean.UserBean;
import com.example.demo.helper.MockData;
import com.example.demo.helper.ResourceLoader;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
/*@PrepareForTest(UserController.class)*/
public class UserControllerTest {
	
	private MockMvc mockMvc;

    @InjectMocks
    private UserController userControllerSpy;

    @Mock
    private UserService userServiceSpy;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerSpy).build();
    }

    @Test
    public void testfetchUserById() throws Exception {
        mockMvc.perform(get("/api/users/{userId}", MockData.UESR_ID.getLong())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Test
    public void testcreateUser() throws JsonProcessingException, Exception {
        mockMvc.perform(post("/api/users").content(ResourceLoader.asJsonString(new UserBean()))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


}
