package com.codeElevate.ServiceBookingSystem.dto.finance;

import lombok.Data;

@Data
public class StatsDTO {

    private Double balance;

    private Double minIncome;

    private Double maxIncome;

    private Double minExpense;

    private Double maxExpense;

    private Double income;

    private Double expense;

    private IncomeDTO incomeDTO;

    private ExpenseDTO expenseDTO;

}
