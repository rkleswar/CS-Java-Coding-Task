package com.example.demo.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.example.demo.bean.PostRequestBean;
import com.example.demo.helper.MockData;
import com.example.demo.helper.ResourceLoader;
import com.example.demo.service.WallPostService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class WallPostControllerTest {
	
	private MockMvc mockMvc;

    @InjectMocks
    private WallPostController wallPostControllerSpy;

    @Mock
    private WallPostService demoService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wallPostControllerSpy).build();
    }

    @Test
    public void testcreateUser() throws JsonProcessingException, Exception {
    	mockMvc.perform(post("/api/posts").content(ResourceLoader.asJsonString(new PostRequestBean()))
    			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Test
    public void testGetNewsFeed() throws Exception {
        mockMvc.perform(get("/api/posts/{userId}", MockData.UESR_ID.getLong())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Test
    public void testFollow() throws JsonProcessingException, Exception {
    	mockMvc.perform(post("/api/follow/{followerId}/{followeeId}",MockData.UESR_ID.getLong(),MockData.UESR_ID.getLong())
    			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testUnFollow() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/api/unfollow/{followerId}/{followeeId}", MockData.FOLLOWER_ID.getLong(), MockData.FOLLOWEE_ID.getLong())
    			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
