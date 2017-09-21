package restcontrollerapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restcontrollerapi.domain.ClientDTO;
import restcontrollerapi.repo.ClientRepo;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientRepo cr;

    public ClientController(ClientRepo cr) {
        this.cr = cr;
    }

    @GetMapping
    public List<ClientDTO> getExams() {
        return cr.findAll();
    }

    @PostMapping
    public void newExam(@DTO(ClientDTO.class) ClientDTO client) {
        cr.saveAndFlush(client);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editExam(@DTO(ClientDTO.class) ClientDTO client) {
    	cr.saveAndFlush(client);
    }
}