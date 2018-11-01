package pckg;

public class StringValue extends Value {
    public String value=new String();

    public StringValue () {}

    public static StringValue getInstance(){
        return new StringValue();
    }

    public StringValue create (String a){
        StringValue x=new StringValue();
        x.value=a;
        return x;
    }

    public StringValue (String a){
        value=a;
    }

    public String toString() {
        return value;
    }

    public Value add(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        value=value+((StringValue)a).value;
        return this;
    }

    public Value sub(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        System.out.println("Operacja niemożliwa dla typu String");
        return this;
    }

    public Value mul(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        System.out.println("Operacja niemożliwa dla typu String");
        return this;
    }

    public Value div(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        System.out.println("Operacja niemożliwa dla typu String");
        return this;
    }

    public Value pow(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        System.out.println("Operacja niemożliwa dla typu String");
        return this;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.equals(((StringValue)a).value))
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.length()<(((StringValue)a).value).length())
            return true;
        return false;
    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.length()>(((StringValue)a).value).length())
            return true;
        return false;}

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!value.equals(((StringValue)a).value))
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
