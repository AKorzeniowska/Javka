package pckg;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataFrame data=new DataFrame(new String[]{"Integer", "Double"}, new String[]{"kol1", "kol2"});
        ArrayList<Object> array=data.get("kol1");
        data.addElement(5,6.13);
        data.addElement(7,9.14);
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
        DataFrame datas=new DataFrame(new String[]{"Integer", "Integer"}, new String[]{"kol1", "kol2"});
        datas.addElement(1,0);
        datas.addElement(0,4);
        SparseDataFrame sdf=new SparseDataFrame(datas,"0");
        System.out.println(sdf);
        System.out.println(sdf.toDense());
    }
}
