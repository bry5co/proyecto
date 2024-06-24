package com.codeElevate.ServiceBookingSystem.dto.finance;

import com.codeElevate.ServiceBookingSystem.entity.finance.Expense;
import com.codeElevate.ServiceBookingSystem.entity.finance.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {

    private List<Expense> expenseList;

    private List<Income> incomeList;

}
