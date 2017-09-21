package springbootapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootapi.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
