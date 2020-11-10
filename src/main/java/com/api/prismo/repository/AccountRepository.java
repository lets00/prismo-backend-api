package com.api.prismo.repository;

import com.api.prismo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByDocumentNumber(String documentNumber);
    Account findById(long id);

}
