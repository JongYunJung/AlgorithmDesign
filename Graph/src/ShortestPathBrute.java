
import java.util.*;

public class ShortestPathBrute {
	static int[][] G = new int[100][100];				// 인접 행렬
	static int[] D = new int[100];						// 거리 저장
	static int[] P = new int[100];						// 최단 경로 트리
	
	static int V, E;
		
	// D[], P[] 배열 출력 하기
	public static void printResult()
	{
		for(int i = 1; i <= V; i++)
		{
			System.out.printf("%d ", D[i]);
		}
		System.out.printf("\n");
		for(int i = 1; i <= V; i++)
		{
			System.out.printf("%d ", P[i]);
		}
		System.out.printf("\n");
	}
	
	// BFS + 인접 행렬
	public static void BruteForce(int s)
	{
		for(int i = 1; i <= V; i++)
			D[i] = 0xffffff;
		D[s] = 0; P[s] = s;
		
		boolean flag;
		do
		{
			flag = false;
			
			for(int v = 1; v <= V; v++)				
			for(int i = 1; i <= V; i++)
			{
				if(G[v][i] != 0 && D[i] > D[v] + G[v][i])
				{
					D[i] = D[v] + G[v][i];
					P[i] = v;
					flag = true;
				}	
			}
		}while(flag);
	}
	
	public static void main(String[] args) {		

		Scanner sc = new Scanner(System.in);		
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		int from, to, weight;
		for(int i = 0; i < E; i++)
		{
			from = sc.nextInt();
			to = sc.nextInt();
			weight = sc.nextInt();				
			G[from][to] = G[to][from] = weight;
		}		
				
		System.out.println("최단 경로 / BruteForce");
		System.out.println("----------------");
		BruteForce(1); printResult();
		System.out.println("----------------");
		
		sc.close();
	}
}
