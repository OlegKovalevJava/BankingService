package com.banking_service.service;

import com.banking_service.entity.TransferBalance;
import com.banking_service.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {

    private final ClientRepository clientRepository;

    public BigDecimal getBalance(Long accountID) {
        BigDecimal balance = clientRepository.getBalance(accountID);
        if (balance == null) {
            throw new IllegalArgumentException();
        }
        return balance;
    }

    public BigDecimal putMoney(Long toIdBalance, BigDecimal amount) {
        BigDecimal currentBalance = clientRepository.getBalance(toIdBalance);
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
        BigDecimal currentBalance = clientRepository.getBalance(toIdBalance);
        if (currentBalance == null) {
            throw new IllegalArgumentException("This amount is not available, please repeat the operation");
        } else {
            BigDecimal updateBalance = currentBalance.subtract(amount);
            clientRepository.save(toIdBalance, updateBalance);
            return updateBalance;
        }
    }

    public void transferMoney(TransferBalance transferBalance) {
        BigDecimal senderBalance = clientRepository.getBalance(transferBalance.getSenderId());
        BigDecimal recipientBalance = clientRepository.getBalance(transferBalance.getRecipientId());

        if (senderBalance == null || recipientBalance == null)
            throw new IllegalArgumentException("Failed to complete the operation, check the details of the sender and recipient");
        if (transferBalance.getAmount().compareTo(senderBalance) > 0)
            throw new IllegalArgumentException("Not enough money in the account");

        BigDecimal updatedSenderBalance = senderBalance.subtract(transferBalance.getAmount());
        BigDecimal updatedRecipientBalance = recipientBalance.add(transferBalance.getAmount());

        clientRepository.save(transferBalance.getSenderId(), updatedSenderBalance);
        clientRepository.save(transferBalance.getRecipientId(), updatedRecipientBalance);
    }
}