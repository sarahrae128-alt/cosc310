package chapter14;

import java.util.ArrayList;
import java.util.List;

import json.*;

public class Lab3Personal {
    public static void main(String[] args) throws Exception {

        // Load all four days of data into one combined list
        ArrayList<BikeDataRecord> records = new ArrayList<>();
        records.addAll(BikeDataReader.parse("json/day1.json"));
        records.addAll(BikeDataReader.parse("json/day2.json"));
        records.addAll(BikeDataReader.parse("json/day3.json"));
        records.addAll(BikeDataReader.parse("json/day4.json"));
        System.out.println("Total records loaded: " + records.size());

        // Q1: What was the rider's peak power moment of the week?
        // Approach: mergeSort by power (index 7), grab index 0
        System.out.println("\n Peak Power Moment");

        BikeDataRecord.sortCriteria = 7; // sort by power (watts)
        List<BikeDataRecord> sortedByPower = Sorting.mergeSort(records);

        BikeDataRecord peakPower = sortedByPower.get(sortedByPower.size() - 1); // highest power is at the end (ascending)
        System.out.println("Peak power record: " + peakPower);
        System.out.println("Power (watts): "    + peakPower.getPow());
        System.out.println("Speed (m/s): "      + peakPower.getSpeed());
        System.out.println("Cadence (rpm): "    + peakPower.getCad());
        System.out.println("Heart rate (bpm): " + peakPower.getHeartrate());
        System.out.println("Timestamp: "        + peakPower.getTimestamp());

        // Also show top 5 for context
        System.out.println("\n Top 5 highest power records");
        int total = sortedByPower.size();
        for (int i = total - 1; i >= total - 5; i--) {
            BikeDataRecord r = sortedByPower.get(i);
            System.out.println("  Power=" + r.getPow() + "W  Speed=" + r.getSpeed() + "m/s  Cadence=" + r.getCad() + "rpm  HR=" + r.getHeartrate() + "bpm");
        }

        // Q2: When did the rider show signs of fatigue?
        // Fatigue = heart rate rising while speed is dropping
        // Approach: mergeSort by timestamp (index 0) to get
        // chronological order, then binarySearch for a HR
        // threshold to find the fatigue window, then scan
        // adjacent records within that window for the pattern
    
        System.out.println("\n Q2: Fatigue Detection");

        BikeDataRecord.sortCriteria = 0; // sort by timestamp
        List<BikeDataRecord> sortedByTime = Sorting.mergeSort(records);

        // Step 1: binary search by HR threshold (index 2) to find
        // the first region where HR is elevated (>=150 bpm)
        // This tells us roughly when the rider was working hard
        int hrThreshold = 150;
        ArrayList<BikeDataRecord> highHRRecords = Searching.binarySearch(
            (ArrayList<BikeDataRecord>) Sorting.mergeSort(records), // sorted by HR for binary search
            hrThreshold, 2
        );
        // Note: the above mergeSort uses sortCriteria=0 still, so we need to re-sort by HR
        BikeDataRecord.sortCriteria = 2; // sort by heartrate
        List<BikeDataRecord> sortedByHR = Sorting.mergeSort(records);
        highHRRecords = Searching.binarySearch((ArrayList<BikeDataRecord>) sortedByHR, hrThreshold, 2);
        System.out.println("Records with HR >= " + hrThreshold + " bpm: " + highHRRecords.size());

        // Step 2: scan chronological records for the fatigue pattern
        // Look for a window where speed goes down AND HR goes up
        // compared to the previous record
        System.out.println("\nScanning for fatigue pattern (speed down + HR up)...");
        int fatigueCount = 0;
        int bestFatigueIdx = -1;
        float biggestSpeedDrop = 0;

        ArrayList<BikeDataRecord> timeList = (ArrayList<BikeDataRecord>) sortedByTime;
        for (int i = 1; i < timeList.size(); i++) {
            BikeDataRecord prev = timeList.get(i - 1);
            BikeDataRecord curr = timeList.get(i);

            float speedDrop = prev.getSpeed() - curr.getSpeed();
            int hrRise     = curr.getHeartrate() - prev.getHeartrate();

            // fatigue signal: speed dropped by more than 0.5 m/s AND HR rose by at least 2 bpm
            if (speedDrop > 0.5f && hrRise >= 2 && curr.getHeartrate() > 0) {
                fatigueCount++;
                if (speedDrop > biggestSpeedDrop) {
                    biggestSpeedDrop = speedDrop;
                    bestFatigueIdx = i;
                }
            }
        }

        System.out.println("Total fatigue signal moments found: " + fatigueCount);

        if (bestFatigueIdx != -1) {
            BikeDataRecord fatigueRecord = timeList.get(bestFatigueIdx);
            BikeDataRecord beforeFatigue = timeList.get(bestFatigueIdx - 1);
            System.out.println("\nStrongest fatigue moment:");
            System.out.println("  Before -> Speed=" + beforeFatigue.getSpeed() + "m/s  HR=" + beforeFatigue.getHeartrate() + "bpm");
            System.out.println("  After  -> Speed=" + fatigueRecord.getSpeed()  + "m/s  HR=" + fatigueRecord.getHeartrate()  + "bpm");
            System.out.println("  Speed drop: " + biggestSpeedDrop + " m/s");
            System.out.println("  Timestamp: " + fatigueRecord.getTimestamp());
        }
    }
}