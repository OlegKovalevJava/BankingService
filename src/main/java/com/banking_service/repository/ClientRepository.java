package com.banking_service.repository;

import com.banking_service.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface ClientRepository extends CrudRepository<Client, Long> {

    BigDecimal getBalance(Long accountId);

    void save(Long accountId, BigDecimal amount);

}