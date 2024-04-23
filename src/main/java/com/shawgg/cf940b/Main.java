package com.shawgg.cf940b;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        // Start writing your solution here. -------------------------------------
        long n = sc.nextLong();
        long k = sc.nextLong();
        long a = sc.nextLong();
        long b = sc.nextLong();
        long cost = 0;
        while (n > 1) {
            if (n % k != 0) {
                if (n < k) {
                    cost += a * (n - 1);
                    break;
                } else {
                    cost += a * (n % k);
                    n -= n % k;
                }

            } else {
                if (a * (n - n / k) <= b) {
                    cost += a * (n - 1);
                    break;
                } else {
                    cost += b;
                    n /= k;
                }
            }
        }
        out.println(cost);

        // Stop writing your solution here. -------------------------------------
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