package resource;

import java.util.ArrayList;

public class Mex {
    ArrayList<Integer> SpecialChar = new ArrayList<>();
    String mex;

    public void setSpecialChar(ArrayList<Integer> m) {this.SpecialChar = m;}

    public ArrayList<Integer> getSpecialChar() {
        return SpecialChar;
    }

    public void setMex(String mex) {this.mex = mex;}

    public String getMex() {
        return mex;
    }


}
