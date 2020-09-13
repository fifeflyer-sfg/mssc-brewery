package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping({"/{id}"})
    public CustomerDTO getCustomer(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customer) {
        return customerService.createCustomer(id, customer);
    }

    @PutMapping({"/{id}"})
    public CustomerDTO updateCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping({"/{id}"})
    public CustomerDTO deleteCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customer) {
        return customerService.deleteCustomerById(id, customer);
    }
}
