package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID id);
    CustomerDTO createCustomer(UUID id, CustomerDTO customer);
    void updateCustomer(UUID id, CustomerDTO customer);
    void deleteCustomerById(UUID id);
}
