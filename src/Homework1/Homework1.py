#import numpy as np
def unigram_func(words,lines,unigram) : 
	
# 
    count = 0
    for i in range(0,len(words)):



		for j in range(0,len(lines)) :
			#word1 = line
			#for j in range(0,len(word1)):
			#print 'j ' + str(j)
			#print 'word1 :'+word1[j]
			#print  'words :'+words[i]
			if words[i] == lines[j]:
				#print 'words[i]:'+words[i]
				count = count + 1

		#print words[i] +'' +str(count) 
		#print 'i '+ str(i)
		unigram.update({words[i]:count})
		count = 0
		#print unigram
# */

def bigram(words,lines,bigram1,bigram2) :	
	count1 = 1 
	#print("words in bigram2 :"+str(words))
	i = 0
	#for i in lines :
		
	#	print "line test" + i
	#	print "test for lines"
	#	print lines[0]
	#	print lines[5]
		 
	for i in range(0,len(words)):
		for j in range(0,len(lines) - 1) :
			
			#for j in range(0,len(word1) - 1):
				
			if (words[i] == lines[j]):
				#print "testing for something quite fishy out here"
				#print "bigram1 " + words[i]+' ' +lines[j+1];
				if words[i]+' '+ lines[j+1] in bigram1:
					value  =bigram1.get(words[i]+ ' ' + lines[j+1])
				#	print value
					bigram1.update({words[i]+' '+lines[j+1] : value + 1})
				else:
				#	print 'test'
					bigram1.update({words[i]+' '+ lines[j+1] : count1})
				#	print bigram1
				#	print "bigram1 " + words[i]+' ' +lines[j+1] + str(bigram1.get(words[i]+' '+lines[j+1])) 
	for i in range(0,len(words)): 
		for j in range(0,len(words)):
		#	print "bigram2 top" + words[i]+' ' +words[j]
			if words[i]+' '+ words[j] in bigram1:
				value1  =bigram1.get(words[i]+ ' ' + words[j])
		#		print "bigram2 " + words[i]+' ' +words[j]
		#		print "value1 "+ str(value1)
				bigram2.update({words[i]+' '+words[j] : value1})
		#		print "bigram2 " + words[i]+' ' +words[j] + str(bigram2.get(words[i]+' '+words[j])) 
			else:
		#		print "bigram2 " + words[i]+' ' +words[j]
				bigram2.update({words[i]+' '+ words[j] : str(0)})
		#		print "bigram2 " + words[i]+' ' +words[j] + str(bigram2.get(words[i]+' '+words[j])) 
#	print "bigram2 " + str(bigram2)			

# for key in bigram1:
# 	key1=key.split(' ')
# 	print key1
# 	count2 = unigram.get(key1)
# 	print count2
# 	prob = (bigram1.get(key))/count2
# 	print prob

def writefile(bigram_prob,unigram_prob,bigram3,unigram2):
     
 	f3 = open('bigrams.txt','w')
 	resolution = 0.01
 	f3.write("   Bigrams    "+ "      Probablity \n" )
 	for key,value in bigram_prob.items():
 #		print("test "+ key);
 		#newValue = np.round(value/resolution)*resolution
 		value=format(round(value,3))

 		f3.write(key+ '    ' + str(value) + '\n')
 	f4 = open('unigrams.txt','w')
 	f4.write("  Unigrams  " +    "     Probablity \n" )
 #	print "test  " +str(unigram_prob)
 	for key,value in unigram_prob.items():
 		#print "key "+ key + "value " + value
 		value=format(round(value,3))
 		f4.write(key+ '     ' + str(value) + '\n')
 		
 	f5 = open('GoodturingforBigrams.txt','w')
 	resolution = 0.01	
 	f5.write("   Bigrams  "+ "       Probablity \n" )
 	for key,value in bigram3.items():
# 		print("test "+ key);
 		#newValue = np.round(value/resolution)*resolution
 		value=format(round(float(value),3))

 		f5.write(key+ '     ' + str(value) + '\n')
 	f6 = open('GoodturingforUnigrams.txt','w')
 	resolution = 0.01	
 	f6.write("   Unigrams  "+ "       Probablity \n" )
 	for key,value in unigram2.items():
 #		print("test "+ key);
 		#newValue = np.round(value/resolution)*resolution
 		value=format(round(float(value),3))

 		f6.write(key+ '     ' + str(value) + '\n')
    
 
