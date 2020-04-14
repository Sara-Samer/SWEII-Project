package com.SWE.OnlineStorePlatform;

import com.SWE.OnlineStorePlatform.services.UsersController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
@WebMvcTest
public class UsersControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersController usersController;

    // u sure u have the testing ? yup
    // junit ? no 
    // بس بيشتغل ع testNG عادي
    // @Test 
    // void registerType() throws Exception{
    //     MvcResult result = this.mockMvc.perform(get("/register/sara"))
    //                 .andReturn();
    //     final String responseString = result.getResponse().getContentAsString();
    //     System.out.println("RESPONSE:" + responseString);
    // }
    @Test
    void registerType() throws Exception{
        this.mockMvc.perform(get("/register/sara"))
                    .andDo(print())
                    .andExpect(status().isOk());
        //System.out.println("RESPONSE:" + responseString);
    }
}
