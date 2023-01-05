package com.banking_service.service;

import com.banking_service.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankService {

    private final ClientRepository clientRepository;
}
