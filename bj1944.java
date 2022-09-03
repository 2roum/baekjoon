import java.io.*;
import java.util.*;
public class Main{
    static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static int[] prev;
    static int N;
    static char[][] map;
    static LinkedList<Edge> list;
    static Map<Integer, Integer> points = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        points = new HashMap<>();
        map = new char[N][N];
        prev = new int[N*N];
        Arrays.fill(prev, -1);
        int idx = 0;
        for (int i = 0 ; i < N ; i++){
            String s = br.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S' || map[i][j] == 'K'){
                    points.put(i * N + j, idx);
                    idx++;
                }
            }
        }
        list = new LinkedList<>();
        for (int temp : points.keySet()){
            if (bfs(temp) != M){
                System.out.println(-1);
                return ;
            }
        }
        Collections.sort(list);
        int sum = 0;
        int cnt = 0;
        for (Edge edge : list){
            int u = edge.u;
            int v = edge.v;
            int cost = edge.cost;
            if (merge(u,v)){
                sum += cost;
                cnt++;
                if (cnt == M)
                    break;
            }
        }
        if (sum == 0)
            System.out.println(-1);
        else
            System.out.println(sum);
    }
    public static int bfs(int start){
        boolean[][] visited = new boolean[N][N];
        visited[start/N][start%N] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int dist = 0;
        int cnt = 0;
        while (!queue.isEmpty()){
            int qSize = queue.size();
            for (int j = 0 ; j < qSize ; j++){
                int curr = queue.poll();
                int currX = curr % N;
                int currY = curr / N;
                if (curr != start && points.containsKey(curr)){
                    list.add(new Edge(points.get(start), points.get(curr), dist));
                    cnt++;
                }
                for (int[] dir : directions){
                    int nX = currX + dir[1];
                    int nY = currY + dir[0];
                    if ((nX>=0 && nX < N) && (nY>=0 && nY<N) && !visited[nY][nX] && map[nY][nX] != '1'){
                        visited[nY][nX] = true;
                        queue.add(nY * N + nX);
                    }
                }
            }
            dist++;
        }
        return cnt;
    }
    public static int find(int a){
        if (prev[a] < 0)
            return a;
        return (prev[a] = find(prev[a]));
    }
    public static boolean merge(int a, int b){
        a = find(a);
        b = find(b);
        if (a == b)
            return false;
        prev[b] = a;
        return true;
    }
}
class Edge implements Comparable<Edge>{
    public int u;
    public int v;
    public int cost;
    Edge(int u, int v, int cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge other){
        if (this.cost > other.cost)
            return 1;
        else if (this.cost < other.cost)
            return -1;
        else
            return 0;
    }
}
