package main.java.Impl;


import resource.Mex;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*VP01*/
public class Enc {
    //metodo di codifica
    public static Mex encoding(String mex) {
        String enc_mex="";
        char [] encode= mex.toCharArray();
        int i = 0, space=0, j=0;
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
        Mex m = new Mex();
        String enc_mexs = convertToUTF8(enc_mex);
        m.setMex(enc_mexs);
        m.setSpecialChar(SpecialChar);

        return m;
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
