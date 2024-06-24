package com.codeElevate.ServiceBookingSystem.repository.finance;

import com.codeElevate.ServiceBookingSystem.entity.finance.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllAmounts();

    List<Expense> findByDateBetween(LocalDate firstDateOfMonth, LocalDate lastDateOfMonth);

    Optional<Expense> findFirstByOrderByDateDesc();
}
