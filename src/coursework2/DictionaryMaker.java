
package coursework2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author 100122248
 */
public class DictionaryMaker {
    
    public DictionaryMaker(){
    }
/**
 * Reads all the words in a comma separated text document into an Array
 * @param f 
 */   
    public static ArrayList<String> readWordsFromCSV(String file) throws FileNotFoundException 
    {
        Scanner sc=new Scanner(new File(file));
        sc.useDelimiter(" |,");
        ArrayList<String> words=new ArrayList<>();
        String str;
        while(sc.hasNext()){
            str=sc.next();
            str=str.trim();
            str=str.toLowerCase();
            words.add(str);
        }
        return words;
    }
     public static void saveCollectionToFile(Collection<?> c,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(Object w: c)
        {
            printWriter.println(w.toString());
        }
        printWriter.close();
     }
     
     
     public ArrayList formDictionary(ArrayList<String> words) throws FileNotFoundException
     {
        HashMap wordDictionary = new HashMap();  //makes the hash map for the wordDictionary
        //ArrayList<String> words = readWordsFromCSV("files/testDocument.txt"); //get the file
        
        /*for each word in the word array*/
        for(int i=0; i < words.size(); i++)
        {
            if(wordDictionary.containsKey(words.get(i))) //if the wordDictionary already contains the word
            {
                wordDictionary.put(words.get(i), ((int)wordDictionary.get(words.get(i)) + 1)); //add one onto the frequency of the word
            }
            else
            {
                wordDictionary.put(words.get(i), 1); //add the word to the dictionary
                //System.out.println(words);
            }

        }
        Set wordSet = wordDictionary.entrySet();
        ArrayList sortedList = new ArrayList();
        for(Object wordObject: wordSet)
        {
            sortedList.add(wordObject.toString());
        }
        
        Collections.sort(sortedList);
        //System.out.println(sortedList);
        return sortedList;
     }
     
     
     public void saveToFile(ArrayList list) throws FileNotFoundException
     {
        File file = new File("files/lotrMatches.csv"); 
        PrintStream filePrint = new PrintStream(file);
        filePrint.print(list);
         
     }
     
     
   
         
//    public static void main(String[] args) throws Exception {
//        DictionaryMaker df=new DictionaryMaker();
//        ArrayList<String> in=readWordsFromCSV("files/testDocument.txt");
//        //DO STUFF TO df HERE in countFrequencies
//        ArrayList list = df.formDictionary(in);
//        df.saveToFile(list);
//        
//        
//        
//    }
    
}
