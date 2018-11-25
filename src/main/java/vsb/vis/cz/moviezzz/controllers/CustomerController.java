package vsb.vis.cz.moviezzz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vsb.vis.cz.moviezzz.mappers.CustomerMapper;
import vsb.vis.cz.moviezzz.models.Customer;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerMapper.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveCustomer(@RequestParam Customer customer) {
        customerMapper.insert(customer);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateCustomer(@RequestParam Customer customer) {
        customerMapper.update(customer);
    }
}
