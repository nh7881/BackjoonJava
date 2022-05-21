import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//시물레이션이면 됬는데.... 1500*1500이라 안됨..
public class Baekjoon3197_Fail {
    public static class Main {
        char[][] map;
        boolean[][] cache;
        int[][] swans = new int[2][2];
        int[][] directs = {{0,1},{1,0},{0,-1},{-1,0}};
        public static void main(String[] args) throws IOException {
            Main me = new Main();
            me.solve();
        }
        void solve() throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] rc = br.readLine().split(" ");
            int r = Integer.parseInt(rc[0]);
            int c = Integer.parseInt(rc[1]);
            map = new char[r][c];
            cache = new boolean[r][c];
            String temp;
            int count=0;
            for(int i=0; i<r; i++) {
                temp = br.readLine();
                for(int j=0; j<c; j++) {
                    map[i][j] = temp.charAt(j);
                    if(map[i][j] == 'L') {
                        swans[count][0] = i;
                        swans[count][1] = j;
                        count++;
                    }
                }
            }
            int day = 0;
            initCache();
            while(!meet()) {
                melt();
                day++;
                initCache();
            }
            System.out.println(day);
        }
        void initCache() {
            for(int i=0; i<cache.length; i++) {
                for(int j=0; j<cache[i].length; j++) {
                    cache[i][j] = false;
                    if(map[i][j] == 'C') map[i][j] = '.';
                }
            }
        }
        //두 오리가 만나는지 여부
        //넓이 우선 탐색 ㄱㄱ bfs
        boolean meet() {
            int startY = swans[0][0], startX = swans[0][1], endY = swans[1][0], endX = swans[1][1];
            int dY, dX;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{startY, startX});
            cache[startY][startX] = true;
            while(!queue.isEmpty()) {
                startY = queue.peek()[0];
                startX = queue.peek()[1];
                queue.poll();
                for (int i = 0; i < 4; i++) {
                    dY = startY + directs[i][0];
                    dX = startX + directs[i][1];
                    if(dX >= 0 && dX < map[0].length && dY>=0 && map.length > dY && cache[dY][dX] == false) {
                        if(map[dY][dX] == 'L') return true;
                        else if(map[dY][dX] == '.') {
                            queue.offer(new int[]{dY,dX});
                            cache[dY][dX] = true;
                        }
                    }
                }
            }
            return false;
        }
        //얼음이 녹는것
        //녹은 얼음 주변만 녹음
        void melt() {
            int dI, dJ;
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map[i].length; j++) {
                    if(map[i][j] == 'X') {
                        for (int n = 0; n < 4; n++) {
                            dI = i + directs[n][0];
                            dJ = j + directs[n][1];
                            if (dJ >= 0 && dJ < map[0].length && dI >= 0 && map.length > dI) {
                                if (map[dI][dJ] == '.') {
                                    map[i][j] = 'C';
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
