/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

import static coursework2.DictionaryMaker.readWordsFromCSV;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cyu15mtu
 */
public class AutoComplete {
    
    
    public static void main(String[] args) throws Exception {
        
        
        DictionaryMaker dictionary = new DictionaryMaker();
        StringBuilder stringTest = new StringBuilder();
        Trie trie = new Trie();    
        HashMap freqValues = new HashMap();  //makes the hash map for the frequency Values
        
        /*Arraylists defined here*/
        ArrayList<String> words = readWordsFromCSV("files/lotr.csv");
        ArrayList<String> wordList = dictionary.formDictionary(words);
        ArrayList<String> prefixList = new ArrayList<String>();       
        ArrayList<String> wordPartList = new ArrayList();
        ArrayList<Integer> freqList = new ArrayList();
        
        Scanner scan = new Scanner(new File("files/lotrQueries.csv"));
        
        /*this is adding the string from the Queries file to the prefix list */
        while(scan.hasNextLine()){
            prefixList.add(scan.nextLine());
        }
        
        /*add words to the trie*/
        for(String word: wordList)
        {
            String[] part = word.split("=");
            String wordPart = part[0]; //word part of string
            int freqPart = Integer.parseInt(part[1]);//number part of string
            
            wordPartList.add(wordPart);
            freqList.add(freqPart);
            trie.add(wordPart);         
        }
        
        List<String> allWords = new ArrayList<String>();
            
        /*find the words from the results*/
        for(String prefix: prefixList)
        {
            System.out.println("Prefix: " + prefix);         
            Trie subTrie = trie.getSubTrie(prefix); 
            //int counter = 0;
            
            allWords = subTrie.getAllWords();
            for(String word: allWords)
            {
                String newWord = prefix.substring(0, prefix.length() -1) + word;
                int freqValue = freqList.get(wordPartList.indexOf(newWord));
                System.out.print(newWord + " ");
                System.out.println(freqValue);
                freqValues.put(freqValue,wordPartList.indexOf(newWord));
                System.out.println(freqValues.toString());
                dictionary.saveToFile(freqList);
                
            }
            freqValues.clear();
            System.out.println("\n"); 
        }

        /*find the frequency of each word*/
        
    
    }
    
     
    
    
}
