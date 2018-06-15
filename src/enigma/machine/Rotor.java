//***************************************************************//
// Filename: Rotor.java                                          //
// Description: Rotor class. 1 constructor, various              //
//              methods that allow for the creation and          //
//              customization of rotor objects to be used in     //
//              the Enigma Machine Class.                        //
//                                                               //
//***************************************************************//

package enigma.machine;

public class Rotor {
   
   private int position; //rotor position 
   final static int MAX = 26; //max number of characters 
   /**
    * arrays to represent the wiring of the Enigma Rotors
    * one array for each direction a character passes 
    * through the rotors (left to right and right to left). 
    */
   private char[] leftToRightWiring = new char[MAX]; 
   private char[] rightToLeftWiring = new char[MAX];
   
   /**
    * Class constructor for a single Rotor in the Enigma
    * machine. Argument must be a string of all 26 upper case letters of 
    * the alphabet with each character used only once for the rotor to work 
    * properly. Each array is assigned according to right to left wiring 
    * and left to right wiring. Sets position to 0 (first position) 
    * as default. 
    *
    * @param rotorLabel order of characters on Rotor. 
    */
   
   public Rotor(String rotorLabel) {
   
      position = 0;
       
      for (int i = 0; i < MAX; i++) 
         leftToRightWiring[i] = rotorLabel.charAt(i); 
      
      /**  
       * Each letter of the alphabet is placed at the
       * index of the corresponding letter in rotorLabel.
       * (i.e. if 'Q' has index 0 in rotorLabel, 'A' is 
       * placed where 'Q' is in alphabetical order 
       * (index 17) in the rightToLeftWiring array. 
       */
      int rightToLeftIndex;
      String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      
      for (int i = 0; i < MAX; i++) {
         rightToLeftIndex = getWiringIndex(rotorLabel.charAt(i)); 
         rightToLeftWiring[rightToLeftIndex] = alpha.charAt(i);
      }    
   }
   
   /**
    * Simulates the rotation of the physical enigma rotors by 
    * incrementing the position of the rotor by one. If the rotor
    * reaches the last index of the wiring array it returns to the 
    * first index as the physical rotor would continue to revolve 
    * until there were no further characters to encode or decode. 
    *
    * @return true if the rotor has returned to position 0, false otherwise  
    */
   
   public boolean inc() {
   
      if (position == MAX - 1) {
         position = 0;
         return true;
      }
      else {
         position++;
         return false;
      }
   }
   
   /**
    * Sets rotor position to specified value. Must be between
    * 0 and max - 1. 
    *
    * @param newRotorPosition what position the rotor is turned to 
    */
   
   public void setRotorPosition(int newRotorPosition) {
   
      position = newRotorPosition;
   }
   
   /**
    * Returns current rotor position
    *
    * @return position 
    *
    */
   
   public int getRotorPosition() {
      
      return position;
   }
   
   /**
    * Simulates the encoding of a character when passing
    * from the left to right side of a rotor. 
    * Returns a new character according to the rotor
    * label and position settings.
    *
    * @param charToEncode
    * @return encodedChar
    */
   
   public char encodeLR(char charToEncode) {
   
      int charIndex = getWiringIndex(charToEncode);
      char encodedChar;
      /**
       * handles wrap around if index plus position 
       * is out of bounds (greater than max - 1)
       */ 
      if ((charIndex + position) > MAX - 1) {
         encodedChar = leftToRightWiring[charIndex + position - MAX];
         return encodedChar;
      }
      else {
         encodedChar = leftToRightWiring[charIndex + position];
         return encodedChar; 
      }
   }
   
    /**
    * Simulates the encoding of a character when passing
    * from the right to left side of a rotor. 
    * Returns a new character according to the rotor
    * label and position settings.
    *
    * @param charToEncode
    * @return encodedChar
    */
   
   public char encodeRL(char charToEncode) {
       
      int charIndex = getWiringIndex(charToEncode);
      
      char encodedChar = rightToLeftWiring[charIndex]; 
      charIndex = getWiringIndex(encodedChar);
      
      /** 
       * Handles wrap around if index minus position value
       * is out of bound (a negative number) by adding 26  
       */
      if ((charIndex - position) < 0) {
         encodedChar = (char) (encodedChar - position + MAX);
         return encodedChar;  
      } 
      else {
         // else subtract position value from encoded character
         encodedChar = (char) (encodedChar - position);
         return encodedChar; 
      }
   }
   
   /**
    * Calculates index value of character that corresponds 
    * with the wiring array. Value will be result from 0 to 25  
    *
    * @param c character to calculate index 
    * @return c - 'A' int value of index from 0 to 25 
    */
   
   private int getWiringIndex(char c) {
   
      return c - 'A';   
   }
}//end class

