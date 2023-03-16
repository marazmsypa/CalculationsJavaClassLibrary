package org.example;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculations {
    public String[] availablePeriods(LocalTime[] startTimes,
                                     int[] durations,
                                     LocalTime beginWorkingTime,
                                     LocalTime endWorkingTime,
                                     int consultationTime
    ) throws Exception {
        if (consultationTime == 0) {
            throw new Exception("Вы неправильно ввели длительность приема");
        }
        if (durations.length != startTimes.length) {
            throw new Exception("Вы неправильно указали готовые приемы врачей");
        }

        if (beginWorkingTime == LocalTime.of(0, 0, 0)) {
            throw new Exception("Вы неправильно указали начало работы специалиста");
        }
        if (endWorkingTime == LocalTime.of(0, 0, 0)) {
            throw new Exception("Вы неправильно указали начало работы специалиста");
        }
        if (endWorkingTime.getHour() - beginWorkingTime.getHour() < 1) {
            throw new Exception("Специалист работает менее 1 часа");
        }

        List<String> mass = new ArrayList<>();
        while (beginWorkingTime.isBefore(endWorkingTime)) {

            if (startTimes.length > 0) {
                boolean hasABreak = false;
                for (int i = 0; i < startTimes.length; i++) {
                    if (startTimes[i].equals(beginWorkingTime) || (startTimes[i].isBefore(beginWorkingTime.plusMinutes(consultationTime)) && startTimes[i].isAfter(beginWorkingTime))) {
                        beginWorkingTime = startTimes[i].plusMinutes(durations[i]);
                        hasABreak = true;
                        break;
                    }
                }
                if (hasABreak) continue;
            }
            StringBuilder builder = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            builder.append(beginWorkingTime.format(formatter));
            builder.append("-");
            beginWorkingTime = beginWorkingTime.plusMinutes(consultationTime);
            builder.append(beginWorkingTime.format(formatter));
            mass.add(builder.toString());
        }
        return mass.toArray(String[]::new);
    }
}
