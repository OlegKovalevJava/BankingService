package com.banking_service.controller;

import com.banking_service.entity.TransferBalance;
import com.banking_service.service.BankService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BankService bankService;

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) {
        return bankService.getBalance(accountId);
    }

    @PostMapping("/putMoney")
    public BigDecimal putMoney(@RequestBody TransferBalance transferBalance) {
        return bankService.putMoney(transferBalance.getRecipientId(), transferBalance.getAmount());
    }

    @PostMapping("/takeMoney")
    public BigDecimal takeMoney(@RequestBody TransferBalance transferBalance) {
        return bankService.takeMoney(transferBalance.getRecipientId(), transferBalance.getAmount());
    }

    @PostMapping("/makeTransfer")
    public void transfer(@RequestBody TransferBalance transferBalance) {
        bankService.transferMoney(transferBalance);
    }
}