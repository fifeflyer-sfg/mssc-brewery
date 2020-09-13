package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return CustomerDTO.builder()
            .id(UUID.randomUUID())
            .customerName("Bruce Wayne")
            .build();
    }

    @Override
    public CustomerDTO createCustomer(UUID id, CustomerDTO customer) {
        log.debug("Created customer [{}]", id);
        return customer;
    }

    @Override
    public CustomerDTO updateCustomer(UUID id, CustomerDTO customer) {
        log.debug("Updated customer [{}]", id);
        return customer;
    }

    @Override
    public CustomerDTO deleteCustomerById(UUID id, CustomerDTO customer) {
        log.debug("Deleted customer [{}]", id);
        return customer;
    }
}
