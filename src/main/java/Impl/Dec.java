package main.java.Impl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Dec {
    public static String decode(String encodedKey) throws IOException {
        String mex = readFile();
        char[] dec = mex.toCharArray();
        ArrayList<Integer> SpecialChar = new ArrayList<>();
        String dec_mex = "";
        int i, j;
        mex = "";
        for(i = 0; i<= dec.length - 1; ++i){
            if(dec[i] == '_' && dec[i + 1] == dec[0] && dec[i + 2] == '_'){
                for(j = i + 3; j <= dec.length - 1; ++j){
                    int dash = j;
                    if(dec[dash] == '-' && dash != dec.length - 1) {
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
            }
            else{
                mex = mex + dec[i];
            }
        }
        dec = mex.toCharArray();

        char [] ek = encodedKey.toCharArray();
        for(i = 0; i<= dec.length - 1; ++i){
            if(i <= ek.length - 1) {
                if(i != 0) {
                    dec[i] =  (char) ((dec[i] - (ek[i] * ek[i])));
                }
                else{
                    dec[i] = (char) ((dec[i] - ek[0]));
                }
            }
            else{
                dec[i] = (char) ((dec[i] - ek[0]));
            }
        }


        if(mex.length() == 1){
            if(SpecialChar.contains(0)){
                dec[0] = (char) ((int) (dec[0]) - 1);
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
                            dec[i] = (char) ((int) (dec[i]) - 1);
                        } else {
                            dec[i] = (char) (((int) dec[i] - (i * 3)) - 5 - dec.length - i);
                        }
                    } else {
                        if (SpecialChar.contains(i)) {
                            dec[i] = (char) ((int) (dec[i]) - 1);
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
        writeFile(dec_mex);
        return dec_mex;
    }


    public static String readFile() throws IOException {
        String path = System.getProperty("user.dir");
        BufferedReader reader = new BufferedReader(new FileReader(path + "/Mex.txt"));
        String line = reader.readLine();

        return line;
    }


    public static void writeFile(String mex){
        String path = System.getProperty("user.dir");
        try {
            File file = new File(path + "/Mex.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mex);
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

}