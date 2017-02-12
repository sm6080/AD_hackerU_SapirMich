package com.company;

import java.security.InvalidParameterException;

/**
 * Created by Sapir Michaeli on 06.02.2017.
 */
public class RationalNumber extends Number {
    int numerator;
    int denominator;

    public RationalNumber(){
        this(0,1);
    }
    public RationalNumber(int numerator){
        this(numerator,1);
    }

    public RationalNumber(int numerator, int denominator){
        if(numerator==0)
            throw new InvalidParameterException("division by zero");
        this.numerator=numerator;
        this.denominator=denominator;
    }

    @Override
    public int intValue() {
        return numerator/denominator;
    }

    @Override
    public long longValue() {
        return intValue();
    }

    @Override
    public float floatValue() {
        /*float n=numerator;
        float d=denominator;
        return n/d;*/
        return  (float)numerator/denominator;
    }

    @Override
    public double doubleValue() {
        return (double) numerator/denominator;
    }

    public void add(RationalNumber num){
        //this.denominator=15    num.denominator=10
        //gcd=5
        //a=15/5=3
        //b=10/5=2
        int gcd=gcd(this.numerator,this.denominator);
        int a=this.denominator/gcd;
        int b=num.denominator/gcd;
        this.denominator*=b;
        this.numerator*=b;
        this.numerator+=num.numerator*a; // final numerator
        reduce();
    }
    public void reduce(){
        int gcd=gcd(this.numerator,this.denominator);
        this.denominator/=gcd;
        this.numerator/=gcd;
    }

    public static int gcd(int x, int y){
        if(x==0)
            return y;
        return gcd(y%x,x);
    }
}
