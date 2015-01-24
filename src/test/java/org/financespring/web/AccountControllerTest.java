package org.financespring.web;

import org.financespring.model.Account;
import org.financespring.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:web/WEB-INF/spring-servlet.xml")
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testShowClientDetails() throws Exception {
        mockMvc.perform(get("/clientdetails"))
                .andExpect(model().attribute("account", notNullValue()))
                .andExpect(view().name("accountpage"));
    }

    /*
    Test in progress
     */
    @Test
    public void testAddNewAccount() throws Exception {
        Client mockClient = new Client();
        mockClient.setId(1);

        mockMvc.perform(post("/newaccount")
                .param("accountNumber", "US0000000")
                .param("amount", "100.00f")
                .param("currency", "USD")
                .param("date", "2014-12-12")
                .sessionAttr("account", new Account())
                .sessionAttr("client", mockClient))
                .andExpect(model().attribute("account", notNullValue()))
                .andExpect(model().attribute("client", notNullValue()))
                .andExpect(view().name("accountpage"));
    }
}
