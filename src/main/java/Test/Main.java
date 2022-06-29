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
        System.out.println("vuoi criptare o decriptare un messaggio?");
        String encodec = scanner.nextLine();
        if (encodec.equals("criptare")) {
            System.out.println("Inserisci messaggio da criptare");
            String a = scanner.nextLine();
            enc.encode(a);
            System.out.println("Nella stessa directory è stato creato il file Mex.txt con all'interno il messaggio criptato, per decriptare il messaggio ricordare al mittente che questo file deve trovarsi nello stesso path dell'eseguibile");
        } else if (encodec.equals("decriptare")) {
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Il file con il messaggio criptato si trova nello stesso percorso file del programma che stai eseguendo? ( digita y o n)");
            String file = scanner2.nextLine();
            if (file.equals("y")) {
                System.out.println("Inserisci chiave di cifratura");
                String key = scanner2.nextLine();
                dec.decode(key);
                System.out.println("Il messaggio decriptato è stato scritto nel file Mex.txt");
            } else {
                System.out.println("Allora sposta il file ed esegui il programma");
            }

        }
    }
}
