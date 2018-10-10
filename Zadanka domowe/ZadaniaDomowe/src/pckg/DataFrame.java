package pckg;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class DataFrame extends Object implements Cloneable{
    Map<String, ArrayList<Object>> dataBase=new HashMap<String, ArrayList<Object>>();
    String[] types;
    String[] cols;

    public DataFrame(){
        dataBase.clear();
    }

    public DataFrame(String[] typy, String[] kolumny){
        types=typy;
        ArrayList<Object> helped=new ArrayList<Object>();
        System.out.println(Arrays.toString(types));
        cols=kolumny;

        System.out.println(Arrays.toString(cols));
        /*for (int i=0; i<typy.length; i++){
            if (typy[i]=="int")
                helped=new ArrayList<Integer>();
            else if (typy[i]=="string")
                helped=new ArrayList<String>();
            else if (typy[i]=="double")
                helped=new ArrayList<Double>();
            else if (typy[i]=="float")
                helped=new ArrayList<Float>();
            dataBase.put(kolumny[i],helped);
        }*/
    }

    int size(){
        if (cols.length!=0)
            return dataBase.get(cols[0]).size();
        return 0;
    }

    ArrayList<?> get (String colname){
        return dataBase.get(colname);
    }


    public DataFrame get (String[] colls, boolean copy){
        Method method=null;
        DataFrame cloned=new DataFrame();
        if (copy==true) {
            List <String> list=Arrays.asList(colls);
            for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
                if (list.contains(entry.getKey())){
                    try{
                        method=entry.getValue().get(0).getClass().getMethod("clone");
                    }   catch (NoSuchMethodException e){
                        System.out.println("Class:  doesn't have declared clone method");
                    }
                    ArrayList<Object> clone=new ArrayList<>();
                    for (Object item : entry.getValue()) {
                        try {
                            clone.add(method == null ? item : method.invoke(item));
                        }   catch (IllegalAccessException | InvocationTargetException e){
                            e.printStackTrace();
                        }
                    }
                    cloned.dataBase.put(entry.getKey(),clone);
                }
            }
        }
        if (copy==false){

        }
    return cloned;
    }

    DataFrame iloc (int i){
        DataFrame nowy=new DataFrame();
        int a=0;
        ArrayList<Object> helped=new ArrayList<Object>();
        for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
            helped=new ArrayList<Object>();
            //Object z=entry.getValue().get(i);
            helped.add(entry.getValue().get(i));
            nowy.dataBase.put(cols[a],helped);
            a++;
        }
        return nowy;
    }

    DataFrame iloc (int from, int to){
        DataFrame nowy=new DataFrame(cols,types);
        int a=0;
        for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
            ArrayList<Object> helped=new ArrayList<>();
            for (int i=from; i<=to; i++) {
                Object z = entry.getValue().get(i);
                helped.add(z);                              //[sic!]
            }
            nowy.dataBase.put(cols[a],helped);
            a++;
        }
        return nowy;
    }
}
