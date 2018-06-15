//***************************************************************//
// Filename: FindRotorSettings.java                              //
// Description: FindRotorSettings class. Use main method to      //
//              find correct rotor setting for decrypting an     //
//              encoded text file with an Enigma Machine object. //
//                                                               //
//***************************************************************//

package enigma.machine;

/**
 * Using tunable constants that store information about errorsAllowed, 
 * a multiplier for error deviation and the number of lines decoded, 
 * the FindRotorSettings looks at a specified section of decrypted text 
 * and prints the decrypted text and rotor settings if the text matches 
 * English letter frequencies.
 *
 */

import java.util.Scanner;
import static enigma.machine.Rotor.MAX;

public class FindRotorSettings {

   public static void main(String[] args) throws Exception {
       
      final int errorsAllowed = 6;
      final double multiplier = 2.0;
      final int numberOfLines = 4;
      
      //create Enigma Machine 
      Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
      Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
      Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
      Reflector ref = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
   
      EnigmaMachine em = new EnigmaMachine(r1, r2, r3, ref);
      
      //build section of message as string  
      String messageToDecode = "";
   
      java.io.File encodedMessage = new java.io.File("encrypted.txt");
      
      Scanner input = new Scanner(encodedMessage);
          
      int lineCount = 0;
     
      while(lineCount < numberOfLines) {
      
         messageToDecode += input.nextLine() + "\n";
         lineCount++;
      }
      
      /**
       * Decode part of message on all rotor settings
       * and check is letter frequencies match English
       * letter frequencies in English class. If a match
       * is found, print decoded message and rotor settings  
       */
      int rotorOnePosition, rotorTwoPosition, rotorThreePosition;
      
      for (int i = 0; i < MAX; i++) {
         for (int j = 0; j < MAX; j++) {
            for (int k = 0; k < MAX; k++) {
               
               rotorOnePosition = i;
               rotorTwoPosition = j;
               rotorThreePosition = k;
               em.setRotors(rotorOnePosition, rotorTwoPosition, rotorThreePosition);
               
               String decoded = em.encodeLine(messageToDecode);
               
               if(em.isEnglish(decoded, multiplier, errorsAllowed)) {
                  System.out.println("DECODED TEXT:\n" + decoded);
                  System.out.println("ROTOR SETTINGS: " + rotorOnePosition + ", " 
                     + rotorTwoPosition + ", " + rotorThreePosition);
               }  
            }
         }
      }
      input.close();
   }//end main  
}//end class 
   
   

