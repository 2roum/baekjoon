import java.io.*;
import java.util.*;

public class Main{
    static int[] prev;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        prev= new int[n];
        Arrays.fill(prev, -1);
        int ans = 0;
        for (int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (sameParent(u, v) && ans == 0){
                ans = i + 1;
            }
            merge(u,v);
        }
        System.out.println(ans);
    }
    public static int find(int a){
        if (prev[a] < 0)
            return a;
        return prev[a] = find(prev[a]);
    }
    public static boolean sameParent(int a, int b){
        a = find(a);
        b = find(b);
        if (a == b)
            return true;
        else
            return false;
    }
    public static void merge(int a, int b){
        a = find(a);
        b = find(b);
        if (a == b)
            return;
        prev[b] = a;
    }
}
