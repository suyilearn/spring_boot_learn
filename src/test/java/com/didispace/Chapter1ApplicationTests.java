package com.didispace;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter1ApplicationTests {

    private MockMvc mvc;

    private MockMvc mvc_user;

    @Autowired private BlogProperties blogProperties;
    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        mvc_user = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
	public void getHello() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(equalTo("hello World")));
    }

    @Test
    public void getBlogProperties()throws Exception {
        Assert.assertEquals(blogProperties.getName(),"程序猿DD");
        Assert.assertEquals(blogProperties.getTitle(),"Spring Boot教程");
    }


    @Test
    public void testUserController()throws Exception {
        RequestBuilder request = null;

        request = get("/users/");
        mvc_user.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));


        request = post("/users/")
                .param("id","1")
                .param("name","Ceshi")
                .param("age", "12");

        mvc_user.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = put("/users/1")
                .param("name", "Ceshi2")
                .param("age","23");

        mvc_user.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = get("/users/1");

        mvc_user.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Ceshi2\",\"age\":23}")));

        request = delete("/users/1");

        mvc_user.perform(request)
                .andExpect(content().string(equalTo("success")));

        request = get("/users/");

        mvc_user.perform(request)
                .andExpect(content().string(equalTo("[]")));

    }


}
