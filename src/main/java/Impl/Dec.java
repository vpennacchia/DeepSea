package Impl;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Dec {
    String mex;
    char[] dec;
    ArrayList<Integer> SpecialChar = new ArrayList<>();
    String dec_mex = "";
    int dash;
    int i, j;
    char [] ek;

    public String decode(String encodedKey, String encodedMex) throws IOException {
        mex = encodedMex;
        dec = mex.toCharArray();
        System.out.println("--> " + printArray(dec));
        mex = "";

        addSpecialChars(); // setta la lista degli indici (special chars) e setta dec = al messaggio senza gli indici in coda
        SpecialChar = decIndexes(SpecialChar);
        dec = decKeyMex(encodedKey, dec);
        dec = rollbackCharsPositions(dec);
        dec = decodeMex(dec);

        //trasforma l'array in stringa
        dec_mex = buildMex(dec);

        return dec_mex;
    }

    public char [] rollbackCharsPositions(char [] dec){
        j = (dec.length) / 2;
        for (i = 0; i <= ((dec.length) / 2) - 1 && j <= dec.length; ++i, ++j) {
            char s = dec[j];
            dec[j] = dec[i];
            dec[i] = s;
        }
        if (dec.length % 2 == 0) {
            j = (dec.length) / 2;
            char el0 = dec[0];
            dec[0] = dec[j - 1];
            dec[j - 1] = el0;
            char el1 = dec[j];
            dec[j] = dec[dec.length - 1];
            dec[dec.length - 1] = el1;
        }

        return dec;
    }

    public char [] decodeMex(char [] dec){
        for (i = 0; i <= dec.length - 1; ++i) {
            if (dec[i] == '%' || dec[i] == '$' || dec[i] == '#' || dec[i] == '@' || dec[i] == '*' || dec[i] == '£' || dec[i] == '&') {
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
        return dec;
    }

    public char [] decKeyMex(String encodedKey, char [] dec){
        ek = encodedKey.toCharArray();
        for(i = 0; i<= dec.length - 1; ++i){
            if(i <= ek.length - 1) {
                if(i != 0) {
                    dec[i] = (char) ((dec[i] - (ek[i] + (pow((int) ek[i], 2) + dec.length + i))));
                    System.out.println("--> " + printArray(dec));
                }
                else{
                    dec[i] = (char) ((dec[i] - (ek[i] + (pow((int) ek[i], 2) + dec.length + i))));
                    System.out.println("--> " + printArray(dec));
                }
            }
            else{
                dec[i] = (char) ((dec[i] - ek[0]));
                System.out.println("--> " + printArray(dec));
            }
        }

        return dec;
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

    public String printArray(char [] a){
        String arr = "";
        for (j = 0; j <= dec.length - 1; ++j) {
            arr = arr + a[j];
        }
        return arr;
    }

    public ArrayList <Integer> decIndexes(ArrayList <Integer> SpecialChar){
        for(i = 0; i <= SpecialChar.size() - 1; ++i){
            for(i = 0; i<= SpecialChar.size() - 1; ++i){
                if(i != 0){
                    int indx = (int) (SpecialChar.get(i) - ((i * dec.length) + pow(i,2)));
                    SpecialChar.set(i, indx);
                }
                else {
                    int indx = (int) (SpecialChar.get(i) - ((1 * dec.length) + pow(i,2)));
                    SpecialChar.set(i, indx);
                }
            }
        }

        return SpecialChar;
    }

    public String buildMex(char [] dec){
        for (j = 0; j <= dec.length - 1; ++j) {
            dec_mex = dec_mex + dec[j];
        }
        return dec_mex;
    }
}
