package DCC;

import java.util.Random;

public class Test {
	
	final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789$\1\2";

	public static void main(String[] args){
	    Random rnd = new Random(1);
	    String s = "banana$";
	    Node tree = SuffixTree.buildSuffixTree(s, ALPHABET);
	    lcsLength = 0;
	    lcsBeginIndex = 0;

	    System.out.println("String: "+s);
	    tree.printTree(s);
	  }

	  static int lcsLength;
	  static int lcsBeginIndex;

	  // traverse suffix tree to find longest common substring
	  public static int lcs(Node node, int i1, int i2) {
	    if (node.getBegin() <= i1 && i1 < node.getEnd()) {
	      return 1;
	    }
	    if (node.getBegin() <= i2 && i2 < node.getEnd()) {
	      return 2;
	    }
	    int mask = 0;
	    for (char f = 0; f < ALPHABET.length(); f++) {
	      if (node.getChildren()[f] != null) {
	        mask |= lcs(node.getChildren()[f], i1, i2);
	      }
	    }
	    if (mask == 3) {
	      int curLength = node.getDepth() + node.getEnd() - node.getBegin();
	      if (lcsLength < curLength) {
	        lcsLength = curLength;
	        lcsBeginIndex = node.getBegin();
	      }
	    }
	    return mask;
	  }

	  static int slowLcs(String a, String b) {
	    int[][] lcs = new int[a.length()][b.length()];
	    int res = 0;
	    for (int i = 0; i < a.length(); i++) {
	      for (int j = 0; j < b.length(); j++) {
	        if (a.charAt(i) == b.charAt(j))
	          lcs[i][j] = 1 + (i > 0 && j > 0 ? lcs[i - 1][j - 1] : 0);
	        res = Math.max(res, lcs[i][j]);
	      }
	    }
	    return res;
	  }

	  static String getRandomString(int n, Random rnd) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < n; i++) {
	      sb.append((char) ('a' + rnd.nextInt(3)));
	    }
	    return sb.toString();
	  }
}
