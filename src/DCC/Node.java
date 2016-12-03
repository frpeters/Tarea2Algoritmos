package DCC;

public class Node {
    private int begin;
    private int end;
    private int depth;
    private Node parent;
    private Node[] children;
    private Node suffixLink;

    Node(int begin, int end, int depth, Node parent, String alphabet) {
      this.begin = begin;
      this.end = end;
      this.parent = parent;
      this.depth = depth;
      children = new Node[alphabet.length()];
    }
    
    public int getBegin(){
    	return begin;
    }
    
    public void incBegin(int inc){
    	this.begin += inc;
    }
    
    public int getEnd(){
    	return end;
    }
    
    public int getDepth(){
    	return depth;
    }
    
    public void incDepth(int inc){
    	this.depth+=inc;
    }
    
    public Node getParent(){
    	return parent;
    }
    
    public void setParent(Node parent){
    	this.parent = parent;
    }
    
    public Node[] getChildren(){
    	return children;
    }
    
    public Node getSuffixLink(){
    	return suffixLink;
    }
    
    public void setSuffixLink(Node suffixLink){
    	this.suffixLink = suffixLink;
    }
    
    public void printTree(String s){
    	System.out.println("Node: "+s.substring(this.begin, this.end));
    	if (this.suffixLink != null){
    		System.out.println("suffix link: "+ s.substring(this.suffixLink.begin, this.suffixLink.end));
    	}
    	for (Node Node: this.children){
    		if (Node!=null){
    			Node.printTree(s);
    		}
    	}
    }
  }