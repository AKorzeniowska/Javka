package pckg;

import java.lang.Math;

public class IntegerValue extends Value {
    public Integer value;

    public IntegerValue () {}

    public static IntegerValue getInstance(){
        return new IntegerValue();
    }

    public IntegerValue (int a){
        value=a;
    }

    public IntegerValue create(String a){
        IntegerValue x=new IntegerValue();
        if (Double.parseDouble(a)<0.0)
            a="0";
        x.value=Integer.parseInt(a);
        return x;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public Value add (Value a){
        System.out.println(a.getClass()+" "+this.getClass());
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value = value+((IntegerValue)a).value;
        return this;
    }

    public Value sub(Value a){
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value = value-((IntegerValue)a).value;
        return this;
    }

    public Value mul(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value = value*((IntegerValue)a).value;
        return this;
    }


    public Value div(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value = value/((IntegerValue)a).value;
        return this;
    }

    public Value pow(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=(int)Math.pow(value,((IntegerValue)a).value);
        return this;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value==((IntegerValue)a).value)
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value<((IntegerValue)a).value)
            return true;
        return false;    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value>((IntegerValue)a).value)
            return true;
        return false;
    }

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value!=((IntegerValue)a).value)
            return true;
        return false;
    }

    public boolean equals(Object other) {
        return false;
    }

    public int hashCode() {
        return 0;
    }
}
