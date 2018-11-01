package pckg;

public class DoubleValue extends Value {
    public double value;

    public DoubleValue () {}

    public static DoubleValue getInstance(){
        return new DoubleValue();
    }

    public DoubleValue create (String a){
        DoubleValue x=new DoubleValue(Double.parseDouble(a));
        return x;
    }

    public DoubleValue (double a){
        value=a;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public Value add(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value+((DoubleValue)a).value;
        return this;
    }

    public Value sub(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value-((DoubleValue)a).value;
        return this;
    }

    public Value mul(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value*((DoubleValue)a).value;
        return this;
    }

    public Value div(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value/((DoubleValue)a).value;
        return this;
    }

    public Value pow(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=Math.pow(value, ((DoubleValue)a).value);
        return this;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value==((DoubleValue)a).value)
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value<((DoubleValue)a).value)
            return true;
        return false;
    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value>((DoubleValue)a).value)
            return true;
        return false;
    }

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value!=((DoubleValue)a).value)
            return true;
        return  false;
    }

    public boolean equals(Object other) {
        return false;
    }

    public int hashCode() {
        return 0;
    }
}
