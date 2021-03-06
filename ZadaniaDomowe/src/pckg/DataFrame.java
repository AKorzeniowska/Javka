package pckg;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;


@SuppressWarnings("Duplicates")

public class DataFrame {
    Map<String, ArrayList<Value>> dataBase = new HashMap<>();
    List<Class<? extends Value>> classes = new ArrayList<>();
    List<String> cols = new ArrayList<>();

    /**
     * Constructor for DataFrame Class
     * creates object with empty dataBase Map
     */
    public DataFrame() {
        dataBase.clear();
    }

    public DataFrame (DataFrame toCopy){
        cols=toCopy.cols;
        classes=toCopy.classes;
        for (Map.Entry<String, ArrayList<Value>> entry : toCopy.dataBase.entrySet()){
            dataBase.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Constructor for DataFrame Class
     * adds column types and names to types and cols Lists
     * creates new ArrayLists and puts them to dataBase Map
     *
     * @param typesInput String Array of types that each column will keep
     * @param colsInput  String Array of names of columns
     */
    public DataFrame(String[] colsInput, Class<? extends Value>[] typesInput) {
        for (int i = 0; i < colsInput.length; i++) {
            classes.add(typesInput[i]);
            cols.add(colsInput[i]);
        }

        for (int i = 0; i < colsInput.length; i++) {
            ArrayList<Value> helped = new ArrayList<>();
            dataBase.put(colsInput[i], helped);
        }
    }

    public DataFrame (List<String> colsInput, List<Class<? extends Value>> typesInput){
        classes=typesInput;
        cols=colsInput;
        for (int i=0; i<colsInput.size(); i++){
            ArrayList<Value> helped=new ArrayList<>();
            dataBase.put(colsInput.get(i), helped);
        }
    }

    /**
     * Constructor for DataFrame Class
     * Fills cols List with column names given in first line of the file
     * Fills columns with data from file
     *
     * @param address    String containing file's address
     * @param typesInput String Array of types that each column will keep
     * @throws IOException
     */
    public DataFrame(String address, Class<? extends Value>[] typesInput) throws IOException{

        FileInputStream fstream=null;
        BufferedReader br;
        try {
            fstream = new FileInputStream(address);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] separated;
        String[] colsInput = new String[typesInput.length];
        Value[] fixed = new Value[typesInput.length];

        strLine = br.readLine();
        separated = strLine.split(",");
        for (int i = 0; i < separated.length; i++) {
            colsInput[i] = separated[i];
        }
        for (int i = 0; i < typesInput.length; i++) {
            classes.add(typesInput[i]);
            cols.add(colsInput[i]);
        }

        for (int i = 0; i < typesInput.length; i++) {
            ArrayList<Value> helped = new ArrayList<>();
            dataBase.put(colsInput[i], helped);
        }

        Constructor[] methods=new Constructor[separated.length];
        strLine = br.readLine();
        separated = strLine.split(",");
        for (int i = 0; i < separated.length; i++) {
            try {
                methods[i] = classes.get(i).getConstructor(String.class);
                fixed[i] = (Value) methods[i].newInstance(separated[i]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        addElement(fixed);

        while ((strLine = br.readLine()) != null) {
//        for (int j = 0; j < 100000; j++) {
//            strLine = br.readLine();
            separated = strLine.split(",");
            for (int k = 0; k < separated.length; k++) {
                try {
                    fixed[k]=(Value) methods[k].newInstance(separated[k]);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
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
     *
     * @param address    String containing file's address
     * @param typesInput String Array of types that each column will keep
     * @param colsInput  String Array of names of columns
     * @throws IOException
     */
    public DataFrame(String address, Class<? extends Value>[] typesInput, String[] colsInput) throws IOException, InvocationTargetException, IllegalAccessException {

        FileInputStream fstream;
        BufferedReader br;
        fstream = new FileInputStream(address);
        if (fstream == null)
            throw new IOException("File not found!");
        else
            br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String[] separated;
        Value[] fixed = new Value[typesInput.length];

        for (int i = 0; i < typesInput.length; i++) {
            classes.add(typesInput[i]);
            cols.add(colsInput[i]);
        }

        for (int i = 0; i < typesInput.length; i++) {
            ArrayList<Value> helped = new ArrayList<>();
            dataBase.put(colsInput[i], helped);
        }
        //while ((strLine = br.readLine()) != null) {
        for (int j = 0; j < 30; j++) {
            strLine = br.readLine();
            separated = strLine.split(",");
            for (int k = 0; k < separated.length; k++) {
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
    }

    /**
     * Returns size of columns assuming every column has the same size
     *
     * @return size of a column
     */
    int size() {
        if (cols.size() != 0)
            return dataBase.get(cols.get(0)).size();
        return 0;
    }

    /**
     * Returns contents of a column indicated by its name
     *
     * @param colname String with name of the column
     * @return contents of indicated column
     */
    public ArrayList<Value> get(String colname) {
        return dataBase.get(colname);
    }

    /**
     * Creates new empty DataFrame object, ready to be filled with data
     * New object has the same columns' names and classes as original object
     *
     * @return new empty DataFrame
     */
    public DataFrame emptyDataFrame() {
        DataFrame nowy = new DataFrame();
        nowy.cols = cols;
        nowy.classes = classes;
        for (int i = 0; i < cols.size(); i++) {
            nowy.dataBase.put(cols.get(i), new ArrayList<>());
        }
        return nowy;
    }

    public DataFrame emptyWithoutData(){
        DataFrame nowy = new DataFrame();
        for (int i=0; i<classes.size(); i++){
            if (!classes.get(i).equals(DateTimeValue.class)){
                nowy.classes.add(classes.get(i));
                nowy.cols.add(cols.get(i));
            }
        }
        for (int i = 0; i < nowy.cols.size(); i++) {
            nowy.dataBase.put(nowy.cols.get(i), new ArrayList<>());
        }
        return nowy;
    }

    public Value minInCol (String a){
        List<Value> list=this.get(a);
        Value x = creator("100000", list.get(0));
        for (Value z : list){
            if (z.lte(x))
                x=z;
        }
        return x;
    }

    public Value maxInCol (String a){
        List<Value> list=this.get(a);
        Value x = creator("-100000", list.get(0));
        for (Value z : list){
            if (z.gte(x))
                x=z;
        }
        return x;
    }

    /**
     * Creates copy of indicated columns from DataFrame object
     * Depending on copy boolean, creates deep or shallow copy
     *
     * @param colls String Array with names of the columns to copy
     * @param copy  boolean, where: true gives deep copy, false gives shallow copy
     * @return DataFrame object with copied columns
     */
    public DataFrame get(String[] colls, boolean copy) {
        Class<? extends Value>[] typeNames = new Class[colls.length];
        for (int i = 0; i < colls.length; i++) {
            for (int j = 0; j < classes.size(); j++) {
                if (colls[i].equals(cols.get(j))) {
                    typeNames[i] = classes.get(j);
                }
            }
        }
        DataFrame result = new DataFrame(colls, typeNames);
        if (copy) {
            result.dataBase = new HashMap<>();
            for (String coll : colls) {
                result.dataBase.put(coll, new ArrayList<>(dataBase.get(coll)));
            }
        } else {
            result.dataBase = new HashMap<>();
            for (String coll : colls) {
                result.dataBase.put(coll, dataBase.get(coll));
            }
        }
        return result;
    }

    /**
     * Creates new DataFrame object storing only one indicated row
     *
     * @param i number of row to copy
     * @return DataFrame object
     */
    public DataFrame iloc(int i) {
        DataFrame nowy = new DataFrame();
        nowy.cols = new ArrayList<>(cols);
        nowy.classes = new ArrayList<>(classes);
        for (Map.Entry<String, ArrayList<Value>> entry : dataBase.entrySet()) {
            ArrayList<Value> helped = new ArrayList<Value>();
            if (entry.getValue().size() > i)
                helped.add(entry.getValue().get(i));
            nowy.dataBase.put(entry.getKey(), helped);
        }
        return nowy;
    }


    /**
     * Creates new DataFrame object
     * Stores only indicated rows ranging from Integer from to Integer to
     *
     * @param from number of first row
     * @param to   number of last row
     * @return DataFrame object with only indicated rows
     */
    public DataFrame iloc(int from, int to) {
        DataFrame nowy = new DataFrame();
        int x;
        nowy.classes = new ArrayList<>(classes);
        nowy.cols = new ArrayList<>(cols);
        int a = 0;
        for (Map.Entry<String, ArrayList<Value>> entry : dataBase.entrySet()) {
            ArrayList<Value> helped = new ArrayList<>();
            if (entry.getValue().size() > to) {
                x = to;
            } else {
                x = entry.getValue().size() - 1;
            }
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
     * Adds one DataFrame object at the end of another DataFrame object as new rows
     * Using iloc(i) as an argument allows to add only one exact row
     * NOTE: doesn't check whether columns' names are the same in both DataFrames
     *
     * @param a DataFrame object to add at the end
     * @return this DataFrame object with added rows
     */
    public DataFrame addRow(DataFrame a) {
        for (Map.Entry<String, ArrayList<Value>> entry : a.dataBase.entrySet()) {
            for (int s = 0; s < entry.getValue().size(); s++) {
                dataBase.get(entry.getKey()).add(entry.getValue().get(s));
            }
        }
        return this;
    }


    /**
     * Creates new Value object; its class is the same as given get Value object
     * new Value object is created according to given String value
     * function checks for NoSuchMethodException, IllegalAccessException, InvocationTargetException
     *
     * @param value String value of value that new Value object will contain
     * @param get   Value object; its class will be passed while creating new Value
     * @return new Value object with the same class as get Value object
     */
    public static Value creator(String value, Value get) {
        Value x = null;
        try {
            Method getInstance = get.getClass().getMethod("getInstance");
            Object instancja = getInstance.invoke(null);
            Method method = get.getClass().getMethod("create", String.class);
            x = (Value) method.invoke(instancja, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Value creatorFromClass(String value, Class<? extends Value> get) {
        Value x = null;
        try {
            Method getInstance = get.getMethod("getInstance");
            Object instancja = getInstance.invoke(null);
            Method method = get.getMethod("create", String.class);
            x = (Value) method.invoke(instancja, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return x;
    }

    public Value[] wholeColumn (String a){
        Value [] output=new Value[this.dataBase.get(a).size()];
        for (int i=0; i<this.dataBase.get(a).size(); i++)
            output[i]=this.dataBase.get(a).get(i);
        return output;
    }

    /**
     * Returns a String representation of DataFrame object
     *
     * @return a String representation of DataFrame object
     */

    @Override
    public String toString() {
        String toString = new String();
        for (String a : cols) {
            toString += a+" ";
        }
        toString+="\n";
        for (int z=0; z<dataBase.get(cols.get(0)).size(); z++) {
            for (String i : cols) {
                toString+=dataBase.get(i).get(z)+" ";
            }
            toString+="\n";
        }
        return toString;
    }

    /**
     * Adds row to DataFrame object (adds element to every ArrayList in dataBase Map)
     * Checks whether number of given arguments is compatible to number of columns
     * Checks whether types of given arguments are compatible with type of each column
     * If type of argument is not a primitive type, checks whether its users type
     * Checks for ClassNotFoundException
     *
     * @param input Array of Objects to put in columns
     */
    public void addElement (Value[] input) {
        if (input.length != cols.size()) {
            System.out.println("Nieodpowiednia ilość argumentów!");
            return;
        }
        int a = 0;
        for (Value i : input) {
            try {
                if (classes.get(a) != i.getClass())
                    throw new WrongValueTypeException();
            } catch (WrongValueTypeException e) {
                System.out.println("typ danych niezgodny z kolumna: " + classes.get(a) + " " + i.getClass());
                e.columnName=this.cols.get(a);
                e.rowNumber=this.dataBase.get(cols.get(a)).size();
                return;
            }
            a++;
        }
        a = 0;
        for (Value i : input) {
            dataBase.get(cols.get(a)).add(i);
            a++;
        }
    }

    /**
     * Inner class, enables grouping data in DataFrames
     * Contains HashMap in which the Keys are different Values from columns by which the DataFrame is grouped
     * The Values in HashMap are smaller DataFrames
     * Implements Groupby interface, that enables finding specific values in smaller DataFrames
     */
    public class DataMap extends DataFrame implements Groupby {
        HashMap<List<Value>, DataFrame> map = new HashMap<>();

        public DataMap() {
            map.clear();
        }

        public void addFrame (List<Value> keys, DataFrame add){
            this.map.put(keys, add);
        }

        @Override
        public String toString() {
            String toString = new String();
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                toString += entry.getKey().toString() + " : " + entry.getValue().toString() + "\n";
            }
            return toString;
        }

        /**
         * For every smaller DataFrame searches for max Value
         * Creates new max Value element equal to -1000000 according to each column type
         * Iterates on each column and checks whether Value object is bigger than current max Value
         * Puts found max Value to Value array; when Value array has the right amount of elements, it's put into new DataFrame by addElemet()
         *
         * @return new DataFrame object containing only max Values for each Key
         */
        @Override
        public DataFrame max() {
            DataFrame nowy = emptyDataFrame();
            Value[] input = new Value[cols.size()];
            Value x = null;
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                for (int k = 0; k < cols.size(); k++) {
                    x = creator("-1000000", entry.getValue().dataBase.get(cols.get(k)).get(0));
                    for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                        if (z.gte(x)) {
                            x = z;
                        }
                    }
                    if (entry.getKey().size() > iterator)
                        input[iterator] = entry.getKey().get(iterator);
                    else
                        input[iterator] = x;
                    iterator++;
                    if (iterator == cols.size()) {
                        iterator = 0;
                        nowy.addElement(input);
                    }
                }
            }
            return nowy;
        }

        /**
         * For every smaller DataFrame searches for min Value
         * Creates new min Value element equal to 1000000 according to each column type
         * Iterates on each column and checks whether Value object is smaller than current min Value
         * Puts found min Value to Value array; when Value array has the right amount of elements, it's put into new DataFrame by addElemet()
         *
         * @return new DataFrame object containing only min Values for each Key
         */
        @Override
        public DataFrame min() {
            DataFrame nowy = emptyDataFrame();
            Value[] input = new Value[cols.size()];
            Value x = null;
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                for (int k = 0; k < cols.size(); k++) {
                    x = creator("100000", entry.getValue().dataBase.get(cols.get(k)).get(0));
                    for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                        if (z.lte(x))
                            x = z;
                    }
                    if (entry.getKey().size() > iterator)
                        input[iterator] = entry.getKey().get(iterator);
                    else
                        input[iterator] = x;
                    iterator++;
                    if (iterator == cols.size()) {
                        iterator = 0;
                        nowy.addElement(input);
                    }
                }
            }
            return nowy;
        }

        /**
         * For every smaller DataFrame counts mean Value
         * Creates new Value element equal to 0 according to each column type
         * Iterates on each column and sums all Values
         * Divides counted sum by number of elements in column creating mean
         * Puts found mean Value to Value array; when Value array has the right amount of elements, it's put into new DataFrame by addElemet()
         *
         * @return new DataFrame object containing only mean Values for each Key
         */
        @Override
        public DataFrame mean() {
            DataFrame nowy=null;
            int flag=0;
            List<List<Value>> siup=new ArrayList<>(map.keySet());
            for (Value a : siup.get(0)){
                if (a.getClass().equals(DateTimeValue.class)) {
                    flag = 1;
                    break;
                }
            }
            if (flag==1)
                nowy=emptyDataFrame();
            else
                nowy=emptyWithoutData();
            Value[] input = new Value[nowy.cols.size()];
            Value x = null;
            int counter = 0;
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                for (int k = 0; k < cols.size(); k++) {
                    if (!classes.get(k).equals(DateTimeValue.class) || flag==1) {
                        x = creator("0", entry.getValue().dataBase.get(cols.get(k)).get(0));
                        for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                            x.add(z);
                            counter++;
                        }
                        Value count = x.create(String.valueOf(counter));
                        try {
                            if (count.eq(count.create("0")))
                                throw new DividingByZeroException();
                            x.div(count);
                        }
                        catch (DividingByZeroException e){
                            System.out.println("Dzielenie przez 0!");
                        }
                        if (entry.getKey().size() > iterator)
                            input[iterator] = entry.getKey().get(iterator);
                        else
                            input[iterator] = x;
                        iterator++;
                        counter = 0;
                        if (iterator == nowy.cols.size()) {
                            iterator = 0;
                            nowy.addElement(input);
                        }
                    }
                }
            }
            return nowy;
        }

        /**
         * For every smaller DataFrame counts standard deviation Value
         * Creates new Value element equal to 0 according to each column type
         * Iterates on each column and sums all Values
         * Divides counted sum by number of elements in column creating mean
         * Iterates again on column, for each element counts new var Value equal to ((element from column).sub(created mean)).pow(2)
         * Adds each var Value to sum, then sum=squareRoot(sum)
         * Puts found sum Value to Value array; when Value array has the right amount of elements, it's put into new DataFrame by addElemet()
         *
         * @return new DataFrame object containing only standard deviation Values for each Key
         */
        @Override
        public DataFrame std() {
            DataFrame nowy=null;
            int flag=0;
            List<List<Value>> siup=new ArrayList<>(map.keySet());
            for (Value a : siup.get(0)){
                if (a.getClass().equals(DateTimeValue.class)) {
                    flag = 1;
                    break;
                }
            }
            if (flag==1)
                nowy=emptyDataFrame();
            else
                nowy=emptyWithoutData();
            Value[] input = new Value[nowy.cols.size()];
            Value x = null;
            int counter = 0;
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                for (int k = 0; k < cols.size(); k++) {
                    if (!classes.get(k).equals(DateTimeValue.class) || flag==1) {
                        x = creator("0", entry.getValue().dataBase.get(cols.get(k)).get(0));
                        for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                            x.add(z);
                            counter++;
                        }
                        Value count = x.create(String.valueOf(counter));
                        try {
                            if (count.eq(count.create("0")))
                                throw new DividingByZeroException();
                            x.div(count);
                        } catch (DividingByZeroException e) {
                            System.out.println("Dzielenie przez 0!");
                        }
                        Value sum = x.create(String.valueOf("0"));
                        for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                            Value d = z.create(z.toString());
                            Value var = (d.sub(x)).pow(d.create("2"));
                            sum.add(var);
                        }
                        try {
                            if (count.eq(count.create("0")))
                                throw new DividingByZeroException();
                            sum.div(count);
                        } catch (DividingByZeroException e) {
                            System.out.println("Dzielenie przez 0!");
                        }
                        sum.pow(sum.create("0.5"));
                        if (entry.getKey().size() > iterator)
                            input[iterator] = entry.getKey().get(iterator);
                        else
                            input[iterator] = sum;
                        iterator++;
                        counter = 0;
                        if (iterator == nowy.cols.size()) {
                            iterator = 0;
                            nowy.addElement(input);
                        }
                    }
                }
            }
            return nowy;
        }

        /**
         * For every smaller DataFrame counts standard deviation Value
         * Creates new Value element equal to 0 according to each column type
         * Iterates on each column and sums all Values
         * Puts found sums Value to Value array; when Value array has the right amount of elements, it's put into new DataFrame by addElemet()
         *
         * @return new DataFrame object containing only sum Values for each Key
         */
        @Override
        public DataFrame sum() {
            DataFrame nowy=null;
            int flag=0;
            List<List<Value>> siup=new ArrayList<>(map.keySet());
            for (Value a : siup.get(0)){
                if (a.getClass().equals(DateTimeValue.class)) {
                    flag = 1;
                    break;
                }
            }
            if (flag==1)
                nowy=emptyDataFrame();
            else
                nowy=emptyWithoutData();

            Value[] input = new Value[nowy.cols.size()];
            Value x = null;
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                for (int k = 0; k < cols.size(); k++) {
                    if (!classes.get(k).equals(DateTimeValue.class) || flag==1) {
                        x = creator("0", entry.getValue().dataBase.get(cols.get(k)).get(0));
                        for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                            x.add(z);
                        }
                        if (entry.getKey().size() > iterator)
                            input[iterator] = entry.getKey().get(iterator);
                        else
                            input[iterator] = x;
                        iterator++;
                        if (iterator == nowy.cols.size()) {
                            iterator = 0;
                            nowy.addElement(input);
                        }
                    }
                }
            }
            return nowy;
        }

        /**
         * For every smaller DataFrame counts variation Value
         * Creates new Value element equal to 0 according to each column type
         * Iterates on each column and sums all Values
         * Divides counted sum by number of elements in column creating mean
         * Iterates again on column, for each element counts new var Value equal to ((element from column).sub(created mean)).pow(2)
         * Adds each var Value to sum
         * Puts found sum Value to Value array; when Value array has the right amount of elements, it's put into new DataFrame by addElemet()
         *
         * @return new DataFrame object containing only variation Values for each Key
         */
        @Override
        public DataFrame var() {
            DataFrame nowszy=null;
            int flag=0;
            List<List<Value>> siup=new ArrayList<>(map.keySet());
            for (Value a : siup.get(0)){
                if (a.getClass().equals(DateTimeValue.class)) {
                    flag = 1;
                    break;
                }
            }
            if (flag==1)
                nowszy=emptyDataFrame();
            else
                nowszy=emptyWithoutData();
            Value[] input = new Value[nowszy.cols.size()];
            Value x = null;
            int counter = 0;
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                for (int k = 0; k < cols.size(); k++) {
                    if (!classes.get(k).equals(DateTimeValue.class) || flag==1) {
                        x = creator("0", entry.getValue().dataBase.get(cols.get(k)).get(0));
                        for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                            x.add(z);
                            counter++;
                        }
                        Value count = x.create(String.valueOf(counter));
                        try {
                            if (count.eq(count.create("0")))
                                throw new DividingByZeroException();
                            x.div(count);
                        } catch (DividingByZeroException e) {
                            System.out.println("Dzielenie przez 0!");
                            return null;
                        }
                        Value sum = x.create(String.valueOf("0"));
                        for (Value z : entry.getValue().dataBase.get(cols.get(k))) {
                            Value d = z.create(z.toString());
                            Value var = (d.sub(x)).pow(d.create("2"));
                            sum = sum.add(var);
                        }
                        try {
                            if (count.eq(count.create("0")))
                                throw new DividingByZeroException();
                            sum.div(count);
                        } catch (DividingByZeroException e) {
                            System.out.println("Dzielenie przez 0!");
                            return null;
                        }
                        if (entry.getKey().size() > iterator)
                            input[iterator] = entry.getKey().get(iterator);
                        else
                            input[iterator] = sum;
                        iterator++;
                        counter = 0;
                        if (iterator == nowszy.cols.size()) {
                            iterator = 0;
                            nowszy.addElement(input);
                        }
                    }
                }
            }
            return nowszy;
        }

        @Override
        public DataFrame apply(Applyable x) {
            return null;
        }
    }

    /**
     * Creates new DataMap object, which contains new, smaller DataFrame objects grouped by given columns
     * Iterates through given columns' Values and adds them as Keys to map if they're not in KeySet()
     * Adds a row to DataFrame object stored as map's Value using addRow(iloc(current_row))
     *
     * @param colnames String with names of columns to group by
     * @return new DataMap object
     */
    public DataMap groupby(String[] colnames) {
        DataMap var = new DataMap();
        var.cols = cols;
        var.classes = classes;

        for (int j = 0; j < this.dataBase.get(cols.get(0)).size(); j++) {
            List<Value> sign = new ArrayList<>();
            for (int i = 0; i < colnames.length; i++) {
                sign.add(this.dataBase.get(colnames[i]).get(j));
            }
            if (var.map.containsKey(sign))
                var.map.get(sign).addRow(iloc(j));
            else
                var.map.put(sign, emptyDataFrame().addRow(iloc(j)));
        }
        return var;
    }

    public DataFrame addingValue (Value a, String [] colnames){
        for (int i=0; i<colnames.length; i++){
            for (Value x : dataBase.get(colnames[i])){
                if (x.getClass().equals(a.getClass()))
                    x.add(a);
            }
        }
        return this;
    }

    public DataFrame subtractingValue (Value a, String [] colnames){
        for (int i=0; i<colnames.length; i++){
            for (Value x : dataBase.get(colnames[i])){
                if (x.getClass().equals(a.getClass()))
                    x.sub(a);
            }
        }
        return this;
    }

    public DataFrame multiplyingValue (Value a, String [] colnames){
        for (int i=0; i<colnames.length; i++){
            for (Value x : dataBase.get(colnames[i])){
                if (x.getClass().equals(a.getClass()))
                    x.mul(a);
            }
        }
        return this;
    }

    public DataFrame dividingValue (Value a, String [] colnames){
        for (int i=0; i<colnames.length; i++){
            for (Value x : dataBase.get(colnames[i])){
                if (x.getClass().equals(a.getClass())) {
                    try {
                        if (a.eq(a.create("0")))
                            throw new DividingByZeroException();
                        x.div(a);
                    } catch (DividingByZeroException e) {
                        System.out.println("Dzielenie przez 0!");
                        return null;
                    }
                }
            }
        }
        return this;
    }

    public DataFrame addingColumn (List<Value> column, String [] colnames){
        try{
            if (column.size()!=dataBase.get(cols.get(0)).size())
                throw new InvalidColumnSizeException();
        } catch (InvalidColumnSizeException e){
            System.out.println("Niepoprawny rozmiar dodawanej kolumny!");
            return null;
        }
        for (int i=0; i<colnames.length; i++){
            for (int z=0; z< column.size(); z++){
                if (dataBase.get(colnames[i]).get(0).getClass().equals(column.get(0).getClass()))
                    dataBase.get(colnames[i]).get(z).add(column.get(z));
            }
        }
        return this;
    }

    public DataFrame subtractingColumn (List<Value> column, String [] colnames){
        try{
            if (column.size()!=dataBase.get(cols.get(0)).size())
                throw new InvalidColumnSizeException();
        } catch (InvalidColumnSizeException e){
            System.out.println("Niepoprawny rozmiar dodawanej kolumny!");
            return null;
        }
        for (int i=0; i<colnames.length; i++){
            for (int z=0; z< column.size(); z++){
                if (dataBase.get(colnames[i]).get(0).getClass().equals(column.get(0).getClass()))
                    dataBase.get(colnames[i]).get(z).sub(column.get(z));
            }
        }
        return this;
    }

    public DataFrame multiplyingColumn (List<Value> column, String [] colnames){
        try{
            if (column.size()!=dataBase.get(cols.get(0)).size())
                throw new InvalidColumnSizeException();
        } catch (InvalidColumnSizeException e){
            System.out.println("Niepoprawny rozmiar dodawanej kolumny!");
            return null;
        }
        for (int i=0; i<colnames.length; i++){
            for (int z=0; z< column.size(); z++){
                if (dataBase.get(colnames[i]).get(0).getClass().equals(column.get(0).getClass()))
                    dataBase.get(colnames[i]).get(z).mul(column.get(z));
            }
        }
        return this;
    }

    public DataFrame dividingColumn (List<Value> column, String [] colnames){
        try{
            if (column.size()!=dataBase.get(cols.get(0)).size())
                throw new InvalidColumnSizeException();
        } catch (InvalidColumnSizeException e){
            System.out.println("Niepoprawny rozmiar dodawanej kolumny!");
            return null;
        }
        for (int i=0; i<colnames.length; i++){
            for (int z=0; z< column.size(); z++){
                if (dataBase.get(colnames[i]).get(0).getClass().equals(column.get(0).getClass())) {
                    try {
                        if (column.get(z).eq(column.get(z).create("0")))
                            throw new DividingByZeroException();
                        dataBase.get(colnames[i]).get(z).div(column.get(z));
                    } catch (DividingByZeroException e) {
                        System.out.println("Dzielenie przez 0! \nBłąd w rzędzie nr "+(z+1));
                        e.rowNumber=z;
                    }
                }
            }
        }
        return this;
    }

    public List<String> getCols (){
        return this.cols;
    }

    public List<Class<? extends Value>> getClasses(){
        return this.classes;
    }

    public Class<? extends Value> getClassOfCol (String a){
        int z=cols.indexOf(a);
        return classes.get(z);
    }

    public int numberOfRows(){
        if (cols.isEmpty())
            return 0;
        return dataBase.get(cols.get(0)).size();
    }

    public ArrayList<Value> getRow (int i){
        ArrayList <Value> output=new ArrayList<>();
        for (String col : cols){
            output.add(dataBase.get(col).get(i));
        }
        return output;
    }
}
