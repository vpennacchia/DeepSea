package main.java.Test;

import main.java.Impl.Dec;
import main.java.Impl.Enc;
import resource.Mex;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> el = new ArrayList<>();

    public static ArrayList<Integer>  printEl(int a) {
        Scanner scanner = new Scanner(System.in);
        int i;
        if(a != -1) {
            el.add(a);
        }
        System.out.println("chiave: ");
        int m = scanner.nextInt();
        if(m != -1){
            printEl(m);
        }
        return el;
    }

    public static void main(String [] args) {
        Enc enc = new Enc();
        Dec dec = new Dec();
        Scanner scanner = new Scanner(System.in);
        Mex mess = null;
        System.out.println("vuoi criptare o decriptare un messaggio?");
        String encodec = scanner.nextLine();
        if(encodec.equals("criptare")) {
            System.out.println("Inserisci messaggio da criptare");
            String a = scanner.nextLine();
            mess = enc.encoding(a);
            System.out.println("Messaggio criptato: " + mess.getMex());
            System.out.println("chiavi segrete da dare insieme al messaggio: " + mess.getSpecialChar().toString());
        }
        else if(encodec.equals("decriptare")) {
            System.out.println("Inserisci messaggio da decriptare");
            String m = scanner.nextLine();
            System.out.println("digita una per una le chiavi che hai ricevuto insieme al messaggio, invia il numero '-1' quando hai inserito tutte le chiavi");
            System.out.println("chiave: ");
            int chiave = scanner.nextInt();
            ArrayList<Integer> el = printEl(chiave);
            Mex mes = new Mex();
            mes.setMex(m);
            mes.setSpecialChar(el);

            String decr = dec.decoding(mes);
            System.out.println(decr);
        }

        }
    }


