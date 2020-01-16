package euasejsim;

// JDK 8: 
// javac euasejsim/MySelect.java
// java euasejsim.MySelect
// JDK 9:
// javac --add-modules java.smartcardio euasejsim/MySelect.java
// java euasejsim.MySelect

import java.util.List;
import javax.smartcardio.*;

public class MySelect {
 public static void main(String[] args) {
  try {
   // Display the list of terminals
   TerminalFactory factory = TerminalFactory.getDefault();
   List<CardTerminal> terminals = factory.terminals().list();
   System.out.println("Terminals: " + terminals);

   // Use the first terminal
   CardTerminal terminal = terminals.get(0);

   // Connect wit hthe card
   Card card = terminal.connect("*");
   System.out.println("card: " + card);
   CardChannel channel = card.getBasicChannel();

   // Send Select Applet command
   //byte[] aid = {(byte)0xA0, 0x00, 0x00, 0x00, 0x62, 0x03, 0x01, 0x0C, 0x06, 0x01};  
   //ResponseAPDU answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x04, 0x00, DF_TELECOM));
   
   // SELECT = [0xA0, 0xA4, 0x00, 0x00, 0x02]
   // DF_GSM = [0x7F, 0x20]
   
   byte[] DF_GSM = {(byte)0x7F, 0x20};
   ResponseAPDU answer = channel.transmit(new CommandAPDU(0xA0, 0xA4, 0x00, 0x00, DF_GSM));
   System.out.println("answer SELECT DF_GSM 7F 20: " + answer.toString());

   //GET RESPONSE for EF 7F20
   // /send A0C000001A
   answer = channel.transmit(new CommandAPDU(0xA0, 0xC0, 0x00, 0x00, 0x1A));
   System.out.println("answer GET RESPONSE for EF 7F20: " + answer.toString());
   byte r[] = answer.getData();
   for (int i=0; i<r.length; i++)
      System.out.printf(" %02X", r[i]);
   System.out.println();
   
   // //select EF IMSI - 6F07
   // /send A0A40000026F07
   byte[] EF_IMSI = {(byte)0x6F, 0x07};
   answer = channel.transmit(new CommandAPDU(0xA0, 0xA4, 0x00, 0x00, EF_IMSI));
   System.out.println("answer select EF IMSI - 6F07: " + answer.toString());
   r = answer.getData();
   for (int i=0; i<r.length; i++)
      System.out.printf(" %02X", r[i]);
   System.out.println();
   
   // //GET RESPONSE for EF 6F07 file
   // /send A0C000000F
   answer = channel.transmit(new CommandAPDU(0xA0, 0xC0, 0x00, 0x00, 0x0F));
   System.out.println("answer GET RESPONSE for EF 6F07 file: " + answer.toString());
   r = answer.getData();
   for (int i=0; i<r.length; i++)
      System.out.printf(" %02X", r[i]);
   System.out.println();
   
   // //READ BINARY from EF 6F07 - IMSI
   // //IMSI = 2962100425582677
   // /send A0B0000009
   answer = channel.transmit(new CommandAPDU(0xA0, 0xB0, 0x00, 0x00, 0x09));
   System.out.println("answer READ BINARY from EF 6F07 - IMSI: " + answer.toString());
   r = answer.getData();
   for (int i=0; i<r.length; i++)
      System.out.printf(" %02X", r[i]);
   System.out.println();



   // Disconnect the card
   card.disconnect(false);
  } catch(Exception e) {
   System.out.println("Ouch: " + e.toString());
  }
 }
}

