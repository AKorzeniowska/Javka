package pckg;

import java.util.*;

public class DataFrame extends Object {
    Map<String, ArrayList<Object>> dataBase = new HashMap<>();
    List<String> types = new ArrayList<>();
    List<String> cols = new ArrayList<>();

    public DataFrame() {
        dataBase.clear();
    }

    public DataFrame(String[] typy, String[] kolumny){

        for (int i=0; i<typy.length; i++) {
            types.add(typy[i]);
            cols.add(kolumny[i]);
        }

        for (int i=0; i<typy.length; i++) {
            ArrayList<Object> helped=new ArrayList<>();
            dataBase.put(kolumny[i], helped);
        }
    }

    int size(){
        if (cols.size()!=0)
           return dataBase.get(cols.get(0)).size();
        return 0;
    }

    ArrayList<Object> get (String colname){
        return dataBase.get(colname);
    }


    public DataFrame get (String[] colls, boolean copy){
        String[] typeNames = new String[colls.length];
        for (int i = 0; i < colls.length; i++) {
            for (int j = 0; j < types.size(); j++) {
                if(colls[i].equals(cols.get(j))){
                    typeNames[i] = types.get(j);
                }
            }
        }
        DataFrame result = new DataFrame(typeNames, colls);
        if (copy){
            result.dataBase = new HashMap<>();
            for (String coll : colls) {
                result.dataBase.put(coll, new ArrayList<>(dataBase.get(coll)));
            }
        }
        else{
            result.dataBase = new HashMap<>();
            for (String coll : colls) {
                result.dataBase.put(coll, dataBase.get(coll));
            }
        }
        return result;
    }

    DataFrame iloc (int i){
        DataFrame nowy=new DataFrame();
        if (i>=dataBase.get(cols.get(0)).size())
            return nowy;
        nowy.cols=new ArrayList<>(cols);
        nowy.types=new ArrayList<>(types);
        int a=0;
        for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
            ArrayList<Object> helped = new ArrayList<Object>();
            helped.add(entry.getValue().get(i));
            nowy.dataBase.put(cols.get(a), helped);
            a++;
        }
        return nowy;
    }

    DataFrame iloc (int from, int to){
        DataFrame nowy=new DataFrame();
        if (to>=dataBase.get(cols.get(0)).size())
            return nowy;
        nowy.types=new ArrayList<>(types);
        nowy.cols=new ArrayList<>(cols);
        int a=0;
        for (Map.Entry<String, ArrayList<Object>> entry: dataBase.entrySet()){
            ArrayList<Object> helped = new ArrayList<>();
            for (int i = from; i <= to; i++) {
                Object z = entry.getValue().get(i);
                helped.add(z);
            }
            nowy.dataBase.put(nowy.cols.get(a), helped);
            a++;
        }
        return nowy;
    }

    @Override
    public String toString() {
        return "DataFrame{" +
                "dataBase=" + dataBase +
                ", types=" + types +
                ", cols=" + cols +
                '}';
    }

    void addElement(Object ... input){
        if (input.length!=cols.size()) {
            System.out.println("Nieodpowiednia ilość argumentów!");
            return;
        }
        int a=0;
        for (Object i : input) {
            Class classed=null;
            try{
                if (!Class.forName("java.lang."+types.get(a)).isInstance(i)) {
                    System.out.println("typ danych niezgodny z kolumną");
                    return;
                }
            }   catch (ClassNotFoundException e){
                try{
                    if (!Class.forName(types.get(a)).isInstance(i)) {
                        System.out.println("typ danych niezgodny z kolumną");
                        return;
                    }
                }   catch (ClassNotFoundException g){
                    System.out.println("Nie ma takiej klasy");
                }
            }
            a++;
        }
        a=0;
        for (Object i : input){
            dataBase.get(cols.get(a)).add(i);
            a++;
        }
    }
}
