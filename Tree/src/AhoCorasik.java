import java.util.*;
 
public class AhoCorasik
{
    static final int ALPHABET_SIZE = 26;
    Node[]           nodes;
    int              nodeCount;
 
    public static class Node
    {
        int     parent;
        char    charFromParent;
        int     suffLink    = -1;
        int[]   children    = new int[ALPHABET_SIZE];
        int[]   transitions = new int[ALPHABET_SIZE];
        boolean leaf;
        {
            Arrays.fill(children, -1);
            Arrays.fill(transitions, -1);
        }
    }
 
    public AhoCorasik(int maxNodes)
    {
        nodes = new Node[maxNodes];
        // create root
        nodes[0] = new Node();
        nodes[0].suffLink = 0;
        nodes[0].parent = -1;
        nodeCount = 1;
    }
 
    public void addString(String s)
    {
        int cur = 0;
        for (char ch : s.toCharArray())
        {
            int c = ch - 'a';
            if (nodes[cur].children[c] == -1)
            {
                nodes[nodeCount] = new Node();
                nodes[nodeCount].parent = cur;
                nodes[nodeCount].charFromParent = ch;
                nodes[cur].children[c] = nodeCount++;
            }
            cur = nodes[cur].children[c];
        }
        nodes[cur].leaf = true;
    }
 
    public int suffLink(int nodeIndex)
    {
        Node node = nodes[nodeIndex];
        if (node.suffLink == -1)
            node.suffLink = node.parent == 0 ? 0 : transition(
                    suffLink(node.parent), node.charFromParent);
        return node.suffLink;
    }
 
    public int transition(int nodeIndex, char ch)
    {
        int c = ch - 'a';
        Node node = nodes[nodeIndex];
        if (node.transitions[c] == -1)
            node.transitions[c] = node.children[c] != -1 ? node.children[c]
                    : (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
        return node.transitions[c];
    } 
    public static void main(String[] args)
    {
    	AhoCorasik ahoCorasick = new AhoCorasik(1000);
        Scanner sc = new Scanner(System.in);
        System.out.println("텍스트 문자열 입력: ");
        String main = sc.nextLine().toLowerCase().trim();
        System.out.println("패턴 문자열 입력: ");
        String pattern = sc.nextLine().toLowerCase().trim();
        ahoCorasick.addString(pattern);
        System.out.println("패턴 문자열 입력: ");
        pattern = sc.nextLine().toLowerCase().trim();
        ahoCorasick.addString(pattern);
        System.out.println("패턴 문자열 입력: ");
        pattern = sc.nextLine().toLowerCase().trim();
        ahoCorasick.addString(pattern);
        
        int node = 0;
        for (char ch : main.toCharArray())
        {
            node = ahoCorasick.transition(node, ch);
        }
        if (ahoCorasick.nodes[node].leaf)
            System.out.println("A '" + pattern + "' string is substring of "
                    + main + " string.");
        else
            System.out.println("A '" + pattern
                    + "' string is not substring of " + main + " string.");
        sc.close();
    }
}