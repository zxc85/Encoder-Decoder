class Encoder {
    private static final char[] REFERENCE_TABLE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2','3','4','5','6','7','8','9','(',')','*','+',',','-','.','/'};
    String encode(String message, char offset) {
        // encode the message
        int offsetIndex = 0;
        char[] plaintextArray = message.toCharArray();
        for (char c : REFERENCE_TABLE) {
            if (c == offset) {
                break;
            }
            offsetIndex++;
        }
        for (int i = 0; i < plaintextArray.length; i++) {
            char c = plaintextArray[i];
            for (int j = 0; j < REFERENCE_TABLE.length; j++) {
              if (c == REFERENCE_TABLE[j]) {
                int k = (j - offsetIndex) % REFERENCE_TABLE.length;
                if (k < 0) {
                  k = REFERENCE_TABLE.length + k;
                }
                plaintextArray[i] = REFERENCE_TABLE[k];
                break;
              }
            }
        }

        message = new String(plaintextArray);
        return offset + message;
    }
  }
  
  class Decoder {
    private static final char[] REFERENCE_TABLE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2','3','4','5','6','7','8','9','(',')','*','+',',','-','.','/'};
    String decode(String encodedMessage) {
      // decode the encoded message
        int offsetIndex = 0;
        char[] plaintextArray = encodedMessage.toCharArray();
        for (char c : REFERENCE_TABLE) {
            if (c == plaintextArray[0]) {
                break;
            }
            offsetIndex++;
        }
        encodedMessage ="";
        for (int i = 1; i < plaintextArray.length; i++) {
            char c = plaintextArray[i];
            for (int j = 0; j < REFERENCE_TABLE.length; j++) {
              if (c == REFERENCE_TABLE[j]) {
                int k = (j + offsetIndex) % REFERENCE_TABLE.length;
                if (k < 0) {
                  k = REFERENCE_TABLE.length + k;
                }
                plaintextArray[i] = REFERENCE_TABLE[k];
                break;
              }
            }
            encodedMessage += plaintextArray[i];
        }
        return encodedMessage;
    }
  }
  
  class Main {
    public static void main(String[] args) {
      Encoder encoder = new Encoder();
      Decoder decoder = new Decoder();
      
      String message = "HELLO WORLD";
      char offset = 'F';
      String encodedMessage = encoder.encode(message, offset);
      String decodedMessage = decoder.decode(encodedMessage);
      
      System.out.println("Original Message: " + message);
      System.out.println("Encoded Message: " + encodedMessage);
      System.out.println("Decoded Message: " + decodedMessage);
    }
  }
  
