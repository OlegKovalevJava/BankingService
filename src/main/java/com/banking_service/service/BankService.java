package com.banking_service.service;

import com.banking_service.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {

    private final ClientRepository clientRepository;

    public BigDecimal getBalance(Long accountID) {
        BigDecimal balance = clientRepository.getBalanceForId(accountID);
        if (balance == null) {
            throw new IllegalArgumentException();
        }
        return balance;
    }


}
