package pckg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeValue extends Value {
    Date value;
    static SimpleDateFormat form=new SimpleDateFormat("yyyy-MM-dd");

    public DateTimeValue (){
        String a="0000-00-00";
        value=new Date();
        try {
            value = form.parse(a);
        } catch (ParseException e) {
        System.out.println("Unparseable using " + form);
        }
    }

    public DateTimeValue (String a){
        value=new Date();
        try{
            value=form.parse(a);
        } catch (ParseException e){
            System.out.println("Unparseable using " + form);
        }
    }

    public static DateTimeValue getInstance(){
        return new DateTimeValue();
    }

    public DateTimeValue create (String a){
        DateTimeValue x=new DateTimeValue();
        Date t;
        if (!a.contains("-")){
            a+="-00-00";
        }
        try {
            t = form.parse(a);
            x.value=t;
        } catch (ParseException e) {
            //System.out.println("Unparseable using " + form);
        }
        return x;
    }

    public String toString() {
        return form.format(value);
    }

    public Value add(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        long sum=this.value.getTime()+((DateTimeValue)a).value.getTime();
        value=new Date(sum);
        return this;
    }

    public Value sub(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return null;
        }
        long sum=this.value.getTime()-((DateTimeValue)a).value.getTime();
        value=new Date(sum);
        return this;
    }

    public Value mul(Value a) {
        return this;
    }

    public Value div(Value a) {
        return this;
    }

    public Value pow(Value a) {
        return this;
    }

    public boolean eq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.equals(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean lte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.before(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean gte(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (value.after(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean neq(Value a) {
        if (!a.getClass().isInstance(this)){
            System.out.println("Typ niezgodny");
            return false;
        }
        if (!value.equals(((DateTimeValue)a).value))
            return true;
        return false;
    }

    public boolean equals(Object other) {
        if (!other.getClass().isInstance(this))
            return false;
        if (value.equals(((DateTimeValue)other).value))
            return true;
        return false;
    }

    public int hashCode() {
        return value.hashCode();
    }
}
