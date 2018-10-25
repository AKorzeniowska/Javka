package pckg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeValue extends Value {
    Date value;
    SimpleDateFormat form=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public DateTimeValue (){
        value=new Date();
    }
    public DateTimeValue create (String a){
        DateTimeValue x=new DateTimeValue();
        Date t;
        try {
            t = form.parse(a);
            System.out.println(t);
            x.value=t;
        } catch (ParseException e) {
            System.out.println("Unparseable using " + form);
        }
        return x;
    }

    public String toString() {
        return form.format(value);
    }

    public Value add(Value a) {
        return null;
    }

    public Value sub(Value a) {
        return null;
    }

    public Value mul(Value a) {
        return null;
    }

    public Value div(Value a) {
        return null;
    }

    public Value pow(Value a) {
        return null;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(value)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.equals(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(value)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.before(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(value)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.after(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(value)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!value.equals(((DateTimeValue)a).value))
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
