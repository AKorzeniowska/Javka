package pckg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        DataFrame najnowszy=new DataFrame("groupby (copy).csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
        DataFrame.DataMap n=najnowszy.groupby(new String[] {"id"});
//        System.out.println(n.max());
//        System.out.println(n.min());
//        System.out.println(n.sum());
        System.out.println(n.mean());
//        System.out.println(n.var());
//        System.out.println(n.std());

//        n.max(); System.out.println("max");
//        n.min(); System.out.println("min");
//        n.sum(); System.out.println("sum");
//        n.mean(); System.out.println("mean");
//        n.var(); System.out.println("var");
//        n.std(); System.out.println("std");


//        System.out.println(DataFrame.creator("0", new IntegerValue(2)));
//        System.out.println(DataFrame.creatorFromClass("2",IntegerValue.class)+" "+DataFrame.creatorFromClass("2",IntegerValue.class).getClass());

//        DataFrame roboczy=new DataFrame(new String[] {"id", "id2", "kol1", "kol2", "kol3"}, new Class[] {StringValue.class, StringValue.class, IntegerValue.class, IntegerValue.class, IntegerValue.class});
//        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "x"), new IntegerValue(1), new IntegerValue(7),new IntegerValue( 13)});
//        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "y"), new IntegerValue(2), new IntegerValue(8),new IntegerValue( 14)});
//        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "y"), new IntegerValue(3), new IntegerValue(9),new IntegerValue( 15)});
//        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "y"), new IntegerValue(4), new IntegerValue(10),new IntegerValue( 16)});
//        roboczy.addElement(new Value[] {new StringValue("a"),new StringValue( "x"), new IntegerValue(5), new IntegerValue(11),new IntegerValue( 17)});
//        roboczy.addElement(new Value[] {new StringValue("b"),new StringValue( "x"), new IntegerValue(6), new IntegerValue(12),new IntegerValue( 18)});
//        DataFrame nowiutki=new DataFrame(roboczy);
//        System.out.println(nowiutki);
        //        System.out.println(roboczy.numberOfRows());
//        System.out.println(roboczy.getRow(2));
        //
//        DataFrame.DataMap groupby=roboczy.groupby(new String[] {"id", "id2"});
//        System.out.println(groupby);
//
//        roboczy.dividingValue(new IntegerValue(3), new String[]{"kol1", "kol2", "id"});
//        System.out.println(roboczy);
//
//        List<Value> column=new ArrayList<>();
//        column.add(new IntegerValue(2));
//        column.add(new IntegerValue(2));
//        column.add(new IntegerValue(2));
//        column.add(new IntegerValue(0));
//        column.add(new IntegerValue(2));
//        column.add(new IntegerValue(2));
//
//        roboczy.dividingColumn(column, new String [] {"kol1", "kol3", "id"});
//        System.out.println(roboczy);

    }
}
