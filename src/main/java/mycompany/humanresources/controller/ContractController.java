package mycompany.humanresources.controller;


import lombok.RequiredArgsConstructor;
import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/contracts/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable UUID id) {
        return ResponseEntity.of(contractService.getContract(id));
    }

    @GetMapping("/contracts")
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.findAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @PostMapping("/contracts")
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        contractService.saveContract(contract);
        return ResponseEntity.status(HttpStatus.CREATED).body(contract);
    }

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable  UUID id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @PutMapping("/contracts/{id}")
    public ResponseEntity<Contract> updatedContract(
            @PathVariable  UUID id,
            @RequestBody Contract updatedContract) {
        updatedContract.setId(id);
        contractService.saveContract(updatedContract);
        return ResponseEntity.ok(updatedContract);
    }

}
