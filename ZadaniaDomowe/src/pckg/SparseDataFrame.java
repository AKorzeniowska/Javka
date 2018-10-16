package pckg;

import javafx.util.Pair;

import java.util.*;

public class SparseDataFrame extends DataFrame {
    String hide=new String();
    int colsCount;

    SparseDataFrame(String[] typesInput, String[] namesInput, String toHide){
        super(typesInput, namesInput);
        int a;
        colsCount=0;
        hide=toHide;
    }

    SparseDataFrame (DataFrame obj, String toHide){
        types=obj.types;
        cols=obj.cols;
        colsCount=obj.dataBase.get(cols.get(0)).size();
        for (String names : cols){
            dataBase.put(names, new ArrayList<>(obj.dataBase.get(names)));
        }

        int a=0;
        for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
            a=0;
            for (Object i : entry.getValue()){
                if (!toHide.equals(String.valueOf(i))){
                    CooValue z=new CooValue(a,i);
                    entry.getValue().set(a,z);
                }
                a++;
            }
        }
        for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
            entry.getValue().removeAll(Collections.singleton(Integer.parseInt(toHide)));
        }
    }

    DataFrame toDense () {
        DataFrame data = new DataFrame();
        data.types = types;
        data.cols = cols;
        for (int i = 0; i < types.size(); i++) {
            ArrayList<Object> helped = new ArrayList<>();
            for (int j = 0; j < colsCount; j++) {
                helped.add(0);
            }
            data.dataBase.put(cols.get(i), helped);
        }
        for (Map.Entry<String, ArrayList<Object>> entry : dataBase.entrySet()) {
            for (Object i : entry.getValue()) {
                CooValue pair = (CooValue) i;
                data.dataBase.get(entry.getKey()).set(pair.getPlace(), pair.getObject());
            }
        }
        return data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
