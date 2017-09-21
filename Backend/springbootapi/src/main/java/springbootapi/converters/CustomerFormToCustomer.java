package springbootapi.converters;

import springbootapi.commands.CustomerForm;
import springbootapi.domain.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

    @Override
    public Customer convert(CustomerForm customerForm) {
    	Customer customer = new Customer();
        if (customerForm.getId() != null  && !StringUtils.isEmpty(customerForm.getId())) {
        	customer.setId(new Integer(customerForm.getId()));
        }
        customer.setName(customerForm.getName());
        customer.setPhone(customerForm.getPhone());
        return customer;
    }
}
