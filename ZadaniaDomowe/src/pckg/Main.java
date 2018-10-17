package pckg;

import java.util.ArrayList;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        DataFrame data=new DataFrame(new String[]{"Integer", "Double"}, new String[]{"kol1", "kol2"});
        ArrayList<Object> array=data.get("kol1");
        data.addElement(new Object[]{5,6.13});
        data.addElement(new Object[]{7,9.14});
        DataFrame df = data.get(new String[]{"kol2"}, false);
        DataFrame copy = data.get(new String[]{"kol2"}, true);
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
        DataFrame read=new DataFrame("sparse.csv", new String[] {"Double", "Double", "Double"});
        System.out.println(read);
        SparseDataFrame reader=new SparseDataFrame(read, "0.0");
        System.out.println(reader);
        System.out.println(reader.toDense());

        SparseDataFrame readerer= new SparseDataFrame("sparse.csv", "0.0", new String[] {"Double", "Double", "Double"});
        System.out.println(readerer);

//        sdf.addElement(new Integer[]{5,0});
//        sdf.addElement(new Integer[]{2,0});
//        System.out.println(sdf);
//        DataFrame reading=new DataFrame("data.csv", new String[] {"Double", "Double", "Double"}, new String[] {"kol1", "kol2", "kol3"});
//        System.out.println(reading);
    }
}
