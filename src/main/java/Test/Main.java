package Test;

import Impl.Dec;
import Impl.Enc;

import java.io.IOException;
import java.csecurity.NoSuchAlgorithmException;
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
            String encoded_mex = enc.encode(a);
            System.out.println("\n");
            System.out.println("La codifica è andata a buon fine\n----------------------------------------------------------");
            System.out.println("messaggio criptato: " + encoded_mex);
            System.out.println("chiave di cifratura: " + enc.getKey());
        } else if (encodec.equals("d")) {
                System.out.println("Inserisci il messaggio criptato");
                String mex = scanner2.nextLine();
                System.out.println("Inserisci chiave di cifratura");
                String key = scanner2.nextLine();
                System.out.println("\n----------------------------------------------\nMessaggio decriptato: " + dec.decode(key, mex));
            }
        }
}
