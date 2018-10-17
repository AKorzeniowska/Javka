package pckg;
import java.io.*;
import java.util.*;

public class DataFrame extends Object {
    Map<String, ArrayList<Object>> dataBase = new HashMap<>();
    List<String> types = new ArrayList<>();
    List<String> cols = new ArrayList<>();

    /**
     * Constructor for DataFrame Class
     * creates object with empty dataBase Map
     */
    public DataFrame() {
        dataBase.clear();
    }

    /**
     * Constructor for DataFrame Class
     * adds column types and names to types and cols Lists
     * creates new ArrayLists and puts them to dataBase Map
     * @param typesInput String Array of types that each column will keep
     * @param colsInput String Array of names of columns
     */
    public DataFrame(String[] typesInput, String[] colsInput){

        for (int i=0; i<typesInput.length; i++) {
            types.add(typesInput[i]);
            cols.add(colsInput[i]);
        }

        for (int i=0; i<typesInput.length; i++) {
            ArrayList<Object> helped=new ArrayList<>();
            dataBase.put(colsInput[i], helped);
        }
    }

    public DataFrame(String address, String[] typesInput) throws IOException {

        FileInputStream fstream;
        BufferedReader br;
        fstream = new FileInputStream(address);
        if (fstream==null)
            throw new IOException("File not found!");
        else
            br=new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] separated;
        String[] colsInput=new String[typesInput.length];
        int flag=0;
        while ((strLine = br.readLine()) != null) {
            separated=strLine.split(",");
            if (flag==0){
                for (int i=0; i<separated.length; i++){
                    colsInput[i]=separated[i];
                }
                for (int i=0; i<typesInput.length; i++) {
                    types.add(typesInput[i]);
                    cols.add(colsInput[i]);
                }

                for (int i=0; i<typesInput.length; i++) {
                    ArrayList<Object> helped=new ArrayList<>();
                    dataBase.put(colsInput[i], helped);
                }
                flag=1;
            }
            else if (flag==1){
                System.out.println(separated.toString());
                //addElement(separated[0], separated[1], separated[2]);
            }
        }
    }

    /**
     * Returns size of columns assuming every column has the same size
     * @return size of a column
     */
    int size(){
        if (cols.size()!=0)
           return dataBase.get(cols.get(0)).size();
        return 0;
    }

    /**
     * Returns contents of a column indicated by its name
     * @param colname String with name of the column
     * @return contents of indicated column
     */
    ArrayList<Object> get (String colname){
        return dataBase.get(colname);
    }

    /**
     * Creates copy of indicated columns from DataFrame object
     * Depending on copy boolean, creates deep or shallow copy
     * @param colls String Array with names of the columns to copy
     * @param copy boolean, where: true gives deep copy, false gives shallow copy
     * @return DataFrame object with copied columns
     */
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

    /**
     * Creates new DataFrame object storing only one indicated row
     * @param i number of row to copy
     * @return DataFrame object
     */
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

    /**
     * Creates new DataFrame object
     * Stores only indicated rows ranging from Integer from to Integer to
     * @param from number of first row
     * @param to number of last row
     * @return DataFrame object with only indicated rows
     */
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

    /**
     * Returns a String representation of DataFrame object
     * @return a String representation of DataFrame object
     */
    @Override
    public String toString() {
        return "DataFrame{" +
                "dataBase=" + dataBase +
                ", types=" + types +
                ", cols=" + cols +
                '}';
    }

    /**
     * Adds row to DataFrame object (adds element to every ArrayList in dataBase Map)
     * Checks whether number of given arguments is compatible to number of columns
     * Checks whether types of given arguments are compatible with type of each column
     * If type of argument is not a primitive type, checks whether its users type
     * Checks for ClassNotFoundException
     * @param input elements to put in each column
     */
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
