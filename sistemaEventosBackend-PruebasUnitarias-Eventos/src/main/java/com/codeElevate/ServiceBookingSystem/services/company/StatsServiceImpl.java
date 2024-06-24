package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.finance.ExpenseDTO;
import com.codeElevate.ServiceBookingSystem.dto.finance.GraphDTO;
import com.codeElevate.ServiceBookingSystem.dto.finance.IncomeDTO;
import com.codeElevate.ServiceBookingSystem.dto.finance.StatsDTO;
import com.codeElevate.ServiceBookingSystem.entity.finance.Expense;
import com.codeElevate.ServiceBookingSystem.entity.finance.Income;
import com.codeElevate.ServiceBookingSystem.repository.finance.ExpenseRepository;
import com.codeElevate.ServiceBookingSystem.repository.finance.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    @Override
    public StatsDTO getStats() {
        double totalIncome = incomeRepository.sumAllAmounts();
        double totalExpense = expenseRepository.sumAllAmounts();

        // Retrieve all incomes and expenses
        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        // Calculate min and max income
        OptionalDouble minIncome = incomes.stream()
                .mapToDouble(Income::getAmount)
                .min();
        OptionalDouble maxIncome = incomes.stream()
                .mapToDouble(Income::getAmount)
                .max();

        // Calculate min and max expense
        OptionalDouble minExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .min();
        OptionalDouble maxExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .max();

        // Retrieve the latest income and expense records
        Optional<Income> latestIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> latestExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setBalance(totalIncome-totalExpense);
        statsDTO.setIncome(totalIncome);
        statsDTO.setExpense(totalExpense);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);

        // Set the latest income if present
        latestIncome.ifPresent(income -> {
            IncomeDTO incomeDTO = income.getIncomeDto();
            statsDTO.setIncomeDTO(incomeDTO);
        });

        // Set the latest expense if present
        latestExpense.ifPresent(expense -> {
            ExpenseDTO expenseDTO = expense.getExpenseDTO();
            statsDTO.setExpenseDTO(expenseDTO);
        });

        return statsDTO;
    }

    @Override
    public GraphDTO getChartData() {

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);
        List<Income> incomes = incomeRepository.findByDateBetween(startDate, endDate);

//        List<ExpenseDTO> expenseDTOList = expenses.stream().map(Expense::getExpenseDTO).collect(Collectors.toList());
//        List<IncomeDTO> incomeDTOList = incomes.stream().map(Income::getIncomeDto).collect(Collectors.toList());

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenses);
        graphDTO.setIncomeList(incomes);

        return graphDTO;
    }
}
