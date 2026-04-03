# Lab 3 — Geospatial Track Data Analyzer
**COSC 310 | Bike Ride Data Analysis**

---

## The Dataset

Four JSON files covering four days of cycling activity, each containing thousands of GPS track point records. Combined, the dataset totals **223,983 records**. Each record captures a snapshot in time including timestamp, distance, heart rate, speed, altitude, latitude, longitude, power output, cadence, temperature, and radar vehicle data.

---

## Problem 1: What was the rider's peak power moment of the week?

### Problem Definition
Power output (measured in watts) is one of the most direct indicators of cycling effort. A high power moment represents the single hardest physical effort the rider produced — whether a sprint, a punchy climb, or an aggressive acceleration. The goal is to find that single peak moment across all four days and describe the full context of what was happening at that instant.

### Algorithm Choice — Merge Sort
**Algorithm:** Merge Sort (`Sorting.mergeSort`)
**Sort criteria:** `sortCriteria = 7` (power in watts)
**Time complexity:** O(n log n)
**Space complexity:** O(n) — requires a second list during the merge step

Merge sort was chosen because it handles large datasets efficiently and is stable, meaning records with equal power values maintain their relative order. With nearly 224,000 records, a quadratic algorithm like selection or bubble sort would be far too slow. After sorting ascending by power, the peak record sits at the last index (`size() - 1`).

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
The peak power moment of the week was **705 watts** at a speed of **38 kph**, with a cadence of 97 rpm. This combination — high speed, high cadence, and peak power — is consistent with a sprint effort rather than a grinding climb. Notably, the heart rate at that moment was only 107 bpm, which seems low for such high output. This is expected: heart rate always lags physical effort by roughly 15–30 seconds, so the cardiovascular response hadn't fully caught up to the burst yet. The second highest effort (697W) shows a higher HR of 113 bpm at a lower speed, suggesting a more sustained effort like a headwind or hill.

---

## Problem 2: When did the rider show signs of fatigue?

### Problem Definition
Fatigue during a ride typically shows up as a combination of slowing speed and a rising heart rate — the body is working harder to maintain (or even losing) pace. The goal is to find when this pattern occurred across the week, and identify the single strongest fatigue signal moment.

### Algorithm Choice — Merge Sort + Binary Search
**Algorithms:** Merge Sort (`Sorting.mergeSort`) + Binary Search (`Searching.binarySearch`)
**Time complexity:** O(n log n) for sort, O(log n) for each binary search
**Space complexity:** O(n)

The approach uses two passes:
1. **Merge sort by timestamp** (`sortCriteria = 0`) to put all records in chronological order — required before any meaningful fatigue pattern scan.
2. **Binary search by heart rate threshold** (`index = 2`, threshold = 150 bpm) to efficiently locate the region of the dataset where the rider was working at high intensity. This is O(log n) rather than scanning all 223,983 records linearly.
3. A sequential scan of adjacent chronological records then identifies moments where speed dropped more than 0.5 m/s and HR rose by 2+ bpm simultaneously.

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
Two findings stand out. First, the binary search for HR >= 150 bpm returned only **3 records** across the entire week. This tells us the rider stayed almost entirely in an aerobic endurance zone — never really pushing into high-intensity territory. This is typical of a multi-day touring ride where pacing matters more than performance.

Second, the 260 fatigue signal moments show that speed fluctuated with minor HR rises frequently throughout the week. The strongest single moment shows a speed drop of **2.286 m/s (~8 kph)** with only a 2 bpm HR increase. The small HR rise suggests this was more likely a terrain event — a steep hill, a stop sign, or traffic — rather than deep physiological fatigue. If the rider were truly exhausted, we would expect a much larger HR rise accompanying the speed drop.

**Conclusion:** The data suggests this was a well-paced endurance week. The rider never pushed hard enough to accumulate significant fatigue, and speed drops were driven more by terrain and external conditions than by the body breaking down.

---

## Algorithm Complexity Summary

| Algorithm | Use | Time Complexity | Space Complexity |
|---|---|---|---|
| Merge Sort | Sort 223,983 records by power (Q1) | O(n log n) | O(n) |
| Merge Sort | Sort 223,983 records by timestamp (Q2) | O(n log n) | O(n) |
| Binary Search | Find HR >= 150 threshold (Q2) | O(log n) | O(1) |

Compared to a naive approach (linear scan for peak power, unsorted fatigue scan), this solution reduces the search phase from O(n) repeated queries to a single O(log n) lookup after an O(n log n) sort — a significant improvement at this dataset size.