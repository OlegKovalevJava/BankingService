package com.banking_service.repository;

import com.banking_service.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    default void getBalanceForId(Long accountId) {

    }

}