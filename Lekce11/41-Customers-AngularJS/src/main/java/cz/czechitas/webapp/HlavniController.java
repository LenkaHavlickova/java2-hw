package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HlavniController {

    private CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping("/services/customers/all")
    public @ResponseBody List<Customer> zobrazZakazniky() throws Exception {
        Thread.sleep(1500L);
        List<Customer> zakaznici = customerRepository.findAll();
        return zakaznici;
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/services/customers/{id:[0-9]+}", method = RequestMethod.GET)
    public @ResponseBody Customer findCustomer(@PathVariable("id") Long id) {
        return customerRepository.findOne(id);
    }

    @RequestMapping(value = "/services/customers/{id:[0-9]+}", method = RequestMethod.PUT)
    public @ResponseBody Customer updateCustomer(
            @PathVariable("id") Long id, @RequestBody Customer customer) {
        Customer changedCustomer = customerRepository.save(customer);
        return changedCustomer;
    }

    @RequestMapping(value = "/services/customers/new", method = RequestMethod.POST)
    public @ResponseBody Customer addCustomer(
            @RequestBody Customer customer) {
        Customer addedCustomer = customerRepository.save(customer);
        return addedCustomer;
    }

}
