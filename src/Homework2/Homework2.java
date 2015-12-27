import java.io.*;
import java.util.*;
import java.util.Map.Entry;


public class Homework2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

       String filename = "HW2_TrainingSet.txt";
       String line;
       String []word_tags;
       int count = 1;
       HashMap<String,Integer> word_tag_count =  new  HashMap<String,Integer>();
       HashMap<String,Integer> word_count =  new  HashMap<String,Integer>();
       HashMap<String,Integer> word_tag_error =  new  HashMap<String,Integer>();
       HashMap<String,Double> word_tag_prob =  new  HashMap<String,Double>();
       //HashMap<String,Double> word_tag_prob1 =  new  HashMap<String,Double>();
       TreeMap<String,String> word_tag_prob1 =  new  TreeMap<String,String>();
       
       ArrayList word = new ArrayList(); 
       ArrayList wordtaglst = new ArrayList();
       FileReader fr = new FileReader(filename)  ;
       BufferedReader br = new BufferedReader(fr);
       int nooferror = 0;
       while((line = br.readLine()) != null){
    	   word_tags = line.split(" ");
    	   
    	   for(int i = 0 ;i < word_tags.length;i++){
    		   if (word_tag_count.containsKey(word_tags[i])){
    			   word_tag_count.put(word_tags[i], word_tag_count.get(word_tags[i]) + 1);
    		   }
    		   else
    		   word_tag_count.put(word_tags[i],count);
    		   
    	   }
    	   for ( int i = 0; i < word_tags.length;i++){
    		   wordtaglst.add(word_tags[i]);
    	   }
    	   for(int i = 0 ;i < word_tags.length;i++){
    		   String [] wordtag=word_tags[i].split("_");
    		   if (word_count.containsKey(wordtag[0])){
    			   word_count.put(wordtag[0], word_count.get(wordtag[0]) + 1);
    		   }
    		   else
    		   word_count.put(wordtag[0],count);
    		   
    	   }
    	   for (Entry<String, Integer> entry : word_tag_count.entrySet()) {
    		   String [] wordtag1=entry.getKey().split("_");
    		   int wordcount=word_count.get(wordtag1[0]);
    		   if (word_tag_prob.containsKey(wordtag1[0])){
    		   }
    		   else{
    			 double value=  entry.getValue()/(double)wordcount;
    			// System.out.println("entry.getValue() " + entry.getValue());
    			// System.out.println("wordcount " + wordcount);
    			// System.out.println("value " + value);
    			// double test = (double)2/3;
    			// System.out.println("2/3 " + test);
    		     word_tag_prob.put(entry.getKey(),value );
    	   }
       }
    	
       }
       for (Entry<String, Integer> entry : word_count.entrySet()) {
    	   String word1 = entry.getKey();
    	  // System.out.println("test ");	
    	   double max_prob = 0;
    	   for (Entry<String, Double> entry1 : word_tag_prob.entrySet()) {
    		   
               String[] wordtag1 = entry1.getKey().split("_");	
               if(word1.equals(wordtag1[0]) && entry1.getValue() > max_prob){
            	   
            	  // System.out.println("wordtag1[0] "+ wordtag1[0]);
            	   word_tag_prob1.put(wordtag1[0],entry1.getValue()+" "+wordtag1[1]);
            	 //  word_tag_prob1.put(entry1.getKey(),entry1.getValue());
            	   max_prob=entry1.getValue();
               }
     	 
    	   }
       }
       
       for(int i = 0 ;i < wordtaglst.size();i++){
		   String [] wordtag2=(wordtaglst.get(i)).toString().split("_");
		   //word_tag_prob1
		  // System.out.println("test34");
		   count = 1;
		   for (Entry<String, String> entry4 : word_tag_prob1.entrySet()) {
    		   //System.out.println("test");System.out.println("test");
               String[] wordtag3= entry4.getValue().split(" ");	
               String[] wordtag4= entry4.getKey().split("_");
               if(wordtag2[0].equals(wordtag4[0]) && (!wordtag2[1].equals(wordtag3[1]))){
            	   nooferror += 1;
            	  // System.out.println("test12");
            	  // System.out.println("wordtag1[0] "+ wordtag1[0]);
            	 if (word_tag_error.containsKey(wordtag2[0])){
    	            word_tag_error.put(wordtag2[0], word_tag_error.get(wordtag2[0]) + 1);
	   
                  }
            	 else
            	   word_tag_error.put(wordtag2[0],count);
            	   //max_prob=entry1.getValue();
               }
               
    	   }
		   
		   
	   }
       
     /*  for (Entry<String, Integer> entry : word_count.entrySet()) {
    	   String word1 = entry.getKey();
    	  // System.out.println("test ");	
    	   double max_prob = 0;
    	   for (Entry<String, Double> entry1 : word_tag_prob.entrySet()) {
    		   
               String[] wordtag1 = entry1.getKey().split("_");	
               if(word1.equals(wordtag1[0]) && entry1.getValue() > max_prob){
            	   
            	  // System.out.println("wordtag1[0] "+ wordtag1[0]);	
            	   word_tag_prob1.put(entry1.getKey(),entry1.getValue());
            	   max_prob=entry1.getValue();
               }
     	 
    	   }
       }*/
       
       /* for (Entry<String, Integer> entry : word_tag_count.entrySet()) {
          System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
    	 }   	
       for (Entry<String, Integer> entry : word_count.entrySet()) {
           System.out.println("Key1 : " + entry.getKey() + " Value1 : " + entry.getValue());
     	 }  
       for (Entry<String, Double> entry : word_tag_prob.entrySet()) {
           System.out.println("Key2 : " + entry.getKey() + " Value2 : " + entry.getValue());
     	 } */ 
      // for (Entry<String, String> entry : word_tag_prob1.entrySet()) {
      //     System.out.println("Key3 : " + entry.getKey() + " Value3 : " + entry.getValue());
     //	 }
	 //   for (Entry<String, Integer> entry2 : word_tag_error.entrySet()) {
     //     System.out.println("Key2 : " + entry2.getKey() + " Value2 : " + entry2.getValue());
	// }
	    //System.out.println("value for have "+word_tag_prob1.get("have") );
	    //System.out.println("value for MORE "+word_tag_prob1.get("more") );
	    //System.out.println("value for plans "+word_tag_prob1.get("plans") );
      System.out.println("error_rate "+((double)nooferror/wordtaglst.size())); 
      HashMap<String,Integer> map1 = sortByValues(word_tag_error);
      Set set3 = map1.entrySet();
      Iterator iterator3 = set3.iterator();
      int top5 = 1;
      //System.out.print("3. The 30 most frequent stems \n");
      System.out.println("Top 5 erroneously tagged words are:");
	while(iterator3.hasNext() && top5 <= 5){
           Map.Entry me3 = (Map.Entry)iterator3.next();
           System.out.print(top5 +":" );
           System.out.print(me3.getKey() + ": ");
           System.out.println("Error count  "+me3.getValue());
           top5++;
      }
      
	
 }
	private static HashMap sortByValues(HashMap map) { 
	      List list = new LinkedList(map.entrySet());
	      // Defined Custom Comparator here
	      Collections.sort(list, new Comparator() {
	           public int compare(Object o1, Object o2) {
	              return ((Comparable) ((Map.Entry) (o2)).getValue())
	                 .compareTo(((Map.Entry) (o1)).getValue());
	           }
	      });

	      // Here I am copying the sorted list in HashMap
	      // using LinkedHashMap to preserve the insertion order
	      HashMap sortedHashMap = new LinkedHashMap();
	      for (Iterator it = list.iterator(); it.hasNext();) {
	             Map.Entry entry = (Map.Entry) it.next();
	             sortedHashMap.put(entry.getKey(), entry.getValue());
	      } 
	      return sortedHashMap;
	 }

}
	
	