package springbootapi.service;

import java.util.List;

import springbootapi.commands.CustomerForm;
import springbootapi.domain.Customer;

public interface CustomerService {
	List<Customer> listAll();
	Customer getById (Integer id);
	Customer saveOrUpdate (Customer customer);
	void delete (Integer id);
	Customer saveOrUpdateCustomerForm (CustomerForm customerform);
}