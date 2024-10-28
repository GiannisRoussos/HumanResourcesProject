package mycompany.humanresources.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public void saveContract(Contract contract) {
        log.debug("#saveContract: contract={}", contract);
        contractRepository.save(contract);
    }

    public Optional<Contract> getContract(UUID id) {
        log.debug("#getContract: id={}", id);
        return contractRepository.findById(id);
    }

    public List<Contract> findAllContracts() {
        log.debug("#findAllContracts");
        return contractRepository.findAll();
    }

    public void deleteContract(UUID id) {
        log.debug("#deleteContract: id={}", id);
        contractRepository.deleteById(id);
    }

}
