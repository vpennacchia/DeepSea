package Test;
import Impl.Dec;
import Impl.Enc;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        /* settaggi */
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_RED = "\u001B[31m";


        /* output effettivo */
        Enc enc = new Enc();
        Dec dec = new Dec();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("vuoi criptare o decriptare un messaggio (digita c o d)?");
        String encodec = scanner.nextLine();
        if (encodec.equals("c")) {
            System.out.println(ANSI_BLUE + "Inserisci messaggio da criptare" + ANSI_RESET);
            String a = scanner.nextLine();
            String encoded_mex = enc.encode(a);
            System.out.println("\n");
            System.out.println(ANSI_GREEN +"La codifica è andata a buon fine\n----------------------------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "messaggio criptato: " + ANSI_RESET + encoded_mex);
            System.out.println(ANSI_BLUE + "chiave di cifratura: " + ANSI_RESET + enc.getKey());
        } else if (encodec.equals("d")) {
                System.out.println(ANSI_RED + "Inserisci il messaggio criptato" + ANSI_RESET);
                String mex = scanner2.nextLine();
                System.out.println(ANSI_PURPLE + "Inserisci chiave di cifratura"+ ANSI_RESET);
                String key = scanner2.nextLine();
                System.out.println(ANSI_GREEN + "\n----------------------------------------------\nMessaggio decriptato: " + ANSI_RESET + dec.decode(key, mex));
            }
        }
}
