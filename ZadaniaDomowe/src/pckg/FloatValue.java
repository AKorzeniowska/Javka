package pckg;

public class FloatValue extends Value {
    public Float value;

    public FloatValue () {}


    public static FloatValue getInstance(){
        return new FloatValue();
    }

    public FloatValue create (String a){
        FloatValue x=new FloatValue();
        x.value=Float.parseFloat(a);
        return x;
    }

    public FloatValue (float a){
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
        value=value+((FloatValue)a).value;
        return this;
    }

    public Value sub(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value-((FloatValue)a).value;
        return this;
    }

    public Value mul(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value*((FloatValue)a).value;
        return this;
    }

    public Value div(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value/((FloatValue)a).value;
        return this;
    }

    public Value pow(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=(float)Math.pow(value, ((FloatValue)a).value);
        return this;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value==((FloatValue)a).value)
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value<((FloatValue)a).value)
            return true;
        return false;
    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value>((FloatValue)a).value)
            return true;
        return false;
    }

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value!=((FloatValue)a).value)
            return true;
        return false;
    }

    public boolean equals(Object other) {
        if (!other.getClass().isInstance(this)){
            return false;
        }
        if (value==((FloatValue)other).value)
            return true;
        return false;
    }

    public int hashCode() {
        return value.hashCode();
    }
}
