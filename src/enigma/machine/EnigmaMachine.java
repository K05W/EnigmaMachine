//***************************************************************//
// Filename: EnigmaMachine.java                                  //
// Description: EnigmaMachine class. 1 constructor, various      //
//              methods that mimic the encoding and decoding     //
//              done by the enigma machine.                      //
//                                                               //
//***************************************************************//

package enigma.machine;

/**
 * Enigma Machine is a class that represents the physical enigma
 * machine with customizable rotor and reflecting settings. 
 *
 */

public class EnigmaMachine {

   private Rotor r1; 
   private Rotor r2;
   private Rotor r3;
   private Reflector ref;
   
   /**
    * Class constructor creates Enigma Machine according
    * to rotor and reflector settings. 
    */
   public EnigmaMachine(Rotor rotorOne, Rotor rotorTwo, Rotor rotorThree, Reflector r) {
   
      r1 = rotorOne;
      r2 = rotorTwo;
      r3 = rotorThree;
      ref = r;
   }
   
   /** 
    * Runs character through encoding Left to right
    * on each rotor then reflecting and then back through
    * encoding right to left on each rotor.
    *
    * @param charToEncode
    */
   
   private char encodeChar(char charToEncode) {
   
      //pass character through rotors 
      char encodedChar = r1.encodeLR(charToEncode);
      encodedChar = r2.encodeLR(encodedChar);
      encodedChar = r3.encodeLR(encodedChar);
      //reflect character 
      encodedChar = ref.reflect(encodedChar);
      //pass back through rotors 
      encodedChar = r3.encodeRL(encodedChar);
      encodedChar = r2.encodeRL(encodedChar);
      encodedChar = r1.encodeRL(encodedChar);
      incrementRotors();
      return encodedChar;
   }
   
   /**
    * Takes in a string and examines each character, if 
    * character is an upper case letter the character is encoded,
    * otherwise it is appended to output string as is. 
    *
    * @param message String to encode
    * @return encodedLine encoded version of string 
    */
   public String encodeLine(String message) {
   
      String encodedLine = "";
      char letterToEncode; 
      for (int i = 0; i < message.length(); i++) {
         letterToEncode = message.charAt(i);
         if(letterToEncode >= 'A' && letterToEncode <= 'Z')
            encodedLine += encodeChar(letterToEncode);
         else
            encodedLine += letterToEncode;
      }
      return encodedLine; 
   
   }
   
   /**
    * Increments rotor positions according to each position.
    * Once preceding rotor moves from the last index of it's 
    * wiring array (25) to the first (0) the following   
    * rotor will increment(rotate) one position. 
    */
   private void incrementRotors() {
         
      if (r1.inc())
         if (r2.inc())
            r3.inc();
   
   }
   
   /**
    * Sets rotor positions. 
    *
    * @param rotorOnePos
    * @param rotorTwoPos
    * @param rotorThreePos
    */
   
   public void setRotors(int rotorOnePos, int rotorTwoPos, int rotorThreePos) {
   
      r1.setRotorPosition(rotorOnePos);
      r2.setRotorPosition(rotorTwoPos);
      r3.setRotorPosition(rotorThreePos);
   }
   
   /**
    * Method uses supporting English class to determine whether  
    * input string is in English according to multiplier and 
    * errorsAllowed values. 
    *
    * @param decodedText 
    * @param multiplier for error deviation
    * @param errorsAllowed allowable errors from frequencies 
    *
    * @return true if decoded text is likely English, false otherwise  
    */
   
   public boolean isEnglish(String decodedText, double multiplier, int errorsAllowed) {
   
      English.countAllLetters(decodedText);
      int errorCount = English.getErrorCount(multiplier);
              
      return errorCount <= errorsAllowed;
   }
}//end class
