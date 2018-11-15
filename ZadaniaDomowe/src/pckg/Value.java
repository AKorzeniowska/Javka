package pckg;

public abstract class Value implements Cloneable{
    public abstract  Value create (String a);
    public abstract String toString();
    public abstract Value add(Value a);
    public abstract Value sub(Value a);
    public abstract Value mul(Value a);
    public abstract Value div(Value a) throws DividingByZeroException;
    public abstract Value pow(Value a);
    public abstract boolean eq(Value a);
    public abstract boolean lte(Value a);
    public abstract boolean gte(Value a);
    public abstract boolean neq(Value a);
    public abstract boolean equals(Object other);
    public abstract int hashCode();
}
