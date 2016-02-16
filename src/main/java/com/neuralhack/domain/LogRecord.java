package com.neuralhack.domain;

/**
 * Created by Gonzalo on 2/15/2016.
 */
public class LogRecord {

    boolean soleProprietor;
    boolean llc;
    boolean ccorp;
    double profitRatio;
    boolean loanAmountMoreThanYearlyEarnings;
    boolean approved;
    int yearsInBusiness;
    double loanEmployeeProductivityRatio;

    public double getLoanEmployeeProductivityRatio() {
        return loanEmployeeProductivityRatio;
    }

    public void setLoanEmployeeProductivityRatio(double loanEmployeeProductivityRatio) {
        this.loanEmployeeProductivityRatio = loanEmployeeProductivityRatio;
    }



    public int getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(int yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }



    public boolean isSoleProprietor() {
        return soleProprietor;
    }

    public void setSoleProprietor(boolean soleProprietor) {
        this.soleProprietor = soleProprietor;
    }

    public boolean isLlc() {
        return llc;
    }

    public void setLlc(boolean llc) {
        this.llc = llc;
    }

    public boolean isCcorp() {
        return ccorp;
    }

    public void setCcorp(boolean ccorp) {
        this.ccorp = ccorp;
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(double profitable) {
        this.profitRatio = profitable;
    }

    public boolean isLoanAmountMoreThanYearlyEarnings() {
        return loanAmountMoreThanYearlyEarnings;
    }

    public void setLoanAmountMoreThanYearlyEarnings(boolean loanAmountMoreThanYearlyEarnings) {
        this.loanAmountMoreThanYearlyEarnings = loanAmountMoreThanYearlyEarnings;
    }



    @Override
    public String toString() {
        return "LogRecord{" +
                "soleProprietor=" + soleProprietor +
                ", llc=" + llc +
                ", ccorp=" + ccorp +
                ", profitRatio=" + profitRatio +
                ", loanAmountMoreThanYearlyEarnings=" + loanAmountMoreThanYearlyEarnings +
                ", yearsInBusiness=" + yearsInBusiness +
                ", loanEmployeeProductivityRatio=" + loanEmployeeProductivityRatio +
                ", approved=" + approved +
                '}';
    }

    public boolean equals(Object obj){
        if (obj instanceof LogRecord){
            LogRecord param = (LogRecord)obj;
            return param.toString().equals(this.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }


    public String toCSVString(){
        StringBuilder sb = new StringBuilder();
        sb.append(soleProprietor?"1":"0");
        sb.append(",").append(llc?"1":"0");
        sb.append(",").append(ccorp?"1":"0");
        sb.append(",").append(profitRatio);
        sb.append(",").append(loanAmountMoreThanYearlyEarnings?"1":"0");
        sb.append(",").append(yearsInBusiness);
        sb.append(",").append(loanEmployeeProductivityRatio);
        sb.append(",").append(approved?"1":"0");
        return sb.toString();

    }


}
