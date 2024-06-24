package com.codeElevate.ServiceBookingSystem.repository.finance;

import com.codeElevate.ServiceBookingSystem.entity.finance.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT SUM(i.amount) FROM Income i")
    Double sumAllAmounts();

    List<Income> findByDateBetween(LocalDate firstDateOfMonth, LocalDate lastDateOfMonth);

    Optional<Income> findFirstByOrderByDateDesc();
}
