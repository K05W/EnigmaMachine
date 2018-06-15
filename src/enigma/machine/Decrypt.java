//***************************************************************//
// Filename: Decrypt.java                                        //
// Description: Decrypt class. Use main method to for decrypting //
//              an encoded text file with an Enigma Machine      //
//              object.                                          //
//                                                               //
//***************************************************************//

package enigma.machine;

import java.util.Scanner;
import java.io.*;

/**
 * Decrypt class creates Enigma Machine and assigns rotor settings
 * and positions and then decrypts and encrypted input file and 
 * prints file to the screen as well as file "decrypted.txt". 
 *
 */

public class Decrypt {

   public static void main(String[] args) throws Exception {
      
      //create Enigma Machine and set rotors
      Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
      Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
      Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
      Reflector ref = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");
   
      EnigmaMachine em = new EnigmaMachine(r1, r2, r3, ref);
      
      em.setRotors(23, 12, 17);
      
      //decypt and print each line of file to screen and output file
      File encodedMessage = new File("encrypted.txt");
      PrintWriter decodedMessage = new PrintWriter("decrypted.txt");
      
      Scanner inputFile = new Scanner(encodedMessage);
          
      while(inputFile.hasNext()) {
      
         String decodedLine = em.encodeLine(inputFile.nextLine());
         System.out.println(decodedLine);
         decodedMessage.print(decodedLine + '\n');
      }
      
      inputFile.close();
      decodedMessage.close();
   }//end main
}//end class
