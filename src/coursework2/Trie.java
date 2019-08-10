/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Adrian
 */
public class Trie {
   
    TrieNode root;
    Queue<TrieNode> queue = new LinkedList<TrieNode>(); //initialise the queue
    StringBuilder stringTest = new StringBuilder(); //initialise the stringBuilder
    
    public Trie()
    {
        root = new TrieNode();
    }
    
    public Trie(TrieNode s)
    {
        root = s;
    }
    
    boolean add(String key)
    { 
        TrieNode temp = root; //set the root TrieNode to value temp
        boolean addSuccessful = false; //addSuccessful set to false
        boolean isNewChar = false; //boolean to see if it's a new word
        
        /*for each character in the key */
        for(char s: key.toCharArray())
        {
            
            TrieNode next = temp.getOffspring(s); //the next node is from the offspring of temp
            
            if(next==null) //if next is null
            {
                isNewChar = true; //sets character to true
                temp.setOffspring(s); //set the temp offspring to the character
                next = temp.getOffspring(s); //set next to the value above
                //System.out.print(next.nodeChar); 
                addSuccessful = true;                
            }
            temp = next; //set temp to the next value
            
        }
        //System.out.println(temp.nodeChar);
        temp.completeWord = true; //once everything is done, set the completeWord boolean to true
        //System.out.println(temp.isKey());
        return addSuccessful; //return the boolean addSuccessful
    }
     
    
    
    
    
    
    boolean contains(String key)
    {
        TrieNode temp = root;
        /*for each character in the key */
        for(char s: key.toCharArray())
        {
            //System.out.println("character: " + temp.getValue());
            TrieNode next = temp.getOffspring(s);
            if(next==null) //if the next node is null
            {
                return false;   //return false
            }
            temp = next;    //set the temp node to the next one
        }
        if(temp.isKey()) //if the endOfWord boolean is true
            return true;
        else
            return false;

    }
    
    String outputBreadthFirstSearch()
    {
        StringBuilder stringBuild = new StringBuilder();
        TrieNode temp = root;
        
        queue.add(temp);
        
        /*while the queue is not empty*/
        while(!queue.isEmpty())
        {
            queue.remove(temp);
            for(TrieNode n: temp.offspring) //for each node in the offspring node
            {
                if (n!=null) //if the node is not null
                {
                    stringBuild.append(n.getValue()); //add 
                    queue.add(n);
                }
            }
            temp = (TrieNode)queue.peek(); //retrieves the head of the queue
            //System.out.println(stringBuild.toString());
        }
        return stringBuild.toString();
    }
    
  
    String outputDepthFirstSearch(TrieNode node, StringBuilder stringTest)
    {
        /*for each node in the offspring node */          
        for(TrieNode n: node.offspring)
        {
            if(n!=null) //if the node is not null
            {
                outputDepthFirstSearch(n, stringTest); //recurse the function
            }
            
        }
        stringTest.append(node.getValue()); //append the character of the node to the string
        return stringTest.toString();
    }
    
    Trie getSubTrie(String prefix)
    {
        
        TrieNode temp = root;
        /*for every character in the prefix*/
        for(char s: prefix.toCharArray()) 
        {            
                temp = temp.getOffspring(s);  //go through the tree until the last character in the prefix          
        }
        Trie subTrie = new Trie(temp); //set a new trie with the root at the last node
        
        return subTrie;
    }
    
    
    
    
    public List<String> getAllWords() {
        /*initialising all the variables needed for the private method below*/
        
        List<String> allWords = new ArrayList<String>();
        StringBuilder string = new StringBuilder();
        getWord(this.root, string, allWords);
        return allWords;
    }
    
    private void getWord(TrieNode node, StringBuilder string,List<String> allWords)
    {
        string.append(node.getValue()); //append the first value to the string
        
        if(node.isKey()) //if the end of word flag is true
        {    
            allWords.add(string.toString()); //add the string to the allWords list            
        }
        
        for(TrieNode n: node.offspring) 
        {
            if(n!=null)
            {              
//                System.out.println("value: " + n.getValue());
//                System.out.println("String: " + string);
//                System.out.println("is word complete: " + n.isKey());
                
                getWord(n,string,allWords);
                               
            }   
            
        }
        if(string.length() > 0)
        {
            string.deleteCharAt(string.length()-1); 
        }       
    }

    /*test main method */
//    public static void main(String[] args) 
//    {
//        Trie tree = new Trie();
//        System.out.println(tree.add("bat"));
//        System.out.println(tree.add("cat"));
//        System.out.println(tree.add("chat"));
//        System.out.println(tree.add("cheers"));
//        System.out.println(tree.add("cheese"));
//        System.out.println(tree.contains("cheers"));
//        System.out.println(tree.contains("ch"));
//        System.out.println(tree.contains("bob"));
//        
////        tree.add("bat");
////        tree.add("cat");
////        tree.add("chat");
////        tree.add("cheers");
////        tree.add("cheese");
////        tree.contains("ch");
//        
//        StringBuilder output = new StringBuilder();
//        
//        String outputBFS = tree.outputBreadthFirstSearch();
//        System.out.println("output breadth first search: " + outputBFS);
//        
//        String outputDFS = tree.outputDepthFirstSearch(tree.root, output);
//        System.out.println("output depth first search: " + outputDFS);
//        
//        Trie subTrie = tree.getSubTrie("c");
//        System.out.println("output depth first search with subTrie: " + subTrie.outputDepthFirstSearch(subTrie.root, new StringBuilder()));
//        
//        List<String> allWords = new ArrayList();
//        allWords = tree.getAllWords();
//        System.out.println("All words: " + allWords);
//        
//        allWords.clear();
//        allWords = subTrie.getAllWords();
//        System.out.println("All words from subTrie: " + allWords);
//        
//    }
    
 
}
