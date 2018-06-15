//***************************************************************//
// Filename: Reflector.java                                      //
// Description: Reflector class. 1 constructor, 1 reflect        //
//              method that returns encoded character based      //
//              on reflector settings. To be used within the     //
//              enigma class.                                    //
//                                                               //
//***************************************************************//

package enigma.machine;

import static enigma.machine.Rotor.MAX;
/**
 * Reflector class is used as part of the Enigma Machine class 
 * to encode or decode upper case letters of the alphabet. 
 */
public class Reflector {
   
    
   //final static int max = 26; //max size of array
   /**
    * array holds sequence of 26 unique letters of the alphabet
    * in a specified order.
    */ 
   private char[] reflectorWiring = new char[MAX];
      
   /**
    * Class constructor. Iterates through input string
    * and fills array with values according to order of
    * characters in String. 
    *
    * @param reflectorLabel String value of 26 characters 
    *
    */   
      
   public Reflector(String reflectorLabel) {
   
      for (int i = 0; i < MAX; i++)
         reflectorWiring[i] = reflectorLabel.charAt(i);
   }
   
   /**
    * Reflects appropriate character based on index 
    * of input character. Calculates index using 
    * char arithmetic. Resulting index will be 0 - 25.  
    *
    * @param charToReflect
    * @return reflectorWiring[charIndex] reflected character 
    */
   
   public char reflect(char charToReflect) {
      
      int charIndex = charToReflect - 'A';
      return reflectorWiring[charIndex];
   
   }
}//end class
