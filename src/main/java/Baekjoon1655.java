import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 너무 어려운데...?
public class Baekjoon1655 {
    public static class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(), maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            int val;
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<n; i++) {
                val = Integer.parseInt(br.readLine());
                if (maxHeap.size() > minHeap.size()) minHeap.offer(val);
                else maxHeap.offer(val);
                if(!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(minHeap.poll());
                }
                sb.append(maxHeap.peek()).append("\n");
            }
            System.out.println(sb);
        }
    }
}