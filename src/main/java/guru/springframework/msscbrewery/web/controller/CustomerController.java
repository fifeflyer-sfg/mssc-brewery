package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

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
    public ResponseEntity<CustomerDTO> createCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "http://localhost:8080/api/v1/customer/" + id);
        return new ResponseEntity<>(customerService.createCustomer(id, customer), httpHeaders, CREATED);
    }

    @PutMapping({"/{id}"})
    public void updateCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customer) {
        customerService.updateCustomer(id, customer);
    }

    @DeleteMapping({"/{id}"})
    public void deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomerById(id);
    }
}
