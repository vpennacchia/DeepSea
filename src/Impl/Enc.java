package Impl;


import java.util.ArrayList;

/*VP01*/
public class Enc {
    //metodo di codifica
    public static String encoding(String mex) {
        String enc_mex = "";
        char[] encode = mex.toCharArray();
        int i = 0, space = 0, j = 0;

        //caso in cui la stringa ha lunghezza 1
        if (mex.length() == 1) {
            encode[0] = (char) (((int) encode[0] + ((1 * 3) + 5) + encode.length + 1));
        } else {

            for (i = 0; i <= encode.length - 1; ++i) {
                //criptazione dei vari caratteri
                if (encode[i] != ' ') {
                    if (i % 2 == 0) {
                        encode[i] = (char) (((int) encode[i] + ((i * 3) + 5) + encode.length + i));
                    } else {
                        encode[i] = (char) (((int) encode[i] + ((i * 6) + 13) + encode.length + (i + 4) * (encode.length / 2)));
                    }
                }

                //criptazione spazi
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
        }
        //manipoliamo la stringa, invertiamo  e scambiamo alcuni caratteri
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

       for(j =0;j <=encode.length -1; ++j) {
        enc_mex = enc_mex + encode[j];
         }

        return enc_mex;
}

}
