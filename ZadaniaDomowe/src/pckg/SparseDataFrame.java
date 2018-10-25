package pckg;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@SuppressWarnings("Duplicates")

public class SparseDataFrame extends DataFrame {
    String hide = new String();
    int colsCount;

    /**
     * Constructor for SparseDataFrame Class, inherited from DataFrame Class
     * adds column types and names to types and cols Lists
     * creates new ArrayLists and puts them to dataBase Map
     * Puts toHide String to hide String - precises Objects to hide
     *
     * @param typesInput String Array of types that each column will keep
     * @param colsInput  String Array of names of columns
     * @param toHide     String precising Objects to hide
     */
    public SparseDataFrame(Class<? extends Value>[] typesInput, String[] colsInput, String toHide) {
        super(colsInput, typesInput);
        colsCount = 0;
        hide = toHide;
    }

    /**
     * Constructor for SparseDataFrame Class
     * Converts DataFrame object to SparseDataFrame object by removing all occurrences of Objects specified in toHide String
     *
     * @param obj    DataFrame object
     * @param toHide String precising Objects to hide
     */
    public SparseDataFrame(DataFrame obj, String toHide) {
        classes = obj.classes;
        cols = obj.cols;
        hide = toHide;
        colsCount = obj.dataBase.get(cols.get(0)).size();
        dataBase=new HashMap<>();
        for (String names : cols) {
            dataBase.put(names, new ArrayList<Value>(obj.dataBase.get(names)));
        }

        int a;
        for (Map.Entry<String, ArrayList<Value>> entry : dataBase.entrySet()) {
            a=0;
            for (Value i : entry.getValue()) {
                if (!toHide.equals(i.toString())) {
                    CooValue z = new CooValue(a, i);
                    entry.getValue().set(a, z);
                }
                a++;
            }
        }
        for (Map.Entry<String, ArrayList<Value>> entry: dataBase.entrySet()){
            if (classes.get(0)==IntegerValue.class) {
                for (int j=entry.getValue().size()-1; j>=0; j--){
                    if (entry.getValue().get(j).toString().equals(toHide)){
                        entry.getValue().remove(j);
                    }
                }
            }
        }
    }
    /**
     * Constructor for DataFrame Class
     * Fills cols List with column names given in first line of the file
     * Fills columns with data from file if they're not equal to given toHide String
     * @param address String containing file's address
     * @param typesInput String Array of types that each column will keep
     * @param toHide String precising data to hide
     * @throws IOException
     */
    /*public SparseDataFrame(String address, String toHide, Class <? extends Value> [] typesInput) throws IOException {
        FileInputStream fstream;
        BufferedReader br;
        hide=toHide;
        fstream = new FileInputStream(address);
        if (fstream==null)
            throw new IOException("File not found!");
        else
            br=new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] separated;
        String[] colsInput=new String[typesInput.length];
        Object[] fixed=new Object[typesInput.length];

        strLine=br.readLine();
        separated=strLine.split(",");
        for (int i=0; i<separated.length; i++){
            colsInput[i]=separated[i];
        }
        for (int i=0; i<typesInput.length; i++) {
            classes.add(typesInput[i]);
            cols.add(colsInput[i]);
        }

        for (int i=0; i<typesInput.length; i++) {
            ArrayList<Value> helped=new ArrayList<>();
            dataBase.put(colsInput[i], helped);
        }

        //while ((strLine = br.readLine()) != null) {
        for (int j=0; j<10; j++) {
            strLine = br.readLine();
            separated = strLine.split(",");
            if(typesInput[0].equals("Double")) {
                for (int k = 0; k < separated.length; k++) {
                    fixed[k]=Double.parseDouble(separated[k]);
                }
            }
            else if (typesInput[0].equals("Integer")){
                for (int k = 0; k < separated.length; k++) {
                    fixed[k] = Integer.parseInt(separated[k]);
                }
            }
            addElement(fixed);
        }
    }*/

    /**
     * Adds row to DataFrame object (adds element to every ArrayList in dataBase Map)
     * Checks whether number of given arguments is compatible to number of columns
     * Checks whether types of given arguments are compatible with type of each column
     * If type of argument is not a primitive type, checks whether its users type
     * Checks for ClassNotFoundException
     * If Object is not equal to hide String, creates new CooValue and puts it to ArrayList
     * CooValue's Key is the number of rows by far (presented by colsCount)
     *
     * @param input Array of Objects to put in columns
     */
    //@Override
    public void addElement(Value[] input) {
        if (input.length != cols.size()) {
            System.out.println("Nieodpowiednia ilość argumentów!");
            return;
        }
        int a = 0;
        for (Object i : input) {
            /*try {
                if (!Class.forName("java.lang." + types.get(a)).isInstance(i)) {
                    System.out.println("typ danych niezgodny z kolumną");
                    return;
                }
            } catch (ClassNotFoundException e) {
                try {
                    if (!Class.forName(types.get(a)).isInstance(i)) {
                        System.out.println("typ danych niezgodny z kolumną");
                        return;
                    }
                } catch (ClassNotFoundException g) {
                    System.out.println("Nie ma takiej klasy");
                }
            }
            a++;*/

            if (classes.get(a) != i.getClass()) {
                System.out.println("typ danych niezgodny z kolumna");
                return;
            }
            a++;
        }
        a = 0;
        for (Value i : input) {
            if (!hide.equals(String.valueOf(i))) {
                CooValue z = new CooValue(colsCount, i);
                dataBase.get(cols.get(a)).add(z);
            }
            a++;

        }
        colsCount++;
    }


    /**
     * Creates DataFrame object from SparseDataFrame object
     * Fills columns with value stored by hide String
     * Puts Object stored by CooValue's Value in row given by CooValue's Key
     * @return DataFrame object corresponding with original SparseDataFrame object
     */
    public DataFrame toDense () {
        DataFrame data = new DataFrame();
        data.classes = classes;
        data.cols = cols;
        CooValue a= (CooValue) dataBase.get(cols.get(0)).get(0);
        Value y=a.getObject();
        for (int i = 0; i < classes.size(); i++) {
            ArrayList<Value> helped = new ArrayList<>();
            if (classes.get(0)==y.getClass()) {
                for (int j = 0; j < colsCount; j++) {
                    y.create(hide);
                    helped.add(y);
                }
                data.dataBase.put(cols.get(i), helped);
            }
        }
        for (Map.Entry<String, ArrayList<Value>> entry : dataBase.entrySet()) {
            for (Value i : entry.getValue()) {
                CooValue pair = (CooValue) i;
                data.dataBase.get(entry.getKey()).set(pair.getPlace(), pair.getObject());
            }
        }
        return data;
    }

    /**
     * Returns a String representation of SparseDataFrame object
     * @return a String representation of SparseDataFrame object
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
