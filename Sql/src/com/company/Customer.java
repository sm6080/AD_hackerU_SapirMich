package com.company;

/**
 * Created by hackeru on 05.04.2017.
 */
public class Customer {

    private float  totalOrderSum;
    private String companyName;


    public Customer(){

    }

    public Customer( String companyName) {
        this.companyName=companyName;
    }

    public float getTotalOrderSum() {
        return totalOrderSum;
    }

    public void setTotalOrderSum(int totalOrderSum) {
        this.totalOrderSum = totalOrderSum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
