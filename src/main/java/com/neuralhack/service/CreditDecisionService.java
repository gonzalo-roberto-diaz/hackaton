package com.neuralhack.service;

import com.neuralhack.domain.LoanApplicationInfo;
import com.neuralhack.domain.BusinessStructure;
import com.neuralhack.domain.LogRecord;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Gonzalo on 2/14/2016.
 */
public class CreditDecisionService {

    Logger logger = Logger.getLogger(CreditDecisionService.class);




    private static BigDecimal minimum = BigDecimal.valueOf(5_000.00);
    private static BigDecimal maximum = BigDecimal.valueOf(300_000.00);

    public boolean decide(LoanApplicationInfo loanApplicationInfo){

        LogRecord logRecord = Converter.loanAppToLogRecord(loanApplicationInfo);
        if (logRecord.isLlc()  && logRecord.getYearsInBusiness() < 5){
            logger.info("1--- rejected because I don't line new Sole Propietorships");
            return false;
        }

        if (logRecord.getProfitRatio()<1){
            logger.info("2--- rejected because the business is not profitable");
            return false;
        }


        if (logRecord.isLoanAmountMoreThanYearlyEarnings()){
            logger.info("3--- rejected because the loan amount " + loanApplicationInfo.getLoanAmountRequested()  + " is more than the yearly earnings " + loanApplicationInfo.getGrossReceipts().subtract(loanApplicationInfo.getExpenses()));
            return false;
        }

        if (logRecord.getLoanEmployeeProductivityRatio() > 6.00){
            logger.info("4--- rejected because the employee productivity is too low in relation to the loan amount requested" );
            return false;
        }

        logger.info("ACCEPTED ratio=" + logRecord.getLoanEmployeeProductivityRatio());
        return true;

    }
}
