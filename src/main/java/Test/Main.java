package Test;
import Impl.Decode;
import Impl.Encode;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;


public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_RED = "\u001B[31m";
        Encode encode = new Encode();
        Decode dec = new Decode();

        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("\n--------------------------------------------------  DeepSea   --------------------------------------------------\n");
        System.out.println(ANSI_BLUE + "vuoi criptare o decriptare un messaggio (digita c o d)?" + ANSI_RESET);
        String operation = scanner.nextLine();

        if (operation.equals("c")) {
            System.out.println(ANSI_BLUE + "Inserisci messaggio da criptare, numero massimo di caratteri 4100" + ANSI_RESET);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
            String readMex = buffReader.readLine();
            System.out.println("len: " + readMex.length());

            String encoded_mex = encode.encode(readMex);
            System.out.println("\n");
            File myObj = new File("mex.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(myObj), encoded_mex.length());
            writer.write(encoded_mex);
            writer.close();

            System.out.println(ANSI_GREEN + "La codifica è andata a buon fine troverai il messaggio criptato nel file mex.txt\n----------------------------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_RED + "messaggio criptato: " + ANSI_RESET + encoded_mex);
            System.out.println(ANSI_PURPLE + "chiave di cifratura: " + ANSI_RESET + encode.getKey());


        } else if (operation.equals("d")) {
            Scanner operationInput = new Scanner(System.in);
            System.out.println("Prima di procedere, hai inserito il file mex.txt nella stessa cartella dove questo programma è in esecuzione?");
            String yesorno = operationInput.nextLine();
            if (yesorno.equals("n")) {
                System.out.println(ANSI_RED + "riesegui il programma dopo averlo fatto" + ANSI_RESET);
                System.exit(0);
            } else {
                System.out.println(ANSI_BLUE + "Leggo il messaggio criptato..." + ANSI_RESET + "\n");
                Thread.sleep(3000);
            }

            System.out.println(ANSI_PURPLE + "Messaggio preso dal file: " + ANSI_RESET + "\n");
            File myObj = new File("mex.txt");
            Scanner myReader = new Scanner(myObj);
            String mex = myReader.nextLine();
            System.out.println(mex);

            System.out.println(ANSI_BLUE + "Inserisci chiave di cifratura" + ANSI_RESET);
            String key = scanner2.nextLine();
            System.out.println(ANSI_GREEN + "\n----------------------------------------------\nMessaggio decriptato: " + ANSI_RESET + dec.decode(key, mex));
            scanner.close();
        }

    }

}


