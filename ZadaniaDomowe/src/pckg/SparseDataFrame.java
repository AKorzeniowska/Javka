package pckg;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("Duplicates")

public class SparseDataFrame extends DataFrame {
    String hide = new String();
    int colsCount;

    /**
     * Constructor for SparseDataFrame Class, inherited from DataFrame Class
     * creates object with empty DataBase map and empty hide String
     */
    public SparseDataFrame (){
        super();
        hide="";
    }
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
    public SparseDataFrame(String address, String toHide, Class <? extends Value> [] typesInput) throws IOException, InvocationTargetException, IllegalAccessException {
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
        Value[] fixed=new Value[typesInput.length];

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
            strLine=br.readLine();
            separated=strLine.split(",");
            for (int k=0; k<separated.length; k++){
                try {
                    Method getInstance = typesInput[k].getMethod("getInstance");
                    Object instancja = getInstance.invoke(null);
                    Method method = typesInput[k].getMethod("create", String.class);
                    fixed[k] = (Value) method.invoke(instancja, separated[k]);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            addElement(fixed);
        }
        br.close();
    }

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
            if (classes.get(a) != i.getClass()) {
                System.out.println("typ danych niezgodny z kolumna");
                return;
            }
            a++;
        }
        a = 0;
        for (Value i : input) {
            if (!hide.equals(i.toString())) {
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
                    Value z=y.create(hide);
                    helped.add(z);
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
     * Creates new SparseDataFrame object storing data from only one indicated row (if such data exist)
     * @param i number of row to copy
     * @return SparseDataFrame object with one row at max
     */
    public SparseDataFrame iloc (int i){
        SparseDataFrame nowy = new SparseDataFrame();
        nowy.cols=new ArrayList<>(cols);
        nowy.classes=new ArrayList<>(classes);
        for (int k=0; k<cols.size(); k++){
            nowy.dataBase.put(cols.get(k), new ArrayList<>());
        }
        if (colsCount<=i)
            return nowy;
        for (Map.Entry<String, ArrayList<Value>> entry : dataBase.entrySet()){
            for (Value x : entry.getValue()){
                CooValue y=(CooValue) x;
                if (y.getPlace()==i){
                    nowy.dataBase.get(entry.getKey()).add(y);
                }
            }
        }
        return nowy;
    }

    /**Creates new SparseDataFrame object
     * Stores only data from indicated rows (if such data exist) ranging from Integer a to Integer b
     * @param a number of first row
     * @param b number of last row
     * @return SparseDataFrame object with data from indicated rows
     */
    public SparseDataFrame iloc (int a, int b){
        SparseDataFrame nowy = new SparseDataFrame();
        nowy.cols=new ArrayList<>(cols);
        nowy.classes=new ArrayList<>(classes);
        for (int k=0; k<cols.size(); k++){
            nowy.dataBase.put(cols.get(k), new ArrayList<>());
        }
        if (colsCount<=a)
            return nowy;
        for (Map.Entry<String, ArrayList<Value>> entry : dataBase.entrySet()){
            for (Value x : entry.getValue()){
                CooValue y=(CooValue) x;
                if (y.getPlace()>=a && y.getPlace()<=b){
                    nowy.dataBase.get(entry.getKey()).add(y);
                }
            }
        }
        return nowy;
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
