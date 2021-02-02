package com.rakeshv.ifinances.repositories;

import com.rakeshv.ifinances.models.Currency;
import com.rakeshv.ifinances.models.CurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, CurrencyId> {
}
