package DCC;

public class SuffixTree {

  public static Node buildSuffixTree(CharSequence s, String alphabet) {
    int n = s.length();
    byte[] a = new byte[n];
    for (int i = 0; i < n; i++){
    	a[i] = (byte) alphabet.indexOf(s.charAt(i));
    }
    Node root = new Node(0, 0, 0, null, alphabet);
    Node node = root;
    for (int i = 0, tail = 0; i < n; i++, tail++) {
      Node last = null;
      while (tail >= 0) {
        Node ch = node.getChildren()[a[i - tail]];
        while (ch != null && tail >= ch.getEnd() - ch.getBegin()) {
          tail -= ch.getEnd() - ch.getBegin();
          node = ch;
          ch = ch.getChildren()[a[i - tail]];
        }
        if (ch == null) {
          node.getChildren()[a[i]] = new Node(i, n, node.getDepth() + node.getEnd() - node.getBegin(), node, alphabet);
          if (last != null) {
        	  last.setSuffixLink(node);
          }
          last = null;
        } 
        else {
          byte t = a[ch.getBegin() + tail];
          if (t == a[i]) {
            if (last != null) {
            	last.setSuffixLink(node);
            }
            break;
          } 
          else {
            Node splitNode = new Node(ch.getBegin(), ch.getBegin() + tail, node.getDepth() + node.getEnd() - node.getBegin(), node, alphabet);
            splitNode.getChildren()[a[i]] = new Node(i, n, ch.getDepth() + tail, splitNode, alphabet);
            splitNode.getChildren()[t] = ch;
            ch.incBegin(tail);
            ch.incDepth(tail);
            ch.setParent(splitNode);
            node.getChildren()[a[i - tail]] = splitNode;
            if (last != null) {
            	last.setSuffixLink(splitNode);
            }
            last = splitNode;
          }
        }
        if (node == root) {
          --tail;
        } else {
          node = node.getSuffixLink();
        }
      }
    }
    return root;
  }  
}



