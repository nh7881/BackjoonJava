import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1003 {
    public static class Main {
        int[][] list = new int[41][2];
        public static void main(String[] args) throws IOException {
            Main me = new Baekjoon1003.Main();
            me.solve();
        }
        public void solve() throws IOException {
            initList();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());
            int n;
            int[] temp;
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<t; i++) {
                n = Integer.parseInt(br.readLine());
                temp = fibonachi(n);
                sb.append(temp[0]).append(" ").append(temp[1]).append("\n");
            }
            System.out.println(sb);
        }

        public void initList() {
            for(int r=0; r<41; r++) {
                for(int c=0; c<2; c++) {
                    list[r][c] = -1;
                }
            }
            list[0][0] = 1;
            list[0][1] = 0;
            list[1][0] = 0;
            list[1][1] = 1;
        }

        public int[] fibonachi(int n) {
            int[] left, right;
            int[] res = new int[2];
            if(list[n][0] == -1 || list[n][1] == -1) {
                left = fibonachi(n-1);
                right = fibonachi(n-2);
                res[0] = left[0] + right[0];
                res[1] = left[1] + right[1];
                list[n][0] = res[0];
                list[n][1] = res[1];
                return res;
            }
            else {
                return list[n];
            }
        }
    }
}
