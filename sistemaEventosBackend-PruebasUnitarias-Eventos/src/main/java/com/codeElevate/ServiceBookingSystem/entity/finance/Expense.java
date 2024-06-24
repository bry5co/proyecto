package com.codeElevate.ServiceBookingSystem.entity.finance;

import com.codeElevate.ServiceBookingSystem.dto.finance.ExpenseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private LocalDate date;

    private Integer amount;

    public ExpenseDTO getExpenseDTO() {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(id);
        expenseDTO.setTitle(title);
        expenseDTO.setAmount(amount);
        expenseDTO.setCategory(category);
        expenseDTO.setDescription(description);
        expenseDTO.setDate(date);
        return expenseDTO;
    }

}