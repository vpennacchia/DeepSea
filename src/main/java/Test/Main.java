package main.java.Test;

import main.java.Impl.Dec;
import main.java.Impl.Enc;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Enc enc = new Enc();
        Dec dec = new Dec();
        Scanner scanner = new Scanner(System.in);
        System.out.println("vuoi criptare o decriptare un messaggio?");
        String encodec = scanner.nextLine();
        if(encodec.equals("criptare")) {
            System.out.println("Inserisci messaggio da criptare");
            String a = scanner.nextLine();
            String mex = enc.encoding(a);
            System.out.println("Messaggio criptato: " + mex);
        }
        else if(encodec.equals("decriptare")) {
            System.out.println("Inserisci messaggio da decriptare");
            String m = scanner.nextLine();
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Inserisci chiave di cifratura");
            String key = scanner2.nextLine();
            String decr = dec.decoding(m , key);
            System.out.println("Messaggio decriptato: " + decr);
        }

    }
}
