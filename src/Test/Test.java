package Test;

import Impl.Dec;
import Impl.Enc;

import java.util.Scanner;

public class Test {
    public static void main(String [] args){
       Enc enc = new Enc();
       Dec dec= new Dec();
       Scanner scanner = new Scanner(System.in);
       System.out.println("vuoi criptare o decriptare un messaggio?");
       String encodec = scanner.nextLine();
       if(encodec.equals("criptare")) {
                System.out.println("Inserisci messaggio da criptare");
               String a =  scanner.nextLine();
               String mess = enc.encoding(a);
               System.out.println("Messaggio criptato: " + mess);
           }

       else if(encodec.equals("decriptare")) {
                System.out.println("Inserisci messaggio da decriptare");
                String m = scanner.nextLine();
                String decr = dec.decoding(m);
                System.out.println(decr);
            }
        }
    }


