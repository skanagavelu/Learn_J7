public class BinaryLiterals {
	
	public static void main(String[] args) {
		
		/**
		 * PREFACE
		 * 
		 * Java only supports signed types (except char) in primitives.
		 * Unsigned Integer Arithmetic API now in JDK 8
		 * 
		 * byte, 8 bit
		 * short, 16 bit
		 * char in Java is UTF-16 encoded, which requires a minimum of 16-bits of storage for each character.
		 * int, 32 bit
         * long, 64 bit. 
		 * 
		 */

		
		/**
		 * The literals supported before Java7 
		 * 
		 * Octal numbers, using a leading 0 (zero) digit and one or more additional octal digits (digits between 0 and 7), such as 077. 
		 * Octal numbers may evaluate to negative numbers; for example 037777777770 is actually the decimal value -8.
		 */
		long octalVal =  037777777777777770L;
		System.out.format("octalVal: %s%n", octalVal);
		
		/*
		 * hexadecimal numbers, using the form 0x (or 0X) followed by one or more hexadecimal digits (digits from 0 to 9, a to f or A to F). 
		 * For example, 0xCAFEBABEL is the long integer 3405691582. Like octal numbers, hexadecimal literals may represent negative numbers.
		 */
		int hexIntVal = (int) 0xCAFEBABE;    // Why this cast is required ?
		long hexLongVal = 0xCAFEBABEL;       //L to represent long value
		System.out.format("hexIntVal: %s%n", hexIntVal);
		System.out.format("hexLongVal: %s%n", hexLongVal);
		
		
		
		/**
		 * There is no support for binary before java7
		 * Then how to work with binary literals?
		 */
		//Method_1
		int intCorrespondingBinaryVal = Integer.parseInt("001101", 2); //radix 2
		System.out.format("XOR Radix 2  :%s%n", Integer.toString(5 ^ 3, 2));  //REPRESENT IN BINARY
		System.out.format("XOR Radix 10 :%s%n", Integer.toString(5 ^ 3, 10)); //REPRESENT IN DECIMAL
		
		//Method_2
		//https://docs.oracle.com/javase/6/docs/api/java/util/BitSet.html
		/*
		 import java.util.BitSet;

		 class BitSetUsage {
    		public static void main(String[] args) {
        		BitSet bits1 = fromString("1000001");
        		BitSet bits2 = fromString("1111111");

        		System.out.println(toString(bits1)); // prints 1000001
        		System.out.println(toString(bits2)); // prints 1111111

        		bits2.and(bits1);
                 //bits2.xor(bits1);
                  
        		System.out.println(toString(bits2)); // prints 1000001

 				 //bits2.set(6); // set the 6th bit index to true.
 				 //bits2.flip(6);Sets the bit at the specified index to the complement of its current value.
 				 //
    		}

    		private static BitSet fromString(final String s) {
        		return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    		}

    		private static String toString(BitSet bs) {
        		return Long.toString(bs.toLongArray()[0], 2);
        		//return Long.toBinaryString(bs);
    		}
		 }
		 */
		
		
		
		
		/**
		 * Starting in J2SE 7.0, as binary numbers, using the form 0b (or 0B) followed by one or more binary digits (0 or 1). 
		 * For example, 0b101010 is the integer 42. Like octal and hex numbers, binary literals may represent negative numbers.
		 */
		
		// An 8-bit 'byte' value:
		byte aByte = (byte) 0b0111111; // is this (byte) casting is important? Yes when the value is more than the byte range 127.
		byte bByte = (byte) 0b00100001;
		byte cByte = (byte) (aByte + bByte); //Mandatory casting while arithmetic operation,  
		//because the arithmetic expression on the right-hand side of the assignment operator evaluates to int by default.
		//If an integer operator has an operand of type long, then the other operand is also converted to type long. 
		//Otherwise the operation is performed on operands of type int, 
		//if necessary shorter operands are converted into int. 
		
		
		//Valid "int x = Integer.MAX_VALUE * Integer.MAX_VALUE;" No casting is required. Outcome will be within int range.
		
		/*
		 * Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes. 
		 * For example, converting an int to an Integer, a double to a Double, and so on. If the conversion goes the other way, this is called unboxing.
		 */
		Integer boxing = 0b10100001;
		
        byte dByte = (byte) 2566;
        short dShort = (short) 2566;
        
        char dChar = (char) 0b10100001010001011010000101000101;
        // A 16-bit 'short' value:
		short aShort = (short) 0b1010000101000101;
		// Some 32-bit 'int' values:
		int anInt1 = 0b10100001010001011010000101000101;
		int anInt2 = 0b101;
		byte anInt3 = 0B101; // The B can be upper or lower case.
		
		// A 64-bit 'long' value. Note the "L" suffix:
		long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;
		
		final short[] HAPPY_FACE = {
			   (short)0b0000011111100000,
			   (short)0b0000100000010000,
			   (short)0b0001000000001000,
			   (short)0b0010000000000100,
			   (short)0b0100000000000010,
			   (short)0b1000011001100001,
			   (short)0b1000011001100001,
			   (short)0b1000000000000001,
			   (short)0b1000000000000001,
			   (short)0b1001000000001001,
			   (short)0b1000100000010001,
			   (short)0b0100011111100010,
			   (short)0b0010000000000100,
			   (short)0b0001000000001000,
			   (short)0b0000100000010000,
			   (short)0b0000011111100000
			};
		
		
		

	}
	
/*	You can use binary integral constants in code that you can verify against a specifications document, 
	such as a simulator for a hypothetical 8-bit microprocessor:
	
	public State decodeInstruction(int instruction, State state) {
		  if ((instruction & 0b11100000) == 0b00000000) {
		    final int register = instruction & 0b00001111;
		    switch (instruction & 0b11110000) {
		      case 0b00000000: return state.nop();
		      case 0b00010000: return state.copyAccumTo(register);
		      case 0b00100000: return state.addToAccum(register);
		      case 0b00110000: return state.subFromAccum(register);
		      case 0b01000000: return state.multiplyAccumBy(register);
		      case 0b01010000: return state.divideAccumBy(register);
		      case 0b01100000: return state.setAccumFrom(register);
		      case 0b01110000: return state.returnFromCall();
		      default: throw new IllegalArgumentException();
		    }
	}
*/
}


//http://stackoverflow.com/questions/477750/primitive-type-short-casting-in-java