import java.util.*;
class Solution {
    static int max = 0;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public int solution(int[] info, int[][] edges) {
        for (int i = 0 ; i < info.length ; i++)
            adj.add(new ArrayList<>());
        for (int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
        }
        Set<Integer> set = new HashSet<>(adj.get(0));
        dfs(1, 0, info, set);
        return max;
    }
    public void dfs(int sheep, int wolf, int[] info, Set<Integer> next){
        if (sheep == wolf)
            return ;
        max = Math.max(max, sheep);
        for (int t : next){
            if (info[t] == 0){
                Set<Integer> temp = new HashSet<>(next);
                temp.remove(t);
                temp.addAll(adj.get(t));
                dfs(sheep + 1, wolf, info, temp);
            }
            else {
                Set<Integer> temp = new HashSet<>(next);
                temp.remove(t);
                temp.addAll(adj.get(t));
                dfs(sheep, wolf + 1, info, temp);
            }
        }
    }
}
