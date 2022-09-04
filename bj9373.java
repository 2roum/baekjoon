import java.io.*;
import java.util.*;
public class Main{
    static int[] prev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T!=0){
            T--;
            double w = Integer.parseInt(br.readLine());
            int num = Integer.parseInt(br.readLine());
            if (num == 0){
                sb.append(String.format("%.6f", (w/2)) + '\n');
                continue;
            }
            int edgeNum = (num * (num-1))/2 + 2*num + 1;
            prev = new int[edgeNum];
            Arrays.fill(prev, -1);
            Point[] points = new Point[num];
            for (int i = 0 ; i < num; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                double x = Integer.parseInt(st.nextToken());
                double y = Integer.parseInt(st.nextToken());
                double r = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y, r);
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(num, num+1, w));
            for (int i = 0 ; i < num ; i++) {
                double left = points[i].x - points[i].r;
                double right = w - points[i].x - points[i].r;
                pq.add(new Edge(num, i, left));
                pq.add(new Edge(i, num + 1, right));
                for (int j = i + 1 ; j < num ; j++){
                    pq.add(new Edge(i, j, getDistance(points[i].x, points[i].y, points[j].x, points[j].y, points[i].r, points[j].r)));
                }
            }
            while (!pq.isEmpty()){
                Edge temp = pq.poll();
                int u = temp.u;
                int v = temp.v;
                merge(u,v);
                if (sameParent(num, num+1)){
                    double ans = temp.distance/2;
                    if (ans <= 0)
                        sb.append("0\n");
                    else
                        sb.append(String.format("%.6f", ans) + "\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
    public static int find(int a){
        if (prev[a] < 0)
            return a;
        return prev[a] = find(prev[a]);
    }
    public static boolean merge(int a, int b){
        a = find(a);
        b = find(b);
        if (a==b)
            return false;
        prev[b] = a;
        return true;
    }
    public static boolean sameParent(int a, int b){
        if (find(a) == find(b))
            return true;
        else
            return false;
    }
    public static double getDistance(double x1, double y1, double x2, double y2, double r1, double r2){
        double dx = Math.abs(x1-x2);
        double dy = Math.abs(y1-y2);
        double distance = Math.sqrt(dx*dx + dy*dy) - r1 - r2;
        return distance;
    }
}
class Point{
    public double x;
    public double y;
    public double r;
    Point(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
class Edge implements Comparable<Edge>{
    public int u;
    public int v;
    public double distance;
    Edge(int u, int v, double distance){
        this.u = u;
        this.v = v;
        this.distance = distance;
    }
    @Override
    public int compareTo(Edge other){
        return Double.compare(this.distance, other.distance);
    }
}
