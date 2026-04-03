# Lab 3 — Geospatial Track Data Analyzer
**COSC 310 | Bike Ride Data Analysis**

---

## The Dataset

In these four JSON files Dr. Toone has recorded his biking data collected over a four day span. In total there are 223,983 records. These records contain data on timestamp, distance, heart rate, speed, altitude, latitude, longitude, power output, cadence, temperature, and radar vehicle data.

---

## Problem 1: What was the rider's peak power moment of the week?

### Problem Definition
My goal for this question was to find the single moment where Dr. Toone performed his best across the entire week. As a well seasoned cyclist, I was curious about when he hit his peak output, specifically his maximum power and what his speed looked like at that moment. Power (watts) is the most direct measure of effort on a bike, so sorting by power and grabbing the top record would give me a clear answer.

### Algorithm Choice — Merge Sort
**Algorithm:** Merge Sort (`Sorting.mergeSort`)
**Sort criteria:** `sortCriteria = 7` (power in watts)
**Time complexity:** O(n log n)
**Space complexity:** O(n)

Both algorithms sort the data, but merge sort is far faster. Merge sort begins by repeatedly cutting the data in half to reach the desired target. Bubble sort compares every record to every other record, which is an expensive process. With 224,000 records, merge sort performs roughly 3.8 million comparisons versus bubble sort's 50 billion — making merge sort the clear choice for a dataset this size. After sorting ascending by power, the peak record sits at the last index (`size() - 1`).

### Results

```
Peak power record:
BikeDataRecord [timestamp=1142010515, distance=282985.34, heartrate=107,
speed=10.628, alt=15.8, lat=39.53334, lng=-76.1203, pow=705, cad=97, degC=25.0]

Power (watts): 705
Speed (m/s):   10.628  (~38 kph / 23.5 mph)
Cadence (rpm): 97
Heart rate:    107 bpm
Timestamp:     1142010515

Top 5 highest power records:
  Power=705W  Speed=10.628m/s  Cadence=97rpm   HR=107bpm
  Power=697W  Speed=7.147m/s   Cadence=83rpm   HR=113bpm
  Power=685W  Speed=9.965m/s   Cadence=91rpm   HR=107bpm
  Power=663W  Speed=10.758m/s  Cadence=102rpm  HR=107bpm
  Power=654W  Speed=5.384m/s   Cadence=82rpm   HR=89bpm
```

### Interpretation
At the peak power moment, Dr. Toone achieved a speed of 38 kph, producing 705 watts at a cadence of 97 rpm. These results illustrate that a sprint was performed rather than a climb. It should be noted that the heart rate was rather low at 107 bpm for such high output. This is because the cardiovascular system lags when a sudden burst of exercise happens, the heart simply had not caught up to the effort yet.

---

## Problem 2: When did the rider show signs of fatigue?

### Problem Definition
For question 2, I was interested in finding when Dr. Toone might have gotten tired during the week. I researched what the data would look like to indicate fatigue and found that it typically shows up as a slowing speed combined with a rising heart rate, the body working harder while losing pace. Just like question 1, I wanted to pinpoint the single strongest moment where the cyclist experienced fatigue across all four days.

### Algorithm Choice — Merge Sort + Binary Search
**Algorithms:** Merge Sort (`Sorting.mergeSort`) + Binary Search (`Searching.binarySearch`)
**Time complexity:** O(n log n) for sort, O(log n) for binary search
**Space complexity:** O(n)

To answer this question, merge sort and binary search were both used. Merge sort was used first to put all records in chronological order so the fatigue pattern could be scanned sequentially. Binary search was then used to find where heart rate crossed a threshold of 150 bpm. Binary search is faster than looping through each and every record because of its ability to split the data in half over and over again to find the target — only needing about 17 jumps through 224,000 records rather than checking every single one. Finally, a sequential scan compared each pinpointed record to its neighboring records to identify the fatigue pattern of dropping speed and rising heart rate.

### Results

```
Records with HR >= 150 bpm: 3
Total fatigue signal moments found: 260

Strongest fatigue moment:
  Before -> Speed=5.542m/s  HR=105bpm
  After  -> Speed=3.256m/s  HR=107bpm
  Speed drop: 2.286 m/s  (~8.2 kph drop)
  Timestamp: 1142076965
```

### Interpretation
From the results we can tell that Dr. Toone was able to keep a steady pace and largely avoid fatigue throughout the week. Only 3 records crossed the 150 bpm heart rate threshold across all 223,983 records, which tells us he stayed in a comfortable endurance zone the entire time. Since so few records display fatigue characteristics, it is possible that something else caused the speed drops. The strongest fatigue signal showed a speed drop of 2.286 m/s with only a 2 bpm heart rate rise, such a small HR increase suggests the slowdown was more likely caused by an external factor like a stop sign or traffic rather than the rider actually getting tired.

---

## Algorithm Complexity Summary

| Algorithm | Use | Time Complexity | Space Complexity |
|---|---|---|---|
| Merge Sort | Sort 223,983 records by power (Q1) | O(n log n) | O(n) |
| Merge Sort | Sort 223,983 records by timestamp (Q2) | O(n log n) | O(n) |
| Binary Search | Find HR >= 150 bpm threshold (Q2) | O(log n) | O(1) |

Compared to a naive approach, this solution reduces the search phase from O(n) repeated queries to a single O(log n) lookup after an O(n log n) sort — a significant improvement at this dataset size.