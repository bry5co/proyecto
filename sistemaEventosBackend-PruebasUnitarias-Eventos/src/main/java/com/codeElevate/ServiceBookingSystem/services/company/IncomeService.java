package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.finance.IncomeDTO;
import com.codeElevate.ServiceBookingSystem.entity.finance.Income;

import java.util.List;

public interface IncomeService {


    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncomes();

    IncomeDTO getIncomeById(Long id);

    Income updateIncome(Long id, IncomeDTO incomeDTO);

    void deleteIncome(Long id);
}
