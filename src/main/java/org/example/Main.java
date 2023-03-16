package org.example;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws Exception {

        Calculations calculations = new Calculations();
        String[] mass =  calculations.availablePeriods(new LocalTime[]{LocalTime.of(13, 0, 0)}, new int[]{55}, LocalTime.of(12, 0, 0), LocalTime.of(18, 0, 0), 30 );
        for (String record : mass){
            System.out.println(record);
        }
    }
}