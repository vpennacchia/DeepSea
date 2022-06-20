package resource;


import java.util.ArrayList;

public class Mex {
    ArrayList<Integer> SpecialChar = new ArrayList<>();
    String mex;
    String key;

    public void setSpecialChar(ArrayList<Integer> m) {
        this.SpecialChar = m;
    }

    public ArrayList<Integer> getSpecialChar() {
        return SpecialChar;
    }

    public void setMex(String mex) {
        this.mex = mex;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
         return key;
    }

    public String getMex() {
        return mex;
    }

}
