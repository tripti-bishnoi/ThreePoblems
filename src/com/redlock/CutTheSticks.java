package com.redlock;

import java.util.ArrayList;
import java.util.Arrays;

public class CutTheSticks {

	public static void main(String[] args) {

		ArrayList<Integer> out = new ArrayList<>();

		CutSticks cutSticksFunction = (int[] l) -> {

			Arrays.sort(l);

			int i, j;
			for (i = 0; i < l.length; i++) {
				if (l[i] != 0) {
					int min = l[i];
					for (j = i; j < l.length; j++) {
						l[j] -= min;
					}
					out.add(j - i);
				}
			}

			int[] outArr = new int[out.size()];
			int index = 0;
			for (int k = 0; k < out.size(); k++) {
				outArr[index] = out.get(k);
				index++;
			}
			return out;
		};

		int[] l = { 1, 1, 2, 3 };
		cutSticksFunction.cut(l);

		for (int item : out) {
			System.out.println(item);
		}

	}
}

interface CutSticks {
	ArrayList<Integer> cut(int[] len);
}
