import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Maze {	
	static int N, M;
	static int[][] MAP = new int[100][100];
	static int[][] D = new int[100][100];
	static boolean[][] visited = new boolean[100][100];
	
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static Queue<Pos> Q = new LinkedList<Pos>();
	
	
	public static class Pos{
		int x, y; 
		Pos(int _x, int _y){ x = _x; y = _y;}
		public String toString()
		{
			return "(" + x + "," + y + ")\n";
			
		}
	}

	public static void bfs()
	{
		Q.add(new Pos(0, 0));
		visited[0][0] = true;
		D[0][0] = 1;
		while(!Q.isEmpty())
		{
			Pos cur = Q.poll();
			//System.out.println(cur);
			for (int k = 0; k < 4; k++){
				int tmpx = cur.x + dx[k]; 
				int tmpy = cur.y + dy[k];
	
				if (tmpx < 0 || tmpx >= N || tmpy < 0 || tmpy >= M) continue;
				
				if (MAP[tmpx][tmpy] == 1 && !visited[tmpx][tmpy]){
					Q.add(new Pos(tmpx, tmpy));
					visited[tmpx][tmpy] = true;
					D[tmpx][tmpy] = D[cur.x][cur.y] + 1;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		for(int i = 0; i < N; i++)
		{
			String tmp = sc.next();
			for(int j = 0; j < M; j++)			
				MAP[i][j] = tmp.charAt(j) - '0';
		}
		bfs();
		System.out.println(D[N-1][M-1]);
		
		sc.close();
	}

}
