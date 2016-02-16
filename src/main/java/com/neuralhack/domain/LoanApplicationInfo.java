package com.neuralhack.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Gonzalo on 2/14/2016.
 */
public class LoanApplicationInfo {

    private BusinessStructure businessStructure;
    private int yearsInBusiness;
    private int numberOfemployees;
    private BigDecimal expenses;
    private BigDecimal grossReceipts;
    private BigDecimal loanAmountRequested;

    public BigDecimal getLoanAmountRequested() {
        return loanAmountRequested;
    }

    public void setLoanAmountRequested(BigDecimal loanAmountRequestedInHundreds) {
        this.loanAmountRequested = loanAmountRequestedInHundreds;
    }



    public int getNumberOfemployees() {
        return numberOfemployees;
    }

    public void setNumberOfemployees(int numberOfemployees) {
        this.numberOfemployees = numberOfemployees;
    }

    public int getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(int yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }



    public BusinessStructure getBusinessStructure() {
        return businessStructure;
    }

    public void setBusinessStructure(BusinessStructure businessStructure) {
        this.businessStructure = businessStructure;
    }

    public BigDecimal getGrossReceipts() {
        return grossReceipts;
    }

    public void setGrossReceipts(BigDecimal grossReceipts) {
        this.grossReceipts = grossReceipts;
    }



    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Loan Application info = [ structure =" + getBusinessStructure());
        sb.append(" years=" + getYearsInBusiness());
        sb.append(" employees=" + getNumberOfemployees());
        sb.append(" grossReceipts=" + getGrossReceipts());
        sb.append(" expenses=" + getExpenses());
        sb.append(" loanAmount=" + getLoanAmountRequested());
        sb.append("]");
        return sb.toString();
    }

    public String toSimpleString(){
        StringBuilder sb = new StringBuilder("Loan Application info = [ structure =" + getBusinessStructure());
        sb.append(" profitable=" + (getGrossReceipts().compareTo(getExpenses())>0?"1": "0" ));
        sb.append("]");
        return sb.toString();
    }

    public String toSimpleCSVString(){
        StringBuilder sb = new StringBuilder();
        switch(getBusinessStructure()) {
            case SOLE_PROPIETORSHIP:
                sb.append("1,0,0");
                break;
            case LLC:
                sb.append("0,1,0");
                break;
            case CCORP:
                sb.append("0,0,1");
                break;
        }
        sb.append("," + (getGrossReceipts().compareTo(getExpenses())>0?"1": "0" ));
        return sb.toString();
    }

    public String toCSVString(){
        StringBuilder sb = new StringBuilder();
        switch(getBusinessStructure()) {
            case SOLE_PROPIETORSHIP:
                sb.append("1,0,0");
                break;
            case LLC:
                sb.append("0,1,0");
                break;
            case CCORP:
                sb.append("0,0,1");
                break;
        }

        BigDecimal earningsRatio = getGrossReceipts().divide(getExpenses(), RoundingMode.CEILING);
        BigDecimal earningsLoanRatio = earningsRatio.divide(getLoanAmountRequested(), RoundingMode.CEILING);
        //sb.append("," + getYearsInBusiness());
        //sb.append("," + getNumberOfemployees());
        //sb.append("," + earningsRatio );
        sb.append("," + earningsLoanRatio);
        return sb.toString();
    }


}
