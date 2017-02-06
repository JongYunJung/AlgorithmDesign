// Ʈ���� ���̿� �ʺ� https://www.acmicpc.net/problem/2250
// Ʋ�� �κ� �����ϱ�

import java.util.*;


public class TreeHeight {
	
	static int[] r = new int[10001];
	static int[] l = new int[10001];
	static int[][] minmax = new int[10002][2];
	
	static int cnt = 0, maxH = 0; 
	
	static void treeHeight(int v, int h)
	{
		if(v == -1) return;
		if(maxH < h) maxH = h;
		
		treeHeight(l[v], h + 1);
		//----------------------------		
		cnt++;
		if(minmax[h][0] > cnt) minmax[h][0] = cnt;
		if(minmax[h][1] < cnt) minmax[h][1] = cnt;
		
		//----------------------------
		treeHeight(r[v], h + 1);				
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int node, left, right;
		
		for(int i = 1; i <= N; i++)
		{
			minmax[i][0] = 0xffffff;
			minmax[i][1] = 0;
		}
		for(int i = 0; i < N; i++)
		{
			node = sc.nextInt();
			left = sc.nextInt();
			right = sc.nextInt();	
			l[node] = left;			
			r[node] = right;
		}
		treeHeight(1, 1);
		
		int max = -1, height = 1;
		for(int i = 1; i <= N; i++)
		{
			int width = minmax[i][1] - minmax[i][0];
			if(max < width) 
			{
				max = width; height = i;
			}
		}
		System.out.printf("%d %d\n",height, max + 1);
		
		sc.close();
	}
}