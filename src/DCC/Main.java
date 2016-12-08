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
					System.out.println("Tiempo transcurrido con 2^".concat(Integer.toString(j)).concat(" caracteres en ").concat(Long.toString(elapsedTime).concat(" Milisegundos")));
					
					try {
						BufferedWriter writer=new BufferedWriter(new FileWriter("test".concat(Integer.toString(j)).concat(".txt")));
						writer.write(s.toString());
						writer.close();
					} catch (IOException e1) {
			
						e1.printStackTrace();
					}
					
				}
				catch(Exception e){
					System.out.println("Error reading 1");
					e.printStackTrace();
				}
				
				
				
				//Picking N/10 random words
				ArrayList<String> words = new ArrayList<String>();
				try (BufferedReader br = new BufferedReader(new FileReader("test".concat(Integer.toString(j)).concat(".txt")))) {
					String line;
			   
					while ((line = br.readLine()) != null) {
						arr = line.split(" ");
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
			
				}
			

		}

	}


