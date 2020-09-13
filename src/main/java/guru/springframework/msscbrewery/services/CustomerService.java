package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {

    CustomerDTO getCustomerById(UUID id);
    CustomerDTO createCustomer(UUID id, CustomerDTO customer);
    CustomerDTO updateCustomer(UUID id, CustomerDTO customer);
    CustomerDTO deleteCustomerById(UUID id, CustomerDTO customer);
}
