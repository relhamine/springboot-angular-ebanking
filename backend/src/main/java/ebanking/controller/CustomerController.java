package ebanking.controller;

import ebanking.dtos.CustomerDTO;
import ebanking.exceptions.CustomerNotFoundException;
import ebanking.services.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Tag(name = "Customer", description = "The Customer API. Contains operations like get, add, list ...")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> customers() {
        return new ResponseEntity<>(customerService.listCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return new ResponseEntity<>(customerService.searchCustomers("%" + keyword + "%"), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerResponse) {
        return new ResponseEntity<>(customerService.saveCustomer(customerResponse), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerResponse) {
        //customerDTO.setId(customerId);
        return new ResponseEntity<>(customerService.updateCustomer(customerResponse), HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
