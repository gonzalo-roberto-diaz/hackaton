package com.neuralhack.service;

import com.neuralhack.domain.BusinessStructure;
import com.neuralhack.domain.LoanApplicationInfo;
import com.neuralhack.domain.LogRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Gonzalo on 2/15/2016.
 */
public class Converter {

    public static LogRecord loanAppToLogRecord(LoanApplicationInfo loanApp){
        LogRecord logRecord = new LogRecord();
        logRecord.setCcorp(loanApp.getBusinessStructure()== BusinessStructure.CCORP);
        logRecord.setLlc(loanApp.getBusinessStructure()== BusinessStructure.LLC);
        logRecord.setSoleProprietor(loanApp.getBusinessStructure()== BusinessStructure.SOLE_PROPIETORSHIP);
        logRecord.setProfitRatio(loanApp.getGrossReceipts().setScale(2) .divide(loanApp.getExpenses().setScale(2), RoundingMode.HALF_EVEN).doubleValue());
        double profitRatio =  loanApp.getGrossReceipts().setScale(2) .divide(loanApp.getExpenses().setScale(2), RoundingMode.HALF_EVEN).doubleValue();
        logRecord.setProfitRatio(Math.min(5.0, profitRatio));


        BigDecimal profit = loanApp.getGrossReceipts().subtract(loanApp.getExpenses());
        boolean asksMoreThanYearEarnings = loanApp.getLoanAmountRequested().compareTo(profit)>0;
        logRecord.setLoanAmountMoreThanYearlyEarnings(asksMoreThanYearEarnings);

        logRecord.setYearsInBusiness(loanApp.getYearsInBusiness());

        double ratioLoanEmployeeProductivity = 1.00;
        if (profitRatio < 1.00){
            ratioLoanEmployeeProductivity = 6.00;  //i.e, more than 3, otherwise it would be negative
        }else {
            if (loanApp.getNumberOfemployees() > 0) {
                BigDecimal employeeProductivity = profit.setScale(2).divide(BigDecimal.valueOf(loanApp.getNumberOfemployees()).setScale(2), RoundingMode.HALF_EVEN);
                ratioLoanEmployeeProductivity = loanApp.getLoanAmountRequested().divide(employeeProductivity, RoundingMode.HALF_EVEN).doubleValue();
            }
            ratioLoanEmployeeProductivity = Math.min(6.00, ratioLoanEmployeeProductivity);
        }
        logRecord.setLoanEmployeeProductivityRatio(ratioLoanEmployeeProductivity);


        return logRecord;
    }

}
