package restcontrollerapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import restcontrollerapi.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	List<Customer> findByNameOrPhone(String srch, String srch2);

}