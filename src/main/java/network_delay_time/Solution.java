package network_delay_time;

import java.util.*;

public class Solution {
    // Dijkstra algorithm
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[n + 1];
        pq.offer(new int[]{k, 0});
        int currDis = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            if (visited[currNode]) {
                continue;
            }
            visited[currNode] = true;
            currDis = curr[1];
            n--;
            if (!graph.containsKey(currNode)) {
                continue;
            }
            for (int[] next : graph.get(currNode)) {
                if (!visited[next[0]]) {
                    pq.offer(new int[]{next[0], currDis + next[1]});
                }
            }
        }
        return n == 0 ? currDis : -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.networkDelayTime(
                new int[][]{{14, 1, 8}, {11, 2, 25}, {14, 15, 37}, {3, 7, 70}, {11, 7, 60}, {13, 11, 87}, {15, 10, 67},
                        {13, 10, 58}, {5, 4, 56}, {9, 3, 26}, {5, 11, 51}, {11, 4, 92}, {7, 6, 8}, {7, 10, 95},
                        {14, 9, 0}, {4, 13, 1}, {7, 9, 89}, {3, 14, 24}, {11, 15, 30}, {13, 2, 91}, {15, 8, 60},
                        {1, 4, 96}, {8, 2, 71}, {6, 8, 38}, {14, 13, 46}, {2, 12, 48}, {10, 11, 92}, {8, 12, 28},
                        {8, 7, 12}, {9, 13, 82}, {8, 6, 27}, {3, 2, 65}, {4, 10, 62}, {11, 13, 55}, {1, 2, 52},
                        {8, 3, 98}, {7, 12, 85}, {6, 12, 97}, {9, 4, 90}, {2, 4, 23}, {9, 11, 20}, {1, 14, 61}, {8, 9, 77},
                        {6, 5, 80}, {14, 11, 33}, {9, 8, 54}, {13, 1, 42}, {13, 8, 13}, {10, 14, 40}, {9, 7, 18}, {14, 3, 50},
                        {14, 6, 83}, {14, 8, 14}, {2, 1, 86}, {9, 5, 54}, {11, 5, 29}, {9, 12, 43}, {9, 2, 74}, {14, 4, 87},
                        {12, 7, 98}, {7, 14, 13}, {4, 12, 33}, {5, 2, 60}, {15, 11, 33}, {8, 4, 99}, {9, 6, 98}, {4, 6, 57},
                        {6, 11, 5}, {9, 15, 37}, {1, 3, 30}, {9, 10, 60}, {13, 12, 73}, {13, 14, 56}, {1, 11, 13}, {14, 2, 8},
                        {4, 15, 60}, {11, 3, 90}, {2, 5, 86}, {11, 1, 1}, {13, 4, 2}, {15, 7, 91}, {15, 4, 51}, {11, 6, 70},
                        {2, 7, 51}, {11, 9, 37}, {4, 2, 92}, {10, 4, 4}, {7, 2, 30}, {13, 9, 79}, {8, 15, 41}, {11, 8, 18},
                        {15, 2, 4}, {12, 14, 88}, {12, 6, 9}, {12, 9, 44}, {1, 6, 87}, {15, 14, 42}, {4, 9, 41}, {7, 15, 90},
                        {4, 1, 84}, {7, 11, 9}, {3, 11, 75}, {5, 9, 2}, {2, 11, 96}, {12, 5, 89}, {6, 15, 25}, {5, 13, 7},
                        {15, 5, 32}, {13, 5, 84}, {7, 5, 9}, {15, 3, 14}, {12, 13, 4}, {5, 3, 73}, {6, 9, 85}, {6, 10, 29},
                        {1, 8, 24}, {12, 3, 85}, {4, 3, 60}, {1, 13, 6}, {1, 5, 58}, {2, 3, 29}, {14, 5, 67}, {13, 15, 70},
                        {5, 14, 94}, {15, 1, 95}, {3, 1, 17}, {10, 2, 6}, {11, 10, 44}, {9, 14, 62}, {4, 11, 32}, {15, 13, 48},
                        {2, 10, 77}, {3, 13, 90}, {5, 7, 68}, {10, 6, 78}, {3, 6, 95}, {10, 12, 68}, {13, 6, 73}, {10, 1, 8},
                        {10, 7, 18}, {10, 5, 64}, {5, 1, 55}, {13, 7, 90}, {1, 9, 67}, {3, 12, 76}, {14, 10, 22}, {12, 8, 83},
                        {4, 7, 76}, {8, 13, 25}, {5, 6, 57}, {13, 3, 90}, {6, 2, 96}, {11, 14, 61}, {12, 1, 94}, {12, 15, 12},
                        {4, 8, 88}, {4, 14, 27}, {7, 4, 25}, {3, 9, 57}, {2, 15, 90}, {1, 12, 85}, {12, 11, 44}, {5, 10, 13},
                        {5, 12, 96}, {14, 7, 24}, {14, 12, 98}, {10, 9, 36}, {15, 6, 17}, {8, 10, 11}, {2, 13, 5}, {10, 3, 78},
                        {6, 13, 11}, {5, 15, 34}, {12, 10, 12}, {9, 1, 68}, {10, 13, 1}, {7, 13, 86}, {1, 7, 62}, {2, 14, 53},
                        {8, 14, 75}, {2, 6, 49}, {10, 15, 83}, {7, 8, 88}, {6, 1, 87}, {8, 1, 38}, {8, 11, 73}, {3, 15, 1},
                        {3, 8, 93}, {2, 8, 26}, {4, 5, 26}, {3, 4, 58}, {7, 1, 55}, {7, 3, 84}, {5, 8, 97}, {12, 4, 42},
                        {6, 3, 71}, {6, 7, 48}, {15, 12, 3}, {1, 15, 30}, {10, 8, 11}, {2, 9, 49}, {6, 14, 95}, {3, 10, 68},
                        {6, 4, 14}, {11, 12, 29}, {1, 10, 93}, {8, 5, 55}, {12, 2, 86}, {3, 5, 26}, {15, 9, 12}},
                15,
                11
        ));
        System.out.println(solution.networkDelayTime(
                new int[][]{
                        {2, 13, 18}, {15, 10, 92}, {6, 15, 80}, {2, 14, 68}, {13, 14, 65}, {14, 3, 63}, {10, 13, 59},
                        {9, 7, 42}, {1, 14, 70}, {15, 14, 34}, {11, 1, 48}, {6, 7, 2}, {8, 13, 48}, {15, 6, 92},
                        {8, 7, 19}, {9, 14, 53}, {3, 5, 48}, {3, 10, 70}, {3, 8, 57}, {5, 15, 5}, {10, 14, 8},
                        {9, 3, 8}, {15, 8, 52}, {10, 5, 96}, {4, 7, 52}, {14, 13, 87}, {14, 10, 91}, {5, 2, 17},
                        {3, 15, 5}, {5, 1, 39}, {13, 3, 39}, {7, 13, 71}, {13, 2, 41}, {4, 13, 20}, {11, 12, 61},
                        {8, 4, 4}, {9, 8, 80}, {9, 2, 45}, {7, 9, 88}, {8, 15, 96}, {1, 12, 92}, {2, 7, 0}, {7, 2, 43},
                        {3, 9, 21}, {4, 2, 95}, {2, 12, 35}, {2, 5, 32}, {1, 9, 97}, {4, 9, 95}, {15, 4, 81},
                        {5, 13, 30}, {1, 6, 43}, {1, 7, 22}, {10, 3, 60}, {11, 4, 9}, {4, 11, 55}, {14, 5, 5}, {7, 4, 13},
                        {15, 13, 72}, {11, 3, 55}, {11, 8, 50}, {3, 7, 25}, {12, 11, 29}, {7, 10, 71}, {7, 5, 24},
                        {12, 14, 18}, {9, 13, 89}, {7, 3, 25}, {2, 9, 2}, {5, 11, 83}, {6, 4, 48}, {14, 1, 27},
                        {14, 11, 21}, {8, 14, 12}, {10, 1, 74}, {4, 1, 97}, {4, 10, 46}, {14, 8, 16}, {13, 5, 39},
                        {9, 4, 6}, {11, 7, 98}, {1, 13, 10}, {8, 11, 22}, {9, 11, 96}, {1, 8, 56}, {3, 14, 81},
                        {6, 11, 45}, {5, 4, 48}, {4, 6, 71}, {11, 15, 64}, {3, 12, 74}, {2, 6, 71}, {7, 8, 35},
                        {11, 2, 20}, {7, 12, 12}, {6, 14, 8}, {2, 15, 42}, {8, 2, 24}, {6, 12, 67}, {5, 8, 90},
                        {2, 10, 36}, {15, 7, 0}, {15, 1, 68}, {12, 4, 43}, {1, 5, 78}, {13, 9, 97}, {2, 4, 51},
                        {13, 15, 39}, {9, 12, 93}, {5, 3, 79}, {7, 1, 34}, {8, 12, 37}, {12, 15, 36}, {8, 5, 92},
                        {7, 11, 96}, {14, 9, 94}, {8, 1, 31}, {14, 2, 18}, {2, 8, 62}, {15, 3, 84}, {6, 1, 3}, {10, 4, 91}
                        , {1, 3, 75}, {1, 4, 9}, {11, 10, 69}, {7, 15, 88}, {6, 9, 25}, {9, 6, 44}, {6, 8, 68}, {6, 2, 96}
                        , {1, 15, 16}, {6, 3, 61}, {12, 9, 50}, {13, 11, 27}, {8, 6, 40}, {13, 12, 45}, {14, 7, 61},
                        {4, 15, 8}, {12, 2, 87}, {14, 4, 94}, {11, 6, 37}, {12, 8, 10}, {13, 6, 0}, {9, 15, 70},
                        {1, 10, 26}, {14, 6, 30}, {15, 2, 58}, {3, 1, 12}, {10, 7, 96}, {2, 3, 4}, {5, 14, 99},
                        {8, 3, 85}, {12, 10, 38}, {14, 15, 34}, {4, 8, 31}, {10, 8, 13}, {4, 12, 57}, {12, 7, 4},
                        {3, 2, 65}, {15, 5, 85}, {12, 5, 42}, {3, 6, 53}, {4, 3, 3}, {10, 15, 29}, {9, 5, 47},
                        {4, 5, 43}, {9, 1, 98}, {13, 4, 72}, {3, 4, 88}, {5, 9, 21}, {10, 12, 41}, {13, 10, 3},
                        {3, 11, 77}, {13, 7, 21}, {5, 7, 88}, {6, 5, 22}, {12, 6, 72}, {15, 12, 37}, {9, 10, 94},
                        {11, 14, 0}, {1, 11, 51}, {5, 10, 44}, {14, 12, 34}, {15, 9, 85}, {11, 13, 74}, {6, 10, 54},
                        {8, 10, 9}, {13, 8, 68}, {2, 11, 13}, {2, 1, 91}, {12, 3, 31}, {12, 13, 99}, {1, 2, 84},
                        {12, 1, 89}, {4, 14, 9}, {5, 12, 34}, {7, 14, 53}, {7, 6, 87}, {11, 9, 20}, {3, 13, 6},
                        {6, 13, 40}, {13, 1, 94}, {10, 11, 20}, {10, 6, 67}, {5, 6, 27}, {8, 9, 97}, {11, 5, 66},
                        {10, 2, 73}, {10, 9, 17}, {15, 11, 48}
                },
                15,
                2
        ));
    }
}
