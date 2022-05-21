import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//시물레이션으로 쌩 bfr호 하면 안된다.
//cache를 초기화 하지 않는다.
//어차피 녹은 지점에서 모든 방향으로 전부 출발하면 갔던 곳은 다시 가봤자 의미가 없다. 결국 새로운 길은 녹은 지점에서 나타나니까!
//녹이는것도 1500*1500*4*day면 시간초과다.
//녹이는것도 새로 녹이는것만 녹인다.
//새로 녹이는 것의 기준은 오리가 부딫친 얼음!!, 오리가 부딪혔다==물과 인접해있다, 왜냐하면 오리는 물에만 있을 수 있으니까!
//위 틀림, 왜냐면 벽으로 막혀있어서 오리가 가지 못하는 반대편 얼음도 녹아야하기 때문이다.

public class Baekjoon3197_Sucess {
    public static class Main {

        class Pos{
            int r,c;
            Pos(int r, int c) {
                this.r = r;
                this.c = c;
            }
            int getR() {return this.r;}
            int getC() {return this.c;}
            Pos add(Pos p) {
                return new Pos(this.r+p.getR(), this.c+p.getC());
            }
        }
        char[][] map;
        boolean[][] cache;
        boolean[][] tempcache;
        Pos[] swans = new Pos[2];
        Pos[] directs = {new Pos(0,1),new Pos(1,0),new Pos(0,-1),new Pos(-1,0)};
        Queue<Pos> queue;
        Queue<Pos> tempQue;
        Queue<Pos> meltQue;

        public static void main(String[] args) throws IOException {
            Main me = new Main();
            me.solve();
        }
        //1.입력
        //2.오리가 만나는지 확인 bfs
        //3.안 만나면 얼음을 녹인다. 다시 2로
        //4.만나면 day 출력
        void solve() throws IOException{
            int day = 0;
            input();
            tempQue = new LinkedList<>();
            queue = new LinkedList<>();
            queue.offer(swans[0]);
            cache[swans[0].getR()][swans[0].getC()] = true;
            while(!meet()) {
                melt();
                day++;
            }
            System.out.println(day);
        }
        void input() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] rc = br.readLine().split(" ");
            int r = Integer.parseInt(rc[0]);
            int c = Integer.parseInt(rc[1]);
            map = new char[r][c];
            cache = new boolean[r][c];
            tempcache = new boolean[r][c];
            meltQue = new LinkedList<>();
            String temp;
            int count=0;
            for(int i=0; i<r; i++) {
                temp = br.readLine();
                for(int j=0; j<c; j++) {
                    map[i][j] = temp.charAt(j);
                    if(map[i][j] == 'L') {
                        swans[count] = new Pos(i,j);
                        count++;
                    }
                    if(map[i][j] != 'X') {
                        meltQue.offer(new Pos(i,j));
                    }
                }
            }
        }
        //두 오리가 만나는지 여부
        //넓이 우선 탐색 ㄱㄱ bfs
        //얼음 만날 때 meltList에 저장
        //처음엔 평범하게 bfs
        //두번째부턴 녹은 얼음 위치가 들어있는 que로 시작
        boolean meet() {
            Pos start;
            Pos delta;
            while(!queue.isEmpty()) {
                start = queue.poll();
                for (int i = 0; i < 4; i++) {
                    delta = start.add(directs[i]);
                    if(delta.getC() >= 0 && delta.getC() < map[0].length &&
                            delta.getR()>=0 && map.length > delta.getR()
                            && cache[delta.getR()][delta.getC()] == false) {
                        //백조를 만났다면
                        if(map[delta.getR()][delta.getC()] == 'L') return true;
                            //물을 만났다면
                        else if(map[delta.getR()][delta.getC()] == '.') {
                            queue.offer(delta);
                            cache[delta.getR()][delta.getC()] = true;
                        }
                        //얼음을 만났다면
                        else {
                            if (tempcache[delta.getR()][delta.getC()] == false) {
                                tempQue.offer(delta);
                                tempcache[delta.getR()][delta.getC()] = true;
                            }
                        }
                    }
                }
            }
            while (!tempQue.isEmpty()) {
                queue.offer(tempQue.poll());
            }
            return false;
        }

        void melt() {
            Pos delta;
            int size = meltQue.size();
            for (int n = 0; n < size; n++) {
                for (int i = 0; i < 4; i++) {
                    delta = meltQue.peek().add(directs[i]);
                    if (delta.getC() >= 0 && delta.getC() < map[0].length &&
                            delta.getR() >= 0 && map.length > delta.getR()
                            && map[delta.getR()][delta.getC()] == 'X') {
                        map[delta.getR()][delta.getC()] = '.';
                        meltQue.add(delta);
                    }
                }
                meltQue.poll();
            }
        }
    }
}
