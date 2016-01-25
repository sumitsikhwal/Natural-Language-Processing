import java.util.*;


public class Viterbi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String obs1 = "331122313";
		String obs2 = "331123312";
		String [] hot_seq = new String[obs1.length() ];
		String [] cold_seq = new String[obs1.length()];
		for(int i = 0 ; i < hot_seq.length ; i++){
			/*if (i == 0){
				hot_seq[i] = "S";
			}
			else*/
			hot_seq[i] = "H";
			
		}
		for(int i = 0 ; i < cold_seq.length ; i++){
			/*if (i == 0){
				cold_seq[i] = "S";
			}
			else*/
			cold_seq[i] = "H";
			
		}
		HashMap<String,Double> obs_state_prob = new HashMap <String,Double>();
		HashMap<String,Double> state_seq_prob = new HashMap <String,Double>();
		HashMap<String,Double> obs_cold = new HashMap <String,Double>();
		ArrayList<Double> alpha_hot = new ArrayList<Double>();
		ArrayList<Double> alpha_hot_max = new ArrayList<Double>();
		ArrayList<Double> alpha_cold = new ArrayList<Double>();
		ArrayList<Double> alpha_cold_max = new ArrayList<Double>();
		ArrayList<Double> max_prob = new ArrayList<Double>();
		ArrayList<String> weather_seq = new ArrayList<String>();
		ArrayList<String> weather_seq1 = new ArrayList<String>();
		obs_state_prob.put("1|H",0.2);
		obs_state_prob.put("2|H",0.4);
		obs_state_prob.put("3|H",0.4);
		obs_state_prob.put("1|C",0.5);
		obs_state_prob.put("2|C",0.4);
		obs_state_prob.put("3|C",0.1);
		state_seq_prob.put("H|H",0.7);
		state_seq_prob.put("H|C",0.4);
		state_seq_prob.put("C|H",0.3);
		state_seq_prob.put("C|C",0.6);
		
		obs1.length();	
		double prob_hot_start = 0.8;
		double prob_cold_start = 0.2;   
	for(int k = 0 ; k <= 1 ; k++){
		if (k == 1){
			obs1=obs2;
			//System.out.println("for observation 2");
		}
		 	
		
	
		for(int i =0,j=0;i <obs1.length() &&  j< hot_seq.length ;i++,j++){
			
				if (j == 0){
					//alpha_hot.add (prob_hot_start * )
					String key = obs1.charAt(i)+"|H";
					double value=obs_state_prob.get(key);
					alpha_hot.add(prob_hot_start * value);
					String key1 = obs1.charAt(i)+"|C";
					double value3=obs_state_prob.get(key1);
					alpha_cold.add(prob_cold_start * value3);
					//System.out.println("alpha_cold " + "index " + j +" "+ alpha_cold.get(j));
					//System.out.println("alpha_hot " + "index " + j +" "+ alpha_hot.get(j));
					if (alpha_hot.get(i) > alpha_cold.get(i) ){
						if (k ==0 ){
							max_prob.add(alpha_hot.get(i));
							weather_seq.add("H");
						}
						else{
							max_prob.add(alpha_hot.get(i));
							weather_seq1.add("H");
						}
					}
					else{
						if (k ==0 ){
							max_prob.add(alpha_cold.get(i));
							weather_seq.add("C");
						}
						else{
							max_prob.add(alpha_cold.get(i));
							weather_seq1.add("C");
						}
					}
				}	
				else{
					String key = obs1.charAt(i)+"|H";
					double value=obs_state_prob.get(key);
					int index=alpha_hot.size() - 1;
					double value1 =alpha_hot.get(index)*value*state_seq_prob.get("H|H") ;
					double value2 =alpha_cold.get(index)*value*state_seq_prob.get("H|C") ;
					//System.out.println("value1 here " + value1);
					//System.out.println("value2 here " + value2);
					alpha_hot.add(Math.max(value1,value2) );
					
					String key2 = obs1.charAt(i)+"|C";
					double value4=obs_state_prob.get(key2);
					double value5 =alpha_cold.get(i-1)*value4*state_seq_prob.get("C|C") ;
					double value6 =alpha_hot.get(i-1)*value4*state_seq_prob.get("C|H") ;
					alpha_cold.add(Math.max(value5,value6) );
					//System.out.println("alpha_cold " + "index " + j +" "+ alpha_cold.get(j));
					//System.out.println("alpha_hot " + "index " + j + " " +alpha_hot.get(j));
					if (alpha_cold.get(i) > alpha_hot.get(i) ){
						max_prob.add(alpha_cold.get(i));
						if (k ==0 )
							weather_seq.add("C");
						else
							weather_seq1.add("C");	
					}
					else{
						max_prob.add(alpha_hot.get(i));
						if (k ==0 )
							weather_seq.add("H");
						else
							weather_seq1.add("H");
					}
				}
			
				
			
		}
		alpha_hot.clear();
		alpha_cold.clear();
		max_prob.clear();
		//weather_seq.clear();
		
	}
	System.out.println("Weather sequence for observation seq 331122313");
		for(int i = 0 ; i < weather_seq.size();i++ ){
			
			System.out.print(weather_seq.get(i));
			
			//System.out.println("prob_seq :"+ max_prob.get(i));
		}
		System.out.println("");
		System.out.println("Weather sequence for observation seq 331123312");
		for(int i = 0 ; i < weather_seq1.size();i++ ){
			
			System.out.print(weather_seq1.get(i));
			
			//System.out.println("prob_seq :"+ max_prob.get(i));
		}
		
		/*if (9.676800000000002E-4 > 4.917248000000001E-4){
			System.out.println("yes big"); 
		}*/
		/*else
			System.out.println("no big");*/
	}
}