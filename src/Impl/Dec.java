package Impl;

import java.util.ArrayList;
import java.util.HashMap;

public class Dec {
    public static String decoding(Mex mex) {
        String m = mex.getMex();
        char[] dec = m.toCharArray();
        ArrayList<Integer> SpecialChar = mex.getSpecialChar();
        String dec_mex = "";
        int i, j;

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

        for (i = 0; i <= dec.length - 1; ++i) {
            if (dec[i] == '%' || dec[i] == '$' || dec[i] == '#' || dec[i] == '@') {
                dec[i] = ' ';
             }

            if (dec[i] != ' ') {
                if(i % 2 == 0) {
                    if(SpecialChar.contains(i)){
                        dec[i] = (char) ( (int) (dec[i]) - 1);
                    }
                    else {
                        dec[i] = (char) (((int) dec[i] - (i * 3)) - 5 - dec.length - i);
                    }
                }
                else {
                    if(SpecialChar.contains(i)){
                        dec[i] = (char) ( (int) (dec[i]) + 1);
                    }
                    else {
                        dec[i] = (char) (((int) dec[i] - (((i * 6) + 13) + dec.length + (i + 4) * (dec.length / 2))));
                    }
                }
            }
        }

        for (j = 0; j <= dec.length - 1; ++j) {
                dec_mex = dec_mex + dec[j];
        }

        return dec_mex;
    }
}


