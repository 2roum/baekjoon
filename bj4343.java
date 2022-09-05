import java.io.*;
import java.util.*;
public class Main{
    static int[] prev;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        double[][] points;
        while (T != 0){
            T--;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            points = new double[S][2];
            prev = new int[S];
            Arrays.fill(prev, -1);
            for (int i = 0 ; i < S ; i++){
                st = new StringTokenizer(br.readLine());
                points[i][0] = Integer.parseInt(st.nextToken());
                points[i][1] = Integer.parseInt(st.nextToken());
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int i = 0 ; i < S ; i++){
                for (int j = i + 1 ; j < S ; j++){
                    pq.add(new Edge(i, j, getDist(points[i], points[j])));
                }
            }
            int cnt = 0;
            double sum = 0;
            while (!pq.isEmpty()){
                Edge t = pq.poll();
                if (merge(t.u, t.v)){
                    cnt++;
                    sum = t.dist;
                    if (cnt == S - 1 - (N - 1))
                        break;
                }
            }
            System.out.println(String.format("%.2f", sum));
        }
    }
    public static double getDist(double[] p1, double[] p2){
        return (Math.sqrt(Math.pow(p1[0]-p2[0],2) + Math.pow(p1[1]-p2[1],2)));
    }
    public static int find(int a){
        if (prev[a] < 0)
            return a;
        return prev[a] = find(prev[a]);
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
    public double dist;
    Edge(int u, int v, double dist){
        this.u = u;
        this.v = v;
        this.dist = dist;
    }
    @Override
    public int compareTo(Edge other){
        if (this.dist > other.dist)
            return 1;
        else if (this.dist < other.dist)
            return -1;
        else
            return 0;
    }
}
