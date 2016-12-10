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
    
    
    public int search(String s, String search,int val){
    	int init=val;
        for (int i=0; i<children.length; i++){
        	if(this.children[i]!=null){
        	int i1=this.children[i].begin;
        	int i2=this.children[i].end;
        	int g=val;
        
         for(int j=i1;j<i2;j++){
        	 if(search.length()==g){		 
        		 return i1;
        	 }
        	 if(s.charAt(j)!=search.charAt(g)){ 
        		
        		 break;
        	 }       	 
        	 g++;

        	 if(j+1==i2){
        		 return this.children[i].search(s,search,g);
        	 }
         }
        }
        }
        
        return -1;
      }
  }