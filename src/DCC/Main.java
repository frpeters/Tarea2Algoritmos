package DCC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		 final String ALPHABET = " abcdefghijklmnopqrstuvwxyz0123456789$\1\2";
		
		
			StringBuffer s=new StringBuffer();
			
			String[] arr;
			// Text pross.
			for(int j=15;j<16;j++){
			
				int N=(int)Math.pow(2, j);
				//Parsing 
				try (BufferedReader br = new BufferedReader(new FileReader("file".concat(Integer.toString(j)).concat(".txt")))) {
					String line;
					
					while ((line = br.readLine()) != null) {
						
						line= Normalizer.normalize(line, Normalizer.Form.NFD);
						line= line.replaceAll("[^\\p{ASCII}]", "");
						line=line.replaceAll("[^a-zA-Z\\s]", "").replaceAll("(\\r|\\n|\\t)", "");
						line=line.toLowerCase();
						//line= line.replaceAll(" ", "");
						s.append(line);
						
					}
					s.append("$");
					//
					long start = System.currentTimeMillis();    	
					//Testing build construction time.
					Node tree = SuffixTree.buildSuffixTree(s.toString(), ALPHABET);
					long elapsedTime =  System.currentTimeMillis() - start;
					System.out.println("Tiempo transcurrido construccion con 2^".concat(Integer.toString(j)).concat(" caracteres en ").concat(Long.toString(elapsedTime).concat(" Milisegundos")));
					
					try {
						BufferedWriter writer=new BufferedWriter(new FileWriter("test".concat(Integer.toString(j)).concat(".txt")));
						writer.write(s.toString());
						
						//Picking N/10 random words
						ArrayList<String> words = new ArrayList<String>();
						try (BufferedReader br2 = new BufferedReader(new FileReader("test".concat(Integer.toString(j)).concat(".txt")))) {
							String line2;
					   
							while ((line2 = br2.readLine()) != null) {
								arr = line2.split(" ");
								for ( String ss : arr) {
					    	   words.add(ss);
								}
							}
					        
						}
					
						catch(Exception e){
							System.out.println("Error reading 2");
						}
						
						int Nwords=N/10;
						ArrayList<String> randomwords = new ArrayList<String>();
						Random yourRandom = new Random();
						int g;
						for(int i=0;i<Nwords;i++){
							g=yourRandom.nextInt(words.size());
							randomwords.add(words.get(g));
						}
						long totalsearchtime=0;
						for(int k=0;k<randomwords.size();k++){
							long start2 = System.currentTimeMillis();    	
							int val=tree.search(s.toString(), randomwords.get(k),0);
							long elapsedTime2 =  System.currentTimeMillis() - start;
							totalsearchtime+=elapsedTime2;
						}
						System.out.println("Tiempo transcurrido en la busqueda con 2^".concat(Integer.toString(j)).concat(" caracteres en ").concat(Long.toString(totalsearchtime).concat(" Milisegundos")));
						
						writer.close();
					} catch (IOException e1) {
			
						e1.printStackTrace();
					}
					
				}
				catch(Exception e){
					System.out.println("Error reading 1");
					e.printStackTrace();
				}
				
				
			
				
				
				
				}
			

		}

	}


