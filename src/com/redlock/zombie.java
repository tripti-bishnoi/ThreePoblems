package com.redlock;

public class zombie {

	public static void main(String[] args) {


        String[] strings = new String[]{
                "1100",
                "1110",
                "0001",
                "1100"

        };

        int count = zombieCluster(strings);
        System.out.println(count);
    }
	static int zombieCluster(String[] zombies) {

		int count = 0;
		
		int num = zombies.length;

        int[][] matrix = new int [num][num];
        for(int i = 0; i<num; i++) {
            for (int j = 0 ; j<num; j++) {
            	matrix[i][j] = Integer.parseInt(zombies[i].charAt(j) + "");
            }
        }

        boolean visited[] = new boolean[num];
        boolean visiting[] = new boolean[num];
        for (int i = 0; i < num; ++i) {
            if(!visited[i]) {
                visiting[i] = true;
                dfs(matrix, num, visited, visiting, i);
                visited[i] = true;
                count++;
            }
        }
        
        return count;
    }
	
	static void dfs(int matrix[][], int num, boolean visited[], boolean[] visiting, int s)
    {
        if( !visited[s] ) {
            visiting[s] = true;
            for(int j = s+1; j < num; j++) {
                if(matrix[s][j] == 1 && !visited[j]) {
                    visiting[j] = true;
                    dfs(matrix, num, visited, visiting, j);
                    visited[j] = true;
                }
            }
        }
    }
}
