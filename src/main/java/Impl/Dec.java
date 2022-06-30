package main.java.Impl;

import java.io.*;
import java.util.ArrayList;

public class Dec {
    String mex;
    static char[] dec;
    static ArrayList<Integer> SpecialChar = new ArrayList<>();
    String dec_mex = "";
    static int i;
    static int j;
    static int dash;
    char [] ek;

    public String decode(String encodedKey, String encMex) throws IOException {
        mex = encMex;
        dec = mex.toCharArray();
        System.out.println("--> " + printArray(dec));
        mex = "";

        //aggiunge al nostro arraylist tutti gli indici presenti nel messaggio criptato
        addSpecialChars();
        //prosegue con la decriptazione utilizzando la chiave di cifratura
        decKeyMex(encodedKey);
        //mette i caratteri nella posizione originaria
        rollbackCharsPositions();
        //effettua la vera e propria decodifica del messaggio
        decodeMex();

        //trasforma l'array in stringa
        dec_mex = buildMex(dec_mex);

        return dec_mex;
    }

    public void addSpecialChars() {
        for (i = 0; i <= dec.length - 1; ++i) {
            if (dec[i] == '_' && dec[i + 1] == dec[0] && dec[i + 2] == '_') {
                for (j = i + 3; j <= dec.length - 1; ++j) {
                    dash = j;
                    if (dec[dash] == '-' && dash != dec.length - 1) {
                        ++dash;
                        String num = "";
                        while (dec[dash] != '-') {
                            num = num + dec[dash];
                            ++dash;
                        }
                        SpecialChar.add(Integer.valueOf(num));
                    }
                }
                break;
            } else {
                mex = mex + dec[i];
            }
        }
        dec = mex.toCharArray();
        System.out.println("--> " + printArray(dec));
    }

   public void decKeyMex(String encodedKey){
       ek = encodedKey.toCharArray();
       for(i = 0; i<= dec.length - 1; ++i){
           if(i <= ek.length - 1) {
               if(i != 0) {
                   dec[i] =  (char) ((dec[i] - (ek[i] * ek[i])));
                   System.out.println("--> " + printArray(dec));
               }
               else{
                   dec[i] = (char) ((dec[i] - ek[0]));
                   System.out.println("--> " + printArray(dec));
               }
           }
           else{
               dec[i] = (char) ((dec[i] - ek[0]));
               System.out.println("--> " + printArray(dec));
           }
       }
   }

  public void rollbackCharsPositions(){
       j = (dec.length) / 2;
       for (i = 0; i <= ((dec.length) / 2) - 1 && j <= dec.length; ++i, ++j) {
           char s = dec[j];
           dec[j] = dec[i];
           dec[i] = s;
           System.out.println("--> " + printArray(dec));
       }
       if (dec.length % 2 == 0) {
           j = (dec.length) / 2;
           char el0 = dec[0];
           dec[0] = dec[j - 1];
           dec[j - 1] = el0;
           char el1 = dec[j];
           dec[j] = dec[dec.length - 1];
           dec[dec.length - 1] = el1;
           System.out.println("--> " + printArray(dec));
       }
   }

    public void decodeMex(){
        for (i = 0; i <= dec.length - 1; ++i) {
            if (dec[i] == '%' || dec[i] == '$' || dec[i] == '#' || dec[i] == '@') {
                dec[i] = ' ';
                System.out.println("--> " + printArray(dec));
            }

            if (dec[i] != ' ') {
                if (i % 2 == 0) {
                    if (SpecialChar.contains(i)) {
                        dec[i] = (char) ((int) (dec[i]) - 1);
                        System.out.println("--> " + printArray(dec));
                    } else {
                        dec[i] = (char) (((int) dec[i] - (i * 3)) - 5 - dec.length - i);
                        System.out.println("--> " + printArray(dec));
                    }
                } else {
                    if (SpecialChar.contains(i)) {
                        dec[i] = (char) ((int) (dec[i]) - 1);
                        System.out.println("--> " + printArray(dec));
                    } else {
                        dec[i] = (char) (((int) dec[i] - (((i * 6) + 13) + dec.length + (i + 4) * (dec.length / 2))));
                        System.out.println("--> " + printArray(dec));
                    }
                }
            }
        }
    }

    public String printArray(char [] a){
        String arr = "";
        for (j = 0; j <= dec.length - 1; ++j) {
            arr = arr + a[j];
        }

        return arr;

    }

  public String buildMex(String dec_mex){
      for (j = 0; j <= dec.length - 1; ++j) {
          dec_mex = dec_mex + dec[j];
      }
       return dec_mex;
  }
}