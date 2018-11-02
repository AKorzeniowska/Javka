package pckg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
//        DataFrame data=new DataFrame(new String[]{"Integer", "Double"}, new String[]{"kol1", "kol2"});
//        ArrayList<Object> array=data.get("kol1");
//        data.addElement(new Object[]{5,6.13});
//        data.addElement(new Object[]{7,9.14});
//        DataFrame df = data.get(new String[]{"kol2"}, false);
//        DataFrame copy = data.get(new String[]{"kol2"}, true);
//        System.out.println(data);
//        System.out.println(df);
//        System.out.println(copy);
//        data.addElement(2,3.3);
//        System.out.println(data);
//        System.out.println(df);
//        System.out.println(copy);
//        DataFrame row= data.iloc(1);
//        System.out.println(row);
//        row=data.iloc(0,1);
//        System.out.println(row);
//        System.out.println(array);
//        System.out.println(data.size());

//        DataFrame datas=new DataFrame(new String[]{"Integer", "Integer"}, new String[]{"kol1", "kol2"});
//        datas.addElement(new Integer[]{1,0});
//        datas.addElement(new Integer[]{0,4});
//        SparseDataFrame sdf=new SparseDataFrame(datas,"0");
//        System.out.println(sdf);
//        System.out.println(sdf.toDense());
//        DataFrame read=new DataFrame("sparse.csv", new String[] {"Double", "Double", "Double"});
//        System.out.println(read);
//        SparseDataFrame reader=new SparseDataFrame(read, "0.0");
//        System.out.println(reader);
//        System.out.println(reader.toDense());
//
//        SparseDataFrame readerer= new SparseDataFrame("sparse.csv", "0.0", new String[] {"Double", "Double", "Double"});
//        System.out.println(readerer);
//        System.out.println(readerer.iloc(1));
//        System.out.println(readerer.iloc(0,2));

//        sdf.addElement(new Integer[]{5,0});
//        sdf.addElement(new Integer[]{2,0});
//        System.out.println(sdf);
//        DataFrame reading=new DataFrame("data.csv", new String[] {"Double", "Double", "Double"}, new String[] {"kol1", "kol2", "kol3"});
//        System.out.println(reading);
//
//        DataFrame nowy=new DataFrame(new String[] {"kol1", "kol2"}, new Class [] {IntegerValue.class, DoubleValue.class});
//        nowy.addElement(new Value[] {new IntegerValue(5), new DoubleValue(3.14)});
//        nowy.addElement(new Value[] {new IntegerValue(3), new DoubleValue(2.13)});
//        nowy.addElement(new Value[] {new IntegerValue(1), new DoubleValue(33.5)});
//        System.out.println(nowy);
//        System.out.println(nowy.iloc(1));
//        System.out.println(nowy.iloc(0,1));
//        DataFrame nowszy=new DataFrame(new String[] {"kol1", "kol2"}, new Class [] {IntegerValue.class, IntegerValue.class});
//        nowszy.addElement(new Value[] {new IntegerValue(0), new IntegerValue(0)});
//        nowszy.addElement(new Value[] {new IntegerValue(1), new IntegerValue(0)});
//        nowszy.addElement(new Value[] {new IntegerValue(0), new IntegerValue(3)});
//        nowszy.addElement(new Value[] {new IntegerValue(4), new IntegerValue(5)});
//        nowszy.addElement(new Value[] {new IntegerValue(0), new IntegerValue(0)});
//        nowszy.addElement(new Value[] {new IntegerValue(8), new IntegerValue(9)});
//        SparseDataFrame sparse=new SparseDataFrame(nowszy, "0");
//        System.out.println(sparse+"to jest sparse");
//        System.out.println(sparse.get(new String[] {"kol1"}, true));
//        System.out.println(sparse.iloc(3));
//        System.out.println(sparse.iloc(0,2));
//        DataFrame x=sparse.toDense();
//        System.out.println(x);
//        System.out.println(nowszy);
//        System.out.println(nowszy.iloc(2));
//        System.out.println(nowszy.addRow(nowszy.iloc(2)));
//        DataFrame nowiutki=new DataFrame(new String[] {"kol1", "kol2", "kol3"}, new Class [] {IntegerValue.class, IntegerValue.class, IntegerValue.class});
//        nowiutki.addElement(new Value[] {new IntegerValue(0), new IntegerValue(10), new IntegerValue(20)});
//        nowiutki.addElement(new Value[] {new IntegerValue(1), new IntegerValue(11), new IntegerValue(21)});
//        nowiutki.addElement(new Value[] {new IntegerValue(1), new IntegerValue(12), new IntegerValue(22)});
//        nowiutki.addElement(new Value[] {new IntegerValue(0), new IntegerValue(13), new IntegerValue(23)});
//        nowiutki.addElement(new Value[] {new IntegerValue(2), new IntegerValue(14), new IntegerValue(24)});
//        nowiutki.addElement(new Value[] {new IntegerValue(1), new IntegerValue(15), new IntegerValue(25)});
//        System.out.println(nowiutki);
//        DataFrame roboczy=new DataFrame(new String[] {"kol3", "kol2", "kol1"}, new Class [] {IntegerValue.class, IntegerValue.class, IntegerValue.class});
//        roboczy.addElement(new Value[] {new IntegerValue(20), new IntegerValue(10), new IntegerValue(0)});
//        nowiutki.addRow(roboczy);
//        System.out.println(nowiutki);
//        DataFrame.DataMap f=nowiutki.groupby(new String [] {"kol1"});
//        System.out.println(f);
//        System.out.println(f.max());
//        System.out.println(f.min());
//
        DataFrame najnowszy=new DataFrame("groupby.csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
//        System.out.println(najnowszy);
        DataFrame.DataMap n=najnowszy.groupby(new String[] {"id"});
//        System.out.println(n);
//        System.out.println(n.max());
//        System.out.println(n.min());
//        System.out.println(n.sum());                                    //              FIX DATETIMEVALUE.ADD()
//        System.out.println(n.mean());
        System.out.println(n);
        System.out.println(n.var());
        System.out.println(n);
        System.out.println(n.var());
        System.out.println(n);
        //System.out.println(n.std());
        //Value a=new DoubleValue(320140);
        //System.out.println(a.pow(a.create("0.5")));

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
