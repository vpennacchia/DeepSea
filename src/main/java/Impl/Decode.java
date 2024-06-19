package Impl;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Decode {
    String mex;
    char[] dec;
    String dec_mex = "";
    int i, j;
    char [] ek;
    int hm = 0;

    public String decode(String encodedKey, String encodedMex) {
        int len = getLen(encodedKey);
        mex = getMexFromMatrix(encodedMex, len);
        encodedKey = removeLen(encodedKey);

        String hms = mex.substring(mex.length()- 4);

        if(hms.charAt(0) == '_') {
            hm = Integer.parseInt(hms.substring( hms.length() - 1));
        }

        else {
            hms = mex.substring( mex.length()- 5);
            hm = Integer.parseInt(hms.substring( hms.length() - 2));
        }
        mex = mex.replace(hms, "");

        dec = mex.toCharArray();
        System.out.println("--> " + printArray(dec));
        mex = "";

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
                    dec[i] = (char) (((int) dec[i] - i  - dec.length - hm));
                    System.out.println("--> " + printArray(dec));
                } else {
                    dec[i] = (char) ((int) dec[i] - ((i  + hm +  (dec.length / 2) )));
                    System.out.println("--> " + printArray(dec));
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

    public String printArray(char [] a){
        String arr = "";
        for (j = 0; j <= dec.length - 1; ++j) {
            arr = arr + a[j];
        }
        return arr;
    }

    public String buildMex(char [] dec){
        for (j = 0; j <= dec.length - 1; ++j) {
            dec_mex = dec_mex + dec[j];
        }
        return dec_mex;
    }

    public int getLen(String key){
        char [] key_arr = key.toCharArray();
        String len = "";

        for(int i = key_arr.length -1; i > 0; --i ){
            if(key_arr[i] == '|'){
                break;
            }

            len = key_arr[i] + len;
        }
        return Integer.parseInt(len);
    }

    public String removeLen(String key){
        char [] key_arr = key.toCharArray();
        String k = "";
        for(int i = 0; i <= key_arr.length - 1; ++i ){
            if (key_arr[i] == '|'){
                break;
            }
            k = k + key_arr[i];
        }
        return k;
    }

    public String getMexFromMatrix(String mex, int len){
        List<String> s = new ArrayList<>();
        String trueMex = "";
        for(int i = 0; i <= mex.length(); i = i + len) {
            if (i == mex.length()) {
                break;
            }
            int j = i + len;
            s.add(mex.substring(i, j));
        }

        for(i = 0; i< s.size(); ++i){
            trueMex = trueMex + s.get(i).charAt(i);
        }

        return trueMex;
    }
}