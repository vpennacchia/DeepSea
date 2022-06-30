package main.java.Test;

import main.java.Impl.Dec;
import main.java.Impl.Enc;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Enc enc = new Enc();
        Dec dec = new Dec();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("vuoi criptare o decriptare un messaggio (digita c o d)?");
        String encodec = scanner.nextLine();
        if (encodec.equals("c")) {
            System.out.println("Inserisci messaggio da criptare");
            String a = scanner.nextLine();
            System.out.println("messaggio criptato: " + enc.encode(a));
        } else if (encodec.equals("d")) {

                System.out.println("Inserisci chiave di cifratura");
                String key = scanner2.nextLine();
                System.out.println("Inserisci il messaggio criptato");
                String mex = scanner2.nextLine();
                System.out.println("Messaggio decriptato: " + dec.decode(key, mex));
            }
        }
}
