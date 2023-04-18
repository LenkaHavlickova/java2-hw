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

}
