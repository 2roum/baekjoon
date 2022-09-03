import java.io.*;
import java.util.*;
public class Main{
    static int[] uf = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pair[][] points = new Pair[3][N];
        Arrays.fill(uf, -1);
        for (int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[0][i] = new Pair(Integer.parseInt(st.nextToken()), i);
            points[1][i] = new Pair(Integer.parseInt(st.nextToken()), i);
            points[2][i] = new Pair(Integer.parseInt(st.nextToken()), i);
        }
        for (int i = 0 ; i < 3 ;i++)
            Arrays.sort(points[i]);
        LinkedList<Edge> list = new LinkedList<>();
        for (int i = 0 ;i < N - 1 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                list.add(new Edge(points[j][i].idx, points[j][i+1].idx, Math.abs(points[j][i].pos - points[j][i+1].pos)));
            }
        }
        Collections.sort(list);
        int cnt = 0;
        long ans = 0;
        for (Edge edge : list){
            int u = edge.u;
            int v = edge.v;
            long cost = edge.cost;
            if (merge(u, v)){
                cnt++;
                ans += cost;
                if (cnt == N - 1)
                    break;
            }
        }
        System.out.println(ans);
    }
    public static int find(int a){
        if (uf[a] < 0)
            return a;
        return uf[a] = find(uf[a]);
    }
    public static boolean merge(int a, int b){
        a = find(a);
        b = find(b);
        if (a==b)
            return false;
        uf[b] = a;
        return true;
    }
}
class Edge implements Comparable<Edge>{
    public int u;
    public int v;
    public long cost;
    Edge(int u, int v, long cost) {
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
class Pair implements Comparable<Pair>{
    public long pos;
    public int idx;
    Pair(long pos, int idx) {
        this.pos = pos;
        this.idx = idx;
    }
    @Override
    public int compareTo(Pair other){
        if (this.pos > other.pos)
            return 1;
        else
            return -1;
    }
}
