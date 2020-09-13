package guru.springframework.msscbrewery;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.controller.CustomerController;
import guru.springframework.msscbrewery.web.model.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerIntegrationTest {

    private static final UUID CUSTOMER_ID = UUID.randomUUID();
    private static final String CUSTOMER_NAME = "Robin Reliant";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomer() throws Exception {
        when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(buildCustomer());

        mockMvc.perform(get("/api/v1/customer/" + CUSTOMER_ID.toString())
            .accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.customerName", is(CUSTOMER_NAME)));
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDTO customer = buildCustomer();
        when(customerService.createCustomer(CUSTOMER_ID, customer)).thenReturn(customer);

        verifyResponse(mockMvc.perform(post("/api/v1/customer/" + CUSTOMER_ID.toString())
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer))));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customer = buildCustomer();

        mockMvc.perform(put("/api/v1/customer/" + CUSTOMER_ID.toString())
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)));
    }

    @Test
    public void deleteCustomer() throws Exception {
        CustomerDTO customer = buildCustomer();

        mockMvc.perform(put("/api/v1/customer/" + CUSTOMER_ID.toString())
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customer)));
    }

    private CustomerDTO buildCustomer() {
        return CustomerDTO.builder()
            .id(CUSTOMER_ID)
            .customerName("Robin Reliant")
            .build();
    }

    private void verifyResponse(ResultActions result) throws Exception {
        result
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.customerName", is(CUSTOMER_NAME)));
    }
}
