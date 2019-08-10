/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework2;

/**
 *
 * @author Adrian
 */
public class TrieNode {
    
    TrieNode[] offspring = new TrieNode[26];
    boolean completeWord = false;
    char nodeChar;

    
    public TrieNode()
    {
        this.nodeChar = ' ';
    }
    
    public TrieNode(char nodeChar)
    {
        this.nodeChar = nodeChar;
        
    }
    
    
    
   TrieNode getOffspring(char nodeChar)
   {   
       return offspring[nodeChar-97];
   }
    
   void setOffspring(char nodeChar)
   {
       offspring[nodeChar-97] = new TrieNode(nodeChar);
   }
   
   boolean isKey() 
  {
      return completeWord;
   }
   
   char getValue()
   {
       return nodeChar;
   }
   
    
}
