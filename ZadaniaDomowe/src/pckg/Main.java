package pckg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        DataFrame najnowszy=new DataFrame("groupby.csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
//        DataFrame.DataMap n=najnowszy.groupby(new String[] {"id"});
//        System.out.println(n.max());
//        System.out.println(n.min());
//        System.out.println(n.sum());
//        System.out.println(n.mean());
//        System.out.println(n.var());
//        System.out.println(n.std());



//        DataFrame roboczy=new DataFrame(new String[] {"id", "id2", "kol1", "kol2", "kol3"}, new Class[] {StringValue.class, StringValue.class, IntegerValue.class, IntegerValue.class, IntegerValue.class});
//        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "x"), new IntegerValue(1), new IntegerValue(7),new IntegerValue( 13)});
//        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "y"), new IntegerValue(2), new IntegerValue(8),new IntegerValue( 14)});
//        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "y"), new IntegerValue(3), new IntegerValue(9),new IntegerValue( 15)});
//        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "y"), new IntegerValue(4), new IntegerValue(10),new IntegerValue( 16)});
//        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "x"), new IntegerValue(5), new IntegerValue(11),new IntegerValue( 17)});
//        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "x"), new IntegerValue(6), new IntegerValue(12),new IntegerValue( 18)});
//        DataFrame.DataMap groupby=roboczy.groupby(new String[] {"id", "id2"});
//        System.out.println(groupby);


//        DateTimeValue a=new DateTimeValue();
//        a.create("11-11-2018 03:10:10");
//        try {
//            DataFrame read=new DataFrame("data.csv", new Class [] {DoubleValue.class, DoubleValue.class, DoubleValue.class});
//            System.out.println(read);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            SparseDataFrame read=new SparseDataFrame("sparse.csv", "0.0",new Class [] {DoubleValue.class, DoubleValue.class, DoubleValue.class});
//            System.out.println(read);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
