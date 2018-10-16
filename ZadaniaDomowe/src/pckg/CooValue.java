package pckg;

import javafx.util.Pair;

public class CooValue {

    private Pair<Integer, Object> pair;

    CooValue(int a, Object x){
        pair=new Pair<>(a,x);
    }

    int getPlace (){
        return pair.getKey();
    }

    Object getObject() {
        return pair.getValue();
    }

    @Override
    public String toString() {
        return ""+pair.getKey()+"-"+pair.getValue();
    }
}
