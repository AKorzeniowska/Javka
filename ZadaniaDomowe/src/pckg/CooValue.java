package pckg;

import javafx.util.Pair;
import java.lang.*;

@SuppressWarnings("Duplicates")

public class CooValue extends Value{
    private Pair<Integer, Value> pair;

    public Value create(String a) {
        CooValue x=new CooValue(0,new IntegerValue(0));
        String[] separated=a.split(",");
        if (separated[1].contains(".")) {
            DoubleValue var=new DoubleValue(0.0);
            x.pair = new Pair<Integer, Value>(Integer.parseInt(separated[0]), var.create(separated[1]));
        }
        else {
            IntegerValue var=new IntegerValue(0);
            x.pair = new Pair<Integer, Value>(Integer.parseInt(separated[0]), var.create(separated[1]));
        }
        return x;
    }

    /**
     * Creates new pair storing the number of a row in which the Object occurred, and the Object
     * @param a number of row Integer
     * @param x given Object itself
     */
    public CooValue(int a, Value x){
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
    public Value getObject() {
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

    public Value add(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return null;
        Integer var=pair.getKey()+((CooValue)a).pair.getKey();
        Value val=pair.getValue().add(((CooValue)a).pair.getValue());
        pair=new Pair<>(var, val);
        return this;
    }

    public Value sub(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return null;
        Integer var=pair.getKey()-((CooValue)a).pair.getKey();
        Value val=pair.getValue().sub(((CooValue)a).pair.getValue());
        pair=new Pair<>(var, val);
        return this;
    }

    public Value mul(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return null;
        Integer var=pair.getKey()*((CooValue)a).pair.getKey();
        Value val=pair.getValue().mul(((CooValue)a).pair.getValue());
        pair=new Pair<>(var, val);
        return this;
    }

    public Value div(Value a) throws DividingByZeroException{
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return null;
        Integer var=pair.getKey()/((CooValue)a).pair.getKey();
        Value val=pair.getValue().div(((CooValue)a).pair.getValue());
        pair=new Pair<>(var, val);
        return this;
    }


    public Value pow(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return null;
        Integer var=(int)Math.pow(pair.getKey(),((CooValue)a).pair.getKey());
        Value val=pair.getValue().pow(((CooValue)a).pair.getValue());
        pair=new Pair<>(var, val);
        return this;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return false;
        if (pair.getKey()==((CooValue)a).pair.getKey() && pair.getValue().eq(((CooValue)a).pair.getValue()))
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return false;
        if (pair.getKey()<((CooValue)a).pair.getKey() && pair.getValue().lte(((CooValue)a).pair.getValue()))
            return true;
        return false;
    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return false;
        if (pair.getKey()>((CooValue)a).pair.getKey() && pair.getValue().gte(((CooValue)a).pair.getValue()))
            return true;
        return false;
    }

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!((CooValue)a).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)a).pair.getValue().getClass().isInstance(pair.getValue()))
            return false;
        if (pair.getKey()!=((CooValue)a).pair.getKey() && pair.getValue().neq(((CooValue)a).pair.getValue()))
            return true;
        return false;
    }

    public boolean equals(Object other) {
        if (!other.getClass().isInstance(this)){
            return false;
        }
        if (!((CooValue)other).pair.getKey().getClass().isInstance(pair.getKey()) || !((CooValue)other).pair.getValue().getClass().isInstance(pair.getValue()))
            return false;
        if (pair.getKey()==((CooValue)other).pair.getKey() && pair.getValue().eq(((CooValue)other).pair.getValue()))
            return true;
        return false;
    }

    public int hashCode() {
        return pair.hashCode();
    }

    @Override
    public Object getValue() {
        return pair;
    }
}
