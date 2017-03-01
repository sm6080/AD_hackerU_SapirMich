package com.company;

/**
 * Created by Sapir Michaeli on 26.02.2017.
 */
public class Station {
    private int charge;
    private int distanceToNext;

    public Station(int charge, int distance) {
        this.charge = charge;
        this.distanceToNext = distance;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getDistance() {
        return distanceToNext;
    }

    public void setDistance(int distance) {
        this.distanceToNext = distance;
    }


}
