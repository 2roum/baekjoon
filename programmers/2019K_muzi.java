import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (long t : food_times)
            pq.add(t);
        long prev = 0;
        int removed = 0;
        while (!pq.isEmpty() && (pq.peek()-prev)* pq.size() <= k){
            k -= (pq.peek()-prev) * pq.size();
            prev = pq.poll();
            removed++;
        }
        if (food_times.length - removed == 0)
            return -1;
        long num = k % (food_times.length - removed);
        long cnt = 0;
        for (int i = 0 ; i < food_times.length ; i++){
            if (food_times[i] > prev){
                if (cnt == num){
                    answer = i + 1;
                    break;
                }
                cnt++;
            }
        }
        return answer;
    }
}
