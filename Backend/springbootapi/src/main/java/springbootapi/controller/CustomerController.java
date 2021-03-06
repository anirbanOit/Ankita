package springbootapi.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springbootapi.commands.CustomerForm;
import springbootapi.domain.Customer;

import springbootapi.converters.CustomerToCustomerForm;
import springbootapi.service.CustomerService;

@Controller
//@RequestMapping("/customer")
public class CustomerController {
	
	private CustomerService customerService;
	private CustomerToCustomerForm customerToCustomerForm;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setCustomerToCustomerForm(CustomerToCustomerForm customerToCustomerForm) {
		this.customerToCustomerForm = customerToCustomerForm;
	}
	
	@RequestMapping("/")
	public String redirToList() { return "redirect:/customer/list"; }
	
	@RequestMapping({"/customer/list", "/customer"})
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.listAll());
		return "customer/list";
	}

	@RequestMapping("/customer/show/{id}")
	public String getCustomer(@PathVariable String id, Model model) {
		model.addAttribute("customers", customerService.getById(Integer.valueOf(id)));
		return "customer/show";
	}
	
	@RequestMapping("/customer/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		Customer customer = customerService.getById(Integer.valueOf(id));
		CustomerForm customerForm = customerToCustomerForm.convert(customer);
		model.addAttribute("customerForm", customerForm);
		return "customer/customerform";
	}
	
	@RequestMapping("/customer/new")
	public String newCustomer(Model model) {
		model.addAttribute("customerForm", new CustomerForm());
		return "customer/customerform";
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public String saveOrUpdateCustomer(@Valid CustomerForm customerForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "customer/customerform";
		}
		Customer savedCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
		return "redirect:/customer/show/" + savedCustomer.getId();
	}
	
	@RequestMapping("/customer/delete/{id}")
	public String delete(@PathVariable String id) {
		customerService.delete(Integer.valueOf(id));
		return "redirect:/customer/list";
	}
	
	/*@Autowired
	CustomerRepo rp;
	
	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> findall() {
		return rp.findAll();
	}*/
}
