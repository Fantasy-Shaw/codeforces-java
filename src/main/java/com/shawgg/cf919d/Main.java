package com.shawgg.cf919d;

import java.io.*;
import java.util.*;

public class Main {
    public static int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int[] deg = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (var e : edges) {
            graph.get(e[0]).add(e[1]);
            deg[e[1]]++;
        }
        // Topology sort
        Queue<Integer> qu = new ArrayDeque<>();
        // List<Integer> order = new ArrayList<>();
        int orderSize = 0;
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                qu.offer(i);
            }
        }
        // dp
        int[][] dp = new int[n][26];
        while (!qu.isEmpty()) {
            int u = qu.poll();
            // order.add(u);
            orderSize++;
            dp[u][colors.charAt(u) - 'a']++;
            for (int v : graph.get(u)) {
                if (--deg[v] == 0) {
                    qu.offer(v);
                }
                for (int k = 0; k < 26; k++) {
                    dp[v][k] = Math.max(dp[u][k], dp[v][k]);
                }
            }
        }
        // if (order.size() != n) {
        // return -1;
        // }
        if (orderSize != n) {
            return -1;
        }
        int ans = Integer.MIN_VALUE;
        for (var arr : dp) {
            ans = Math.max(ans, Arrays.stream(arr).max().getAsInt());
        }
        return ans;
    }

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();
        int m = sc.nextInt();
        String colors = sc.next();
        int[][] edges = new int[m][26];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt() - 1;
            edges[i][1] = sc.nextInt() - 1;
        }
        out.println(largestPathValue(colors, edges));
        out.close();
    }

    // -----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    // -----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    // --------------------------------------------------------
}