package Impl;
import java.lang.Object.*;

/*VP01*/
public class enc {
    public static String encoding(String mex){
        String enc_mex = " ";
        char [] encode= mex.toCharArray();
        int i = 0, space=0;

        for(i=0; i<= encode.length - 1; ++i){
            if(((Object) encode[i]).getClass().getSimpleName() == "Character"){
                encode[i] = ( (char) ( ((int) encode[i] * i) + 5));
            }
            if(((Object) encode[i]).getClass().getSimpleName() == "Integer") {
                int encode_int= Character.getNumericValue(encode[i]);
                encode[i]= (char) ((encode_int + (encode.length * i)) + 9);
            }
            if(encode[i]== ' ') {
                if(space > 3) {
                  space=0;
                }
                if(space==0) {
                  encode[i]= '%';
                }
                if(space==1) {
                   encode[i]='$';
                }
                if(space==2) {
                  encode[i]='@';
                }
                if(space==3) {
                 encode[i]= '#';
                }
            }
        }
        return enc_mex;
    }
}
