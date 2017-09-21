package restcontrollerapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import restcontrollerapi.domain.ClientDTO;

public interface ClientRepo extends JpaRepository<ClientDTO, Integer> {

}
