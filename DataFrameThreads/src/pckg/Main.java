package pckg;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DataFrameThreads roboczy= null;
        roboczy = new DataFrameThreads(new String[] {"id", "id2", "kol1", "kol2", "kol3"}, new Class[] {StringValue.class, StringValue.class, IntegerValue.class, IntegerValue.class, IntegerValue.class});
        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "x"), new IntegerValue(1), new IntegerValue(7),new IntegerValue( 13)});
        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "y"), new IntegerValue(2), new IntegerValue(8),new IntegerValue( 14)});
        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "y"), new IntegerValue(3), new IntegerValue(9),new IntegerValue( 15)});
        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "y"), new IntegerValue(4), new IntegerValue(10),new IntegerValue( 16)});
        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "x"), new IntegerValue(5), new IntegerValue(11),new IntegerValue( 17)});
        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "x"), new IntegerValue(6), new IntegerValue(12),new IntegerValue( 18)});

//        DataFrameThreads.DataMapThread x=roboczy.groupby(new String[]{"id"});
//        x.max();
//        System.out.println(x.max());
//        System.out.println(x.min());
//        System.out.println(x.var());

        DataFrameThreads nowy=null;
        try {
            nowy=new DataFrameThreads("groupby (copy).csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataFrameThreads.DataMapThread y=nowy.groupby(new String[]{"id"});
        System.out.println(y.max());
//        System.out.println(y.mean());
//        System.out.println(y.sum());
//        System.out.println(y.std());
    }
}
