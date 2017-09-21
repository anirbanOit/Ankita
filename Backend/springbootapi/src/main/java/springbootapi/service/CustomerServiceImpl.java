package springbootapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootapi.commands.CustomerForm;
import springbootapi.converters.CustomerFormToCustomer;
import springbootapi.domain.Customer;
import springbootapi.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepo customerRepo;
	private CustomerFormToCustomer customerFormToCustomer;

	@Autowired
	public CustomerServiceImpl(CustomerRepo customerRepo, CustomerFormToCustomer customerFormToCustomer) {
		super();
		this.customerRepo = customerRepo;
		this.customerFormToCustomer = customerFormToCustomer;
	}
	
	@Override
	public List<Customer> listAll() {
		// TODO Auto-generated method stub
		List<Customer> customer = new ArrayList<Customer>();
		customerRepo.findAll().forEach(customer::add);
		return customer;
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		return customerRepo.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.save(customer);
		return customer;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		customerRepo.delete(id);
	}

	@Override
	public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
		// TODO Auto-generated method stub
		Customer savedCustomer = saveOrUpdate(customerFormToCustomer.convert(customerForm));
		System.out.println("Saved Customer Id : " + savedCustomer.getId());
		return savedCustomer;
	}
	
}
