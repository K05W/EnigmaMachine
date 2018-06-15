
//***************************************************************//
// Filename: English.java                                        //
// Description: English class. Stores data about english letter  //
//              frequencies and deviations. Includes two methods //
//              that count letters in a string and count errors  //
//                                                               //
//***************************************************************//

package enigma.machine;

import java.lang.*;

public class English {

   private static int totalNumberOfLetters; //total upper case letters
   final private static int MAX = Rotor.MAX; //max size for letter count array 
   
   //percentages of frequencies according to letter, i.e A at index 0;
   private static double[] engLetterFreq = {8.1, 1.6, 3.2, 3.6, 12.3,
      2.3, 1.6, 5.1, 7.2, 0.1, 0.5, 4.0, 2.2, 7.2, 7.9, 2.3, 0.2, 6.0,
      6.6, 9.6, 3.1, 0.9, 2.0, 0.2, 1.9, 0.1};
   //allowable deviation from frequency statistics  
   private static int[] engLetterDeviation = { 10, 50, 30, 30, 10, 30, 50,
      20, 15, 100, 80, 30, 30, 20, 20, 30, 100, 30, 20, 15, 30, 60, 40, 100, 
      40, 100}; 
   //array for letter count 
   private static int[] letterCount = new int[MAX];
   
   /**
    * Counts all uppercase characters from 'A' to 'Z'
    * in string, all other characters are ignored. Maintains 
    * count of each of the individual 26 letters of alphabet
    * as well as total count of all letters. 
    *
    * @param messageToCount String to count upper case letters in
    */
   public static void countAllLetters(String messageToCount) {
      
      totalNumberOfLetters = 0;
      letterCount = new int[MAX];
      
      int charIndex; //location of each character according to array, i.e. A equals 0
      char charToCheck; //each character to check in string 
      
      //iterate through string, check for uppercase letters, and update letter count
      for (int i = 0; i < messageToCount.length(); i++) {
         charToCheck = messageToCount.charAt(i);  
         if (charToCheck >= 'A' && charToCheck  <= 'Z') {
            charIndex = (int) charToCheck - 'A';
            letterCount[charIndex]++;
            totalNumberOfLetters++;
         }  
      }
   }
   
   /**
    * Calculates error range of letter frequencies 
    * and letter frequency of each character
    * from A to Z. If a characters frequency falls 
    * outside the error range the error count is  
    * incremented.
    *
    * @param mult multiplier for deviation calculation
    * @return errorTotal number of letters outside error range  
    */
   public static int getErrorCount(double mult) {
   
      int errorTotal = 0;
      
      for (int i = 0; i < MAX; i++) {
      
         double freq = engLetterFreq[i];
         int dev = engLetterDeviation[i];
         
         //calculate allowable deviation for frequency range 
         double lowerLimit = freq - ((mult * (dev/100.0)) * freq);
         double upperLimit = freq + ((mult * (dev/100.0)) * freq);
         
         //get the current letter frequency 
         double currentLetterFreq = (double) letterCount[i]/totalNumberOfLetters * 100.0;
         
         //check if letter frequency falls within specified range 
         int lowerVal = Double.compare(currentLetterFreq, lowerLimit);
         int higherVal = Double.compare(currentLetterFreq, upperLimit);
         
         //if outside range update error total
         if( lowerVal < 0  || higherVal > 0)
            errorTotal++;
      }
      return errorTotal;
   }  
}//end class
