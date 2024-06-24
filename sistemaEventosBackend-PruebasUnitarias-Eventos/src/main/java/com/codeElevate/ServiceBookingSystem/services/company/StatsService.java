package com.codeElevate.ServiceBookingSystem.services.company;


import com.codeElevate.ServiceBookingSystem.dto.finance.GraphDTO;
import com.codeElevate.ServiceBookingSystem.dto.finance.StatsDTO;

public interface StatsService {
    StatsDTO getStats();

    GraphDTO getChartData();
}
