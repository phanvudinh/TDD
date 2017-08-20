package com.axonactive.kiss.TDD.web;

import com.axonactive.kiss.TDD.TddApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TddApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllUserList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andExpect(status().is2xxSuccessful()).andDo(print());
	}

    @Test
    public void verifyUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.username").value("Thai Thi Hong"))
                .andExpect(jsonPath("$.address").value("Do Luong - Nghe An"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

	@Test
	public void getUserRankA() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/1/rank").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("A"))
				.andExpect(status().is2xxSuccessful())
				.andDo(print());
	}

    @Test
    public void verifyInvalidUserArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/f").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
                .andDo(print());
    }

    @Test
    public void verifyNullUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/6").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("User doesn´t exist"))
                .andDo(print());
    }

	@Test
	public void verifyDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/4").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("User has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserIdToDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/9").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status").value(404))
		.andExpect(jsonPath("$.message").value("User to delete doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\" : \"Tran Van Nghia\", \"address\" : \"Da Nang\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.username").exists())
		.andExpect(jsonPath("$.address").exists())
		.andExpect(jsonPath("$.username").value("Tran Van Nghia"))
		.andExpect(jsonPath("$.address").value("Da Nang"))
        .andExpect(status().is2xxSuccessful())
		.andDo(print());
	}

	@Test
	public void verifyUpdateUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"2\", \"username\" : \"Ho Quang Hieu\", \"address\" : \"Sai Gon\" }")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.username").exists())
		.andExpect(jsonPath("$.address").exists())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.username").value("Ho Quang Hieu"))
		.andExpect(jsonPath("$.address").value("Sai Gon"))
        .andExpect(status().is2xxSuccessful())
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidUserUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/user/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"8\", \"username\" : \"Le Thanh Phuc\", \"address\" : \"Quang Tri\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(404))
		.andExpect(jsonPath("$.message").value("User to update doesn´t exist"))
        .andExpect(status().is4xxClientError())
		.andDo(print());
	}

}
