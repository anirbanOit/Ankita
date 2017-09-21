package restcontrollerapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import restcontrollerapi.domain.Customer;
import restcontrollerapi.domain.CustomerPatch;
import restcontrollerapi.repo.CustomerRepo;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerRepo rp;
	
	@RequestMapping("/findall")
    @ResponseBody
	public List<Customer> findall(){
		return rp.findAll();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Customer add(@RequestBody Customer customer) {
		Customer customer1 = new Customer();
		customer1.setName(customer.getName());
		customer1.setPhone(customer.getPhone());
	    return rp.saveAndFlush(customer1);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Customer findOne(@PathVariable Integer id) {
	    return rp.findOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Customer update(@PathVariable Integer id, @RequestBody Customer customer) {
		Customer customer1 = rp.findOne(id);
	    if (customer1 != null) {
	    	customer1.setName(customer.getName());
	    	customer1.setPhone(customer.getPhone());
	    	return rp.saveAndFlush(customer1);
	    }
	    return null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Customer patch(@PathVariable Integer id, @RequestBody CustomerPatch customerPatch) {
		Customer customer1 = rp.findOne(id);
		customer1.setName(customerPatch.getName());
        return rp.saveAndFlush(customer1);
    }
	  
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
	    rp.delete(id);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Customer> search(@RequestParam("search") final String srch){
			return rp.findByNameOrPhone(srch,srch);
	}
}