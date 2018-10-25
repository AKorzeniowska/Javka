package pckg;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;


@SuppressWarnings("Duplicates")

public class DataFrame extends Object {
    Map<String, ArrayList<Value>> dataBase = new HashMap<>();
    List <Class <? extends Value>> classes=new ArrayList<>();
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
    public DataFrame(String [] colsInput, Class <? extends Value> [] typesInput){
        for (int i=0; i<colsInput.length; i++) {
            classes.add(typesInput[i]);
            cols.add(colsInput[i]);
        }

        for (int i=0; i<colsInput.length; i++) {
            ArrayList<Value> helped=new ArrayList<>();
            dataBase.put(colsInput[i], helped);
        }
    }

    /**
     * Constructor for DataFrame Class
     * Fills cols List with column names given in first line of the file
     * Fills columns with data from file
     * @param address String containing file's address
     * @param typesInput String Array of types that each column will keep
     * @throws IOException
     */
    public DataFrame(String address, Class <? extends Value> [] typesInput) throws IOException, InvocationTargetException, IllegalAccessException {

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
     * Costructor for DataFrame Class
     * Fills columns with data from file
     * @param address String containing file's address
     * @param typesInput String Array of types that each column will keep
     * @param colsInput String Array of names of columns
     * @throws IOException
     */
    /*public DataFrame(String address, Class <? extends Value> [] typesInput, String[] colsInput) throws IOException {

        FileInputStream fstream;
        BufferedReader br;
        fstream = new FileInputStream(address);
        if (fstream==null)
            throw new IOException("File not found!");
        else
            br=new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] separated;
        Value[] fixed=new Value[typesInput.length];

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
            for (int k = 0; k < separated.length; k++) {
                fixed[k]= //nowy obiekt typu z classes
            }
            addElement(fixed);
        }
    }*/

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
    public ArrayList<Value> get (String colname){
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
        Class <? extends Value> [] typeNames = new Class [colls.length];
        for (int i = 0; i < colls.length; i++) {
            for (int j = 0; j < classes.size(); j++) {
                if(colls[i].equals(cols.get(j))){
                    typeNames[i] = classes.get(j);
                }
            }
        }
        DataFrame result = new DataFrame(colls, typeNames);
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
    public DataFrame iloc (int i){
        DataFrame nowy=new DataFrame();


        nowy.cols=new ArrayList<>(cols);
        nowy.classes=new ArrayList<>(classes);
        int a=0;
        for (Map.Entry<String, ArrayList<Value>> entry: dataBase.entrySet()){
            ArrayList<Value> helped = new ArrayList<Value>();
            if (entry.getValue().size()>i)
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
    public DataFrame iloc (int from, int to){
        DataFrame nowy=new DataFrame();
        int x;
        nowy.classes=new ArrayList<>(classes);
        nowy.cols=new ArrayList<>(cols);
        int a=0;
        for (Map.Entry<String, ArrayList<Value>> entry: dataBase.entrySet()){
            ArrayList<Value> helped = new ArrayList<>();
            if (entry.getValue().size()>to) { x=to; }
            else {x=entry.getValue().size()-1; }
            for (int i = from; i <= x; i++) {
                Value z = entry.getValue().get(i);
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
                ", classes=" + classes +
                ", cols=" + cols +
                '}';
    }

    /**
     * Adds row to DataFrame object (adds element to every ArrayList in dataBase Map)
     * Checks whether number of given arguments is compatible to number of columns
     * Checks whether types of given arguments are compatible with type of each column
     * If type of argument is not a primitive type, checks whether its users type
     * Checks for ClassNotFoundException
     * @param input Array of Objects to put in columns
     */
    public void addElement(Value [] input){
        if (input.length!=cols.size()) {
            System.out.println("Nieodpowiednia ilość argumentów!");
            return;
        }
        int a=0;
        for (Value i : input) {
            if (classes.get(a)!=i.getClass()){
                System.out.println("typ danych niezgodny z kolumna");
                return;
            }
            a++;
        }
        a=0;
        for (Value i : input){
            dataBase.get(cols.get(a)).add(i);
            a++;
        }
    }
}
