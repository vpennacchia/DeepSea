package main.java.Impl;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

/*VP01*/
public class Enc {
    //metodo di codifica
    public String encoding(String mex) throws NoSuchAlgorithmException {
        String enc_mex="";
        char [] encode= mex.toCharArray();
        int i, space = 0, j;
        ArrayList<Integer> SpecialChar = new ArrayList<>();

        if(mex.length() == 1){
            int utf =  (char) (((int) encode[0] + ((1 * 3) + 5) + encode.length + 1));
            if (utf == 0 || (utf >= 127 && utf <= 159) || utf > 1824) {
                encode[0] = (char) ((int) (encode[0]) + 1);
                SpecialChar.add(0);
            }
            else{
                encode[0] = (char) (((int) encode[0] + ((1 * 3) + 5) + encode.length + 1));
            }
        }
        else {

            for (i = 0; i <= encode.length - 1; ++i) {
                int utf = (char) (((int) encode[i] + ((i * 3) + 5) + encode.length + i));
                int utf2 = (char) (((int) encode[i] + ((i * 6) + 13) + encode.length + (i + 4) * (encode.length / 2)));
                if (encode[i] != ' ') {
                    if (i % 2 == 0) {
                        if (utf == 0 || (utf >= 127 && utf <= 159) || utf > 1824) {
                            encode[i] = (char) ((int) (encode[i]) + 1);
                            SpecialChar.add(i);
                        } else {
                            encode[i] = (char) (((int) encode[i] + ((i * 3) + 5) + encode.length + i));
                        }
                    } else {
                        if (utf2 == 0 || (utf2 >= 127 && utf2 <= 159) || utf2 > 1824) {
                            encode[i] = (char) ((int) (encode[i]) + 1);
                            SpecialChar.add(i);
                        } else {
                            encode[i] = (char) (((int) encode[i] + ((i * 6) + 13) + encode.length + (i + 4) * (encode.length / 2)));
                        }
                    }
                }

                if (encode[i] == ' ') {
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
                }
            }

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

        for (j = 0; j <= encode.length - 1; ++j) {
            enc_mex = enc_mex + encode[j];
        }

        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("chiave di cifratura: " + encodedKey);
        String key_mex = "";


        char [] ek = encodedKey.toCharArray();
        for(i = 0; i<= encode.length - 1; ++i){
            if(i <= ek.length - 1) {
                if(i != 0) {
                    encode[i] = (char) ((encode[i] + (ek[i] * ek[i])));
                }
                else{
                    encode[i] = (char) ((encode[i] + ek[0]));
                }
            }
            else{
                encode[i] = (char) ((encode[i] + ek[0]));
            }
        }

        for (j = 0; j <= encode.length - 1; ++j) {
            key_mex = key_mex + encode[j];
        }

        enc_mex = key_mex;



        enc_mex =  enc_mex + "_" + encode[0]  + "_" + "-";
        for(i=0; i <= SpecialChar.size() - 1; ++i){
            enc_mex = enc_mex + SpecialChar.get(i) + "-";
        }

        enc_mex = convertToUTF8(enc_mex);


        return enc_mex;
    }

    public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), StandardCharsets.UTF_8.displayName());
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

}