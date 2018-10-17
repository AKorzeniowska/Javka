package pckg;

import javafx.util.Pair;

public class CooValue {

    private Pair<Integer, Object> pair;

    /**
     * Creates new pair storing the number of a row in which the Object occurred, and the Object
     * @param a number of row Integer
     * @param x given Object itself
     */
    public CooValue(int a, Object x){
        pair=new Pair<>(a,x);
    }

    /**
     * Returns the number of a row in which the Object occurred
     * @return number of row Integer
     */
    public int getPlace (){
        return pair.getKey();
    }

    /**
     * Returns the Object itself
     * @return Object
     */
    public Object getObject() {
        return pair.getValue();
    }

    /**
     * Returns a String representation of CooValue object
     * @return a String representation of CooValue object
     */
    @Override
    public String toString() {
        return ""+pair.getKey()+"-"+pair.getValue();
    }
}
