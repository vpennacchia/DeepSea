package Impl;

import resource.Mex;
import resource.MexInt;

import java.util.ArrayList;

public class Dec {
    public static String decoding(Mex mex) {
        String m = mex.getMex();
        char[] dec = m.toCharArray();
        ArrayList<Integer> SpecialChar = mex.getSpecialChar();
        String dec_mex = "";
        int i, j;

        if(m.length() == 1){
            if(SpecialChar.contains(0)){
                dec[0] = (char) ((int) (dec[0]) - 50);
            }
            else{
                dec[0] =  (char) (((int) dec[0] - ((1 * 3) + 5) - dec.length - 1));
            }
        }
        else {

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
                    if (i % 2 == 0) {
                        if (SpecialChar.contains(i)) {
                            dec[i] = (char) ((int) (dec[i]) - 50);
                        } else {
                            dec[i] = (char) (((int) dec[i] - (i * 3)) - 5 - dec.length - i);
                        }
                    } else {
                        if (SpecialChar.contains(i)) {
                            dec[i] = (char) ((int) (dec[i]) - 40);
                        } else {
                            dec[i] = (char) (((int) dec[i] - (((i * 6) + 13) + dec.length + (i + 4) * (dec.length / 2))));
                        }
                    }
                }
            }
        }

        for (j = 0; j <= dec.length - 1; ++j) {
                dec_mex = dec_mex + dec[j];
        }
        return dec_mex;
    }

    public static int decoding(MexInt mex){
        String m = mex.getCriptMexStr();
        char [] m2 = m.toCharArray();
        int cr_mex = mex.getCriptMex();
        m = mex.getCriptMexStr();
        cr_mex = cr_mex -  ((((int) m2[0]) * m2.length) + m2[m2.length - 1]);

        return cr_mex;
    }
  }



