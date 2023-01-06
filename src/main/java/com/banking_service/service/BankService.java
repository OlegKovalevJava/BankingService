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

    public BigDecimal putMoney(Long toIdBalance, BigDecimal amount) {
        BigDecimal currentBalance = clientRepository.getBalanceForId(toIdBalance);
        if (currentBalance == null) {
            clientRepository.save(toIdBalance, amount);
            return amount;
        } else {
            BigDecimal updateBalance = currentBalance.add(amount);
            clientRepository.save(toIdBalance, updateBalance);
            return updateBalance;
        }
    }

    public BigDecimal takeMoney(Long toIdBalance, BigDecimal amount) {
        BigDecimal currentBalance = clientRepository.getBalanceForId(toIdBalance);
        if (currentBalance == null) {
            throw new IllegalArgumentException("this amount is not available, please repeat the operation");
        } else {
            BigDecimal updateBalance = currentBalance.subtract(amount);
            clientRepository.save(toIdBalance, updateBalance);
            return updateBalance;
        }
    }
}