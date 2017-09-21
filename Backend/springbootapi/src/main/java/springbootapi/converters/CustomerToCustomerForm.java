package springbootapi.converters;

import springbootapi.commands.CustomerForm;
import springbootapi.domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {
    @Override
    public CustomerForm convert(Customer customer) {
    	CustomerForm customerForm = new CustomerForm();
    	customerForm.setId(customer.getId());
    	customerForm.setName(customer.getName());
    	customerForm.setPhone(customer.getPhone());
        return customerForm;
    }
}