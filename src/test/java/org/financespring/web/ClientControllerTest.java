package org.financespring.web;

import org.financespring.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:web/WEB-INF/spring-servlet.xml")
public class ClientControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testInitNewClientForm() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("clientpage"))
                .andExpect(model().attributeExists("client", "listOfClients"))
                .andExpect(model().attribute("client", notNullValue()))
                .andExpect(model().attribute("listOfClients", hasSize(notNullValue())));
    }

    @Test
    public void testAddNewClient() throws Exception {
        mockMvc.perform(post("/newclient")
                .param("firstName", "A")
                .param("lastName", "B")
                .param("address", "ADDRESS")
                .param("city", "CITY")
                .param("postalCode", "00000")
                .sessionAttr("client", new Client()))
                .andExpect(request().attribute("client", hasProperty("firstName", equalTo("A"))))
                .andExpect(request().attribute("client", hasProperty("id", not(equalTo(0)))))
                .andExpect(model().attributeExists("listOfClients"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("clientpage"));
    }

    /*
    To test processClientCRUD() properly I should try to use Mockito. The test in a progress.
     */
    @Test
    public void testProcessClientCRUD() throws Exception{
        mockMvc.perform(post("/clientselection")
                .param("client-action", "addclient")
                .param("id", "0"))
                .andExpect(view().name("newclientpage"))
                .andExpect(model().attribute("client", notNullValue()));
        mockMvc.perform(post("/clientselection")
                .param("client-action", "delclient"))
                .andExpect(view().name("error-page"));
        mockMvc.perform(post("/clientselection")
                .param("client-action", "editclient")
                .param("id", "20"))
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("newclientpage"));
        mockMvc.perform(post("/clientselection")
                .param("client-action", "showclientdetails")
                .param("id", "20"))
                .andExpect(model().attribute("account", notNullValue()))
                .andExpect(model().attribute("client", notNullValue()))
                .andExpect(view().name("accountpage"));
    }
}
