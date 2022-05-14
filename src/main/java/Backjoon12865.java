import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Backjoon12865 {
    public static class Main {
        public static void main(String[] args) throws IOException {
            int[][] map = new int[101][100001];
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = nk[0];
            int max = nk[1];
            int[] wv;
            int[] ws = new int[n];
            int[] vs = new int[n];
            for(int i=0; i<n; i++) {
                 wv = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                 ws[i] = wv[0];
                 vs[i] = wv[1];
                 map[i][0] = 0;
            }
            map[n][0] = 0;
            for(int j=0; j<max; j++) {
                map[0][j] = 0;
            }
            map[0][max] = 0;
            for(int l=1; l<=n; l++) {
                for(int m=1; m<=max; m++) {
                    if(m >= ws[l-1]) {
                        map[l][m] = Math.max(map[l - 1][m], map[l - 1][m - ws[l - 1]] + vs[l - 1]);
                    }
                    else {
                        map[l][m] = map[l-1][m];
                    }
                }
            }
            System.out.print(map[n][max]);
        }
    }
}
