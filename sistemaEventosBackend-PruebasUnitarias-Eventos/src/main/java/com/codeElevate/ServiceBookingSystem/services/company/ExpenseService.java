package com.codeElevate.ServiceBookingSystem.services.company;


import com.codeElevate.ServiceBookingSystem.dto.finance.ExpenseDTO;
import com.codeElevate.ServiceBookingSystem.entity.finance.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);
}
