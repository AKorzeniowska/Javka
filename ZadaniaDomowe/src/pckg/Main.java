package pckg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        DataFrame najnowszy=new DataFrame("groupby.csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
//        System.out.println(najnowszy);
        DataFrame.DataMap n=najnowszy.groupby(new String[] {"id"});
        System.out.println(n);
        System.out.println(n.max());
        System.out.println(n.min());
        System.out.println(n.sum());                                    //              FIX DATETIMEVALUE.ADD()
        System.out.println(n.mean());
        System.out.println(n.var());
        System.out.println(n.std());

//        IntegerValue a=new IntegerValue(5);
//        a.add(new IntegerValue(10));
//        System.out.println(a);
//        System.out.println(x);
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
