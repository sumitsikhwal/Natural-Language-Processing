import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import edu.smu.tspell.wordnet.*;

public class Lesk {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String wordform = "bank";
		/*System.setProperty("wordnet.database.dir", "C:/Program Files (x86)/WordNet/2.1/dict/");*/
		System.setProperty("wordnet.database.dir", "WordNet/2.1/dict/");
		WordNetDatabase database = WordNetDatabase.getFileInstance();
	    Synset[] synsets = database.getSynsets(wordform);
	    StringBuilder exampleString = new StringBuilder();
	    StringBuilder signature = new StringBuilder();
	    String context = "The bank can guarantee deposits will eventually cover future tuition costs"+
"because it invests in adjustable-rate mortgage securities.";
	    /*String context = "pulled canoe river slope deposits"; */
	    /*String context = "home container money";*/
		//IndexWord word =  database.lookupIndexWord(POS.NOUN,"bank");
		//Synset[] senses = database.getSenses("bank", SynsetType.NOUN);
        // Display all definitions
	    String []context_array= context.toLowerCase().split("[\\s.]");
	    int maxOverlap = 0;
	    int index = 0;
	    if (synsets.length > 0)
	    	{
			/*	System.out.println("The following synsets contain '" +
						wordform + "' or a possible base form " +
						"of that text:");*/
				String[] best_sense = synsets[0].getWordForms();
			for (int i = 0; i < synsets.length; i++)
				//for (int i = 0; i < 2; i++)
				{
					System.out.println("");
					// index = i;
					exampleString.setLength(0);
					signature.setLength(0);
					
					String[] wordForms = synsets[i].getWordForms();
					for (int j = 0; j < wordForms.length; j++)
					{
						System.out.print((j > 0 ? ", " : "") +
								wordForms[j]);
					}
					System.out.println(": " + synsets[i].getDefinition());
					String Definition = synsets[i].getDefinition();
					String []Examples=synsets[i].getUsageExamples();
					for ( int k = 0; k < Examples.length;k++){
						//System.out.println("Examples  " + Examples[k].replaceAll("^\"|\"$", ""));
						
						exampleString.append(Examples[k]).toString().replaceAll("\"", " ");
						
				   }
					signature.append(Definition).append(exampleString);
					int overlap=computeOverlap(signature,context_array);
					System.out.println("Sense of the word - "+ wordform +":");
					for (int j = 0; j < wordForms.length; j++)
					{
						System.out.print((j > 0 ? ", " : "") +
								wordForms[j]);
					}
					System.out.println("\n"+"Gloss :" +Definition);
					//System.out.print("#" + index);
					for ( int k = 0; k < Examples.length;k++){
						System.out.println("Examples : " + Examples[k].replaceAll("^\"|\"$", ""));
						
						//exampleString.append(Examples[k]).toString().replaceAll("\"", " ");
						
				   }
					System.out.println("");
					System.out.println("No of overlaps -  "+overlap );
					if (overlap > maxOverlap){
						maxOverlap = overlap; 
						best_sense = synsets[i].getWordForms();
						index = i;
					}
					//System.out.println("maxoverlap "+maxOverlap );
				}
			    System.out.println("");
				System.out.println("Final Chosen Sense: ");
				for (int j = 0; j < best_sense.length; j++)
		    	{
		    		System.out.print((j > 0 ? ", " : "") +
					best_sense[j]);
		    		//System.out.print("#" + index);
		    	}
				String Definition = synsets[index].getDefinition();
				System.out.println("\n"+"Gloss :" +Definition);
				String []Examples=synsets[index].getUsageExamples();
				for ( int k = 0; k < Examples.length;k++){
					System.out.println("Examples : " + Examples[k].replaceAll("^\"|\"$", ""));
					
					//exampleString.append(Examples[k]).toString().replaceAll("\"", " ");
					
			   }
				System.out.println("\n"+ "MaxOverLapCount: "+maxOverlap );
	    	}
			else
			{
				System.err.println("No synsets exist that contain " +
						"the word form '" + wordform + "'");
			}
	    	//String[] wordForms = best_sense.getWordForms();
	    	
	    }

	private static int computeOverlap(StringBuilder signature, String[] context_array) {
		// TODO Auto-generated method stub
		ArrayList<String> stopwords = new ArrayList();
		stopwords.add("the"); 
		stopwords.add("bank");
		stopwords.add("a");
		stopwords.add("an");
		stopwords.add("it");
		stopwords.add("in");
		stopwords.add("can");
		//System.out.println("signature "+signature);
		String regexp = "[\\s,;:()/.'\\n\\t\\d?-]+";
		String signature1=signature.toString().toLowerCase().replaceAll("\"", " ");	
	    String[] signature2=signature1.toString().split("[\\s]+|\"");
	    int overlap_count = 0;
	    for(int count=0;count < context_array.length;count++){
	    	if (!stopwords.contains(context_array[count])){
	    		for(int i = 0; i < signature2.length;i++ ){
	    			//if (!signature2[i].isEmpty())
	    			
	    			if(signature2[i].equals(context_array[count])){
	    				//System.out.println("signature2[i]"+signature2[i].toString());
	    				overlap_count++;
	    			}
	    		}
	    	}
	    }
	    
	    return overlap_count;
	}
        
		
	}
	/*public static void listGlosses(Object word) throws JWNLException {
        System.out.println("\n\nDefinitions for " + word.getLemma() + ":");
        // Get an array of Synsets for a word
        Synset[] senses = word.getSenses();
        // Display all definitions
        for (int i = 0; i < senses.length; i++) {
            System.out.println(word + ": " + senses[i].getGloss());
        }    
    }*/


