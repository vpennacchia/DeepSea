package Impl;

/*VP01*/
public class Enc {
    public static String encoding(String mex){
        String enc_mex = " ";
        char [] encode= mex.toCharArray();
        int i = 0, space=0, j=0;


        for(i=0; i<= encode.length - 1; ++i){
            if(encode[i] != ' ') {
                if ((int) encode[i] >= (int) '0' && (int) encode[i] <= '9') {
                    int encode_int = Character.getNumericValue(encode[i]);
                    encode[i] = (char) (encode_int + ((encode.length * i) + 9));
                } else {
                    encode[i] = (char) (((int) encode[i] * i) + 5);
                }
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
                ++space;
            }
        }

        j = (encode.length) / 2;
        for(i=0; i<= ((encode.length) / 2) -1  && j <= encode.length; ++i,++j){
            char s= encode[i];
            encode[i] = encode[j];
            encode[j] = s;
        }

        j = (encode.length) / 2;
        char el0= encode[0];
        encode[0] = encode[j - 1 ];
        encode[j - 1]= el0;
        char el1= encode[j];
        encode[j] = encode[encode.length - 1];
        encode[encode.length - 1] = el1;

        for(j=0; j <= encode.length - 1; ++j){
            enc_mex = enc_mex + encode[j];
        }


        return enc_mex;
    }
}