def good_turing(bigram2,bigram3,unigram,unigram2):
	value3 = 0
	noofbigrams = 0
	good_turing_prob = 0
	for key3,value3 in bigram2.items():
		noofbigrams += int(value3)

	for Key,value in bigram2.items():
		Bigrams_higher_count = 0
		Bigrams_count = 0
		value=bigram2.get(Key)
		for Key1,value1 in bigram2.items():
			if int(value1) == int(value) + 1 :
				Bigrams_higher_count += 1 ;

				
			if (value1 == value):
				Bigrams_count += 1;
		if value == '0' :
			good_turing_prob = (float(Bigrams_higher_count)/noofbigrams)
#			print "key1 " + Key + "value "+ value
#			print "good_turing_prob" + str(good_turing_prob)
		else:

			#print "key1 esle" + Key + "value "+ str(value)
			#print "good_turing_prob esle" + str(good_turing_prob)
			good_turing_prob =  ((int(value) + 1) * (float(Bigrams_higher_count)/Bigrams_count))/noofbigrams		 
		
		bigram3.update({Key : str(good_turing_prob)})
		Bigrams_c = 0
		Bigrams_c_count = 0		
#	print "bigram3" + str(bigram3)
#	print "bigram2len " + str(len(bigram2))	
#	print "bigram " +str(bigram2)
	noofunigrams = 0
	for key2,value2 in unigram.items():
		noofunigrams += value2

	for Key,value in unigram.items():
		Unigrams_higher_count = 0
		Unigrams_count = 0
		value = unigram.get(Key)
		for Key1,value1 in unigram.items():
			if int(value1) == int(value) + 1 :
				Unigrams_higher_count += 1 ;

			if (value1 == value):
				Unigrams_count += 1;
		if value == 0 :
#			print "testing for god turning"
			good_turing_prob1 = (float(Unigrams_higher_count)/noofunigrams)
#			print "Unigrams_higher_count "+ str(Unigrams_higher_count)
#			print "unigram "+ str(len(unigram))
#			print "good_turing_prob1 "+str(good_turing_prob1)  
		else:	
			good_turing_prob1 =  ((int(value) + 1) * (float(Unigrams_higher_count)/Unigrams_count))	/noofunigrams		
	 	unigram2.update({Key : str(good_turing_prob1)})
		
		
#	print "unigram2" + str(unigram2)
#	print "unigram " + str(unigram)		
#	print "noofbigrams "+ str(noofbigrams)
#	print "noofunigrams "+ str(noofunigrams)
								

def main():
	# file_name = input("Please enter the file name")
	#sentence = input("Please enter the sentence")
	f = open('corpus.txt','r')
	data = f.read()
	lines = data.splitlines()
	#sentence = 'the nlp class was delayed sumit'
	f1 = open('sentence.txt','r')
	sentence = f1.read()
	words = sentence.splitlines()
	
#	print words[0]
#	print "words:"+str(words)
	count = 0
	
	i = 0
#   
	nooftokens = 0
	unigram = {}
	bigram1 = {}
	bigram2 = {}
	bigram3 = {}
	unigram2 = {}
	bigram_prob = {}
	unigram_prob = {}
	print "start"
	# init()
	unigram_func(words,lines,unigram)
	print unigram
	bigram(words,lines,bigram1,bigram2)	
	print "bigram2 in main " + str(bigram2)
	print "unigram in main " + str(unigram)
	for key in bigram2:
		key1= key.split(' ')
#		print key
#		print key1
#		print "key10" +key1[0]
		count2 = unigram.get(key1[0])
#		print count2
#		print "bigram2.get(key) " +str(bigram2.get(key)) 
		bigram_freq=bigram2.get(key)
#		print "bigram_freq "+ str(bigram_freq)
		if bigram_freq == '0':
#			print "inside bigram_freq"
			prob = 0
		else:	
			prob =  float(bigram_freq)/float(count2)
		print "prob" + str(prob)				    		
		bigram_prob.update({key: prob})
	print bigram_prob
	for key,value in unigram.items():
		nooftokens += value
	for key,value in unigram.items():
		value = unigram.get(key)
		prob1 = value/float(nooftokens)
		unigram_prob.update({key: prob1})		 
	writefile(bigram_prob,unigram_prob,bigram3,unigram2)
	#Response=input("Do you want to do Good-Turing discounting")
	#if Response == 'y':
	good_turing(bigram2,bigram3,unigram,unigram2)
	writefile(bigram_prob,unigram_prob,bigram3,unigram2)
	print "unigram "+ str(unigram)
	print "bigram2 " + str(bigram2)
	print "unigram_prob" +str(unigram_prob)

if __name__ == "__main__":
    main()




                   		 
        
 