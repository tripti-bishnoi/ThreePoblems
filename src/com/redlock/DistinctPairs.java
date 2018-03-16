package com.redlock;

import java.util.HashMap;
import java.util.Set;

public class DistinctPairs {

	public static void main(String[] args) {
		int arr[] = { 6, 12, 3, 9, 3, 5, 1 };
		long k = 12;
		System.out.println(numberOfPairs(arr, k));
	}

	static int numberOfPairs(int[] a, long k) {
		int count = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : a) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}

		Set<Integer> keys = map.keySet();
		for (int key : keys) {
				if (map.containsKey((int) k - key) ) {
					if ((map.get(key) != 0 && map.get((int) k - key) != 0)) {
						if(((int) k - key == key) && map.get(key) <2)
							continue;
						count++;
						map.put(key, 0);
						map.put((int) (k - key), 0);
					}
			}
		}

		return count;
	}

}
