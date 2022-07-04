package main.java.Impl;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class Enc {
    int i, space = 0, j;
    ArrayList<Integer> SpecialChar = new ArrayList<>();
    char [] encode;
    String enc_mex = "";
    String encodedKey = "";
    String key_mex = "";
    String Key = "";

    //metodo di codifica
    public String encode(String mex) throws NoSuchAlgorithmException {
        encode = mex.toCharArray();
        SpecialChar = new ArrayList<>();

        for (i = 0; i <= encode.length - 1; ++i) {
            if (encode[i] != ' ') {
                encode[i] = encodeCharacter(encode[i]);
                System.out.println("--> " + printArray(encode));
            }
            if (encode[i] == ' ') {
                encode[i] = encodeSpaces();
                System.out.println("--> " + printArray(encode));
            }
        }

        mixCharacters();
        enc_mex = buildMex(enc_mex);

        Key = generateKey();
        encKeyMex();
        key_mex = buildMex(key_mex);

        enc_mex = key_mex;
        SpecialChar = encIndexes();
        addIndexes();
        System.out.println("--> " + enc_mex);
        return enc_mex;
    }


    public char encodeCharacter(char character) {
        int utf = (char) (((int) character + ((i * 3) + 5) + encode.length + i));
        int utf2 = (char) (((int) character + ((i * 6) + 13) + encode.length + (i + 4) * (encode.length / 2)));

        if (i % 2 == 0) {
            if (utf == 0 || (utf >= 127 && utf <= 159) || utf > 1824) {
                character = (char) ((int) (character) + 1);
                SpecialChar.add(i);
            } else {
                character = (char) (((int) character + ((i * 3) + 5) + encode.length + i));
            }
        } else {
            if (utf2 == 0 || (utf2 >= 127 && utf2 <= 159) || utf2 > 1824) {
                character = (char) ((int) (character) + 1);
                SpecialChar.add(i);
            } else {
                character = (char) (((int) character + ((i * 6) + 13) + encode.length + (i + 4) * (encode.length / 2)));
            }
        }
        return character;
    }

    public String getKey(){
        return encodedKey;
    }

    public char encodeSpaces(){
        if (space > 3) {
            space = 0;
        }
        if (space == 0) {
            encode[i] = '%';
        }
        if (space == 1) {
            encode[i] = '$';
        }
        if (space == 2) {
            encode[i] = '@';
        }
        if (space == 3) {
            encode[i] = '#';
        }
        ++space;

        return encode[i];
    }

    public String printArray(char [] a){
        String arr = "";
        for (j = 0; j <= encode.length - 1; ++j) {
            arr = arr + a[j];
        }

        return arr;
    }

    public void encKeyMex(){
        char [] ek = Key.toCharArray();
        for(i = 0; i<= encode.length - 1; ++i){
            if(i <= ek.length - 1) {
                if(i != 0) {
                    encode[i] = (char) ((encode[i] + (ek[i] * ek[i])));
                    System.out.println("--> " + printArray(encode));
                }
                else{
                    encode[i] = (char) ((encode[i] + ek[0]));
                    System.out.println("--> " + printArray(encode));
                }
            }
            else{
                encode[i] = (char) ((encode[i] + ek[0]));
                System.out.println("--> " + printArray(encode));
            }
        }
    }

    public void mixCharacters(){
        j = (encode.length) / 2;
        for (i = 0; i <= ((encode.length) / 2) - 1 && j <= encode.length; ++i, ++j) {
            char s = encode[i];
            encode[i] = encode[j];
            encode[j] = s;
        }
        if (encode.length % 2 == 0) {
            j = (encode.length) / 2;
            char el0 = encode[0];
            encode[0] = encode[j - 1];
            encode[j - 1] = el0;
            char el1 = encode[j];
            encode[j] = encode[encode.length - 1];
            encode[encode.length - 1] = el1;
        }
    }

    public String buildMex(String enc_mex) {
        for (j = 0; j <= encode.length - 1; ++j) {
            enc_mex = enc_mex + encode[j];
        }

        return enc_mex;
    }

    public void addIndexes(){
        enc_mex =  enc_mex + "_" + encode[0]  + "_" + "-";
        for(i=0; i <= SpecialChar.size() - 1; ++i){
            enc_mex = enc_mex + SpecialChar.get(i) + "-";
        }
    }

    public  ArrayList<Integer> encIndexes(){
        for(i = 0; i<= SpecialChar.size() - 1; ++i){
            if(i != 0){
                int indx = SpecialChar.get(i) + (i * encode.length);
                SpecialChar.set(i, indx);
            }
            else {
                int indx = SpecialChar.get(i) + (1 * encode.length);
                SpecialChar.set(i, indx);
            }
        }
        return SpecialChar;
    }

    public String generateKey() throws NoSuchAlgorithmException {
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return encodedKey;
    }
}