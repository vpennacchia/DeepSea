package Impl;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.time.LocalTime;

import static java.lang.Math.pow;

public class Encode {

    int i, space = 0, j;
    ArrayList<Integer> SpecialChar = new ArrayList<>();
    char [] encode;
    String enc_mex = "";
    String encodedKey = "";
    String key_mex = "";
    String Key = "";
    int hm = Integer.parseInt(LocalTime.now().toString().substring(0,2)) + Integer.parseInt(LocalTime.now().toString().substring(3,5));

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

        encode = mixCharacters(encode);
        enc_mex = buildMex(encode);

        Key = generateKey();
        encode = encKeyMex(encode, Key);
        key_mex = buildMex(encode);

        enc_mex = key_mex;
        enc_mex = addHm(enc_mex);

        System.out.println("--> " + enc_mex);
        return generateMatrix(enc_mex);
    }


    public char encodeCharacter(char character) {
        int utf = (char) (((int) character + i  + encode.length + hm));
        int utf2 = (char) (((int) character + i  + hm + (encode.length / 2)));

        if (i % 2 == 0) {
            character = (char) utf;
        } else {
            character = (char) utf2;
        }
        return character;
    }

    public String getKey(){
        return encodedKey + "|" + enc_mex.length();
    }

    public char encodeSpaces(){
        if (space > 6) {
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
        if (space == 4) {
            encode[i] = '*';
        }
        if (space == 5) {
            encode[i] = '£';
        }
        if (space == 6) {
            encode[i] = '&';
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

    public char [] encKeyMex(char [] encode, String Key){
        char [] ek = Key.toCharArray();
        for(i = 0; i<= encode.length - 1; ++i){
            if(i <= ek.length - 1) {
                if(i != 0) {
                    encode[i] = (char) ((encode[i] + (ek[i] + (pow((int) ek[i], 2) + encode.length + i))));
                    System.out.println("--> " + printArray(encode));
                }
                else{
                    encode[i] = (char) ((encode[i] + (ek[i] + (pow((int) ek[i], 2) + encode.length + i))));
                    System.out.println("--> " + printArray(encode));
                }
            }
            else{
                encode[i] = (char) ((encode[i] + ek[0]));
                System.out.println("--> " + printArray(encode));
            }
        }

        return encode;
    }

    public String addHm(String mex) {
        mex = mex + "_V_" + hm;
        return mex;
    }

    public char [] mixCharacters(char [] encode){
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

        return encode;
    }

    public String buildMex(char [] encode) {
        String enc_mex = "";
        for (j = 0; j <= encode.length - 1; ++j) {
            enc_mex = enc_mex + encode[j];
        }

        return enc_mex;
    }

    public String generateKey() throws NoSuchAlgorithmException {
        if(Integer.parseInt(LocalTime.now().toString().substring(0,2)) % 2 == 0){
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
        else{
            SecretKey secretKey = KeyGenerator.getInstance("DESede").generateKey();
            encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }

        return encodedKey;
    }

    public String generateRandomString(int len){

        String AB = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔØÙÚÛÜÝÞßàáâãæçèéêëìíîïðñòóôõƯưƱƲƳƴƵƶƷƸƹƺƻƼƽƾƿǀǁǂǃǄǅǆǇǈǉǊǋǌǍǎǏǐǑǒǓǔǕǖȹȺȻȼȽȾȿɀɁɂɃɄɅɆɇɈɉɊɋɍɎɏɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺ";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();

    }

    public String generateMatrix(String mex){
        List <String>  s = new ArrayList<>();
        String newMex = "";
        int len = mex.length();

        for(i = 0; i<= mex.length() -1; ++i){
            String random = generateRandomString(len);
            s.add(random);
        }

        char [] t = mex.toCharArray();
        for(i = 0; i< s.size(); ++i){
            String tmp = s.get(i);
            StringBuilder myStr = new StringBuilder(tmp);
            myStr.setCharAt(i, t[i]);
            tmp = String.valueOf(myStr);
            newMex = newMex + tmp;
        }

        return newMex;
    }
}