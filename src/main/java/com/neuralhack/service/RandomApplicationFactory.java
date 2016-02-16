package com.neuralhack.service;

import com.neuralhack.domain.BusinessStructure;
import com.neuralhack.domain.LoanApplicationInfo;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Gonzalo on 2/14/2016.
 */
public class RandomApplicationFactory {

    public static LoanApplicationInfo create(){

        LoanApplicationInfo info = new LoanApplicationInfo();

        int intStructure = new Random().nextInt(3) + 1;
        info.setBusinessStructure(BusinessStructure.getEnum(intStructure));

        int intAmount = new Random().nextInt(295_000) + 5_000 ;
        info.setLoanAmountRequested(BigDecimal.valueOf(intAmount));


        int yearsInBusiness = new Random().nextInt(20);
        info.setYearsInBusiness(yearsInBusiness);


        int numberOfEmployees = new Random().nextInt(20);
        info.setNumberOfemployees(numberOfEmployees);

        int grossReceipts = new Random().nextInt(1_100_000);
        info.setGrossReceipts(BigDecimal.valueOf(grossReceipts));

        int expenses = new Random().nextInt(480_000);
        info.setExpenses(BigDecimal.valueOf(expenses));

        return info;

    }

}
