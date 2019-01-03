package pckg;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("Duplicates")

public class DataFrameThreads extends DataFrame {
    static DataFrame counted;
    static DataFrame given;
    static List<Value> keys;
    static String func = "";

    public DataFrameThreads() {
        super();
    }

    public DataFrameThreads(String[] colsInput, Class<? extends Value>[] typesInput) {
        super(colsInput, typesInput);
    }

    public DataFrameThreads(List<String> colsInput, List<Class<? extends Value>> typesInput) {
        cols = colsInput;
        classes = typesInput;
        for (int i = 0; i < colsInput.size(); i++) {
            ArrayList<Value> helped = new ArrayList<>();
            dataBase.put(colsInput.get(i), helped);
        }
    }

    public DataFrameThreads(String address, Class<? extends Value>[] typesInput) throws IOException {
        super(address, typesInput);
    }

    @Override
    public DataMapThread groupby(String[] colnames) {
        DataMapThread var = new DataMapThread();
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

    public class DataMapThread extends DataFrame.DataMap {

        public DataMapThread() {
            super();
        }

        public DataFrame max() {
            func = "max";
            return uni();
        }

        public DataFrame min() {
            func = "min";
            return uni();
        }

        public DataFrame mean() {
            func = "mean";
            return uni();
        }

        public DataFrame var() {
            func = "var";
            return uni();
        }

        public DataFrame std() {
            func = "std";
            return uni();
        }

        public DataFrame sum() {
            func = "sum";
            return uni();
        }


        public DataFrame uni() {
            int iterator = 0;
            for (Map.Entry<List<Value>, DataFrame> entry : map.entrySet()) {
                if (iterator == 0) {
                    if (func.equals("max") || func.equals("min"))
                        counted = entry.getValue().emptyDataFrame();
                    else
                        counted = entry.getValue().emptyWithoutData();
                }
                Threading thread=new Threading();
                thread.init(entry.getKey(), entry.getValue());
                thread.start();
                iterator++;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return counted;
        }

        public Map<List<Value>, DataFrame> returnMap(){
            return this.map;
        }

    }

    public class Threading extends Thread{
        private List<Value> keyed;
        private DataFrame given;

        public void init(List<Value> keys, DataFrame give){
            this.keyed=keys;
            this.given=give;
        }

        @Override
        public void run(){
            if (func.equals("max"))
                maxing(keyed, given);
            else if (func.equals("min"))
                mining(keyed, given);
            else if (func.equals("mean"))
                meaning(keyed, given);
            else if (func.equals("sum"))
                suming(keyed, given);
            else if (func.equals("var"))
                varing(keyed, given);
            else if (func.equals("std"))
                stding(keyed, given);
        }
    }

    public synchronized void add(DataFrame toAdd){
        counted.addRow(toAdd);
    }

    public void maxing(List<Value> keyed, DataFrame dated) {
        DataFrame computed = counted.emptyDataFrame();
        Value[] input = new Value[dated.cols.size()];
        Value x = null;
        int iterator = 0;
        for (int k = 0; k < dated.cols.size(); k++) {
            x = given.creator("-1000", dated.dataBase.get(dated.cols.get(k)).get(0));
            for (Value z : dated.dataBase.get(dated.cols.get(k))) {
                if (z.gte(x)) {
                    x = z;
                }
            }
            if (keyed.size() > iterator)
                input[iterator] = keyed.get(iterator);
            else
                input[iterator] = x;
            iterator++;
            if (iterator == dated.cols.size()) {
                iterator = 0;
                computed.addElement(input);
            }
        }
        System.out.println(computed);
        add(computed);
    }

    public void mining(List<Value> keyed, DataFrame dated) {
        DataFrame computed = counted.emptyDataFrame();
        Value[] input = new Value[dated.cols.size()];
        Value x = null;
        int iterator = 0;
        for (int k = 0; k < dated.cols.size(); k++) {
            x = dated.creator("1000000", dated.dataBase.get(dated.cols.get(k)).get(0));
            for (Value z : dated.dataBase.get(dated.cols.get(k))) {
                if (z.lte(x)) {
                    x = z;
                }
            }
            if (keyed.size() > iterator)
                input[iterator] = keyed.get(iterator);
            else
                input[iterator] = x;
            iterator++;
            if (iterator == dated.cols.size()) {
                iterator = 0;
                computed.addElement(input);
            }
        }
        counted.addRow(computed);
    }

    public void meaning(List<Value> keyed, DataFrame dated) {
        DataFrame computed = counted.emptyWithoutData();
        Value[] input = new Value[computed.cols.size()];
        Value x = null;
        int counter = 0;
        int iterator = 0;
        for (int k = 0; k < dated.cols.size(); k++) {
            if (!dated.getClasses().get(k).equals(DateTimeValue.class)) {
                x = dated.creator("0", dated.dataBase.get(dated.cols.get(k)).get(0));
                for (Value z : dated.dataBase.get(dated.cols.get(k))) {
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
                if (keyed.size() > iterator)
                    input[iterator] = keyed.get(iterator);
                else
                    input[iterator] = x;
                iterator++;
                counter = 0;
                if (iterator == computed.cols.size()) {
                    iterator = 0;
                    computed.addElement(input);
                }
            }
        }
        System.out.println(computed);
        counted.addRow(computed);
    }

    public void suming(List<Value> keyed, DataFrame dated) {
        DataFrame computed = counted.emptyWithoutData();
        Value[] input = new Value[computed.cols.size()];
        Value x = null;
        int counter = 0;
        int iterator = 0;
        for (int k = 0; k < dated.cols.size(); k++) {
            if (!dated.getClasses().get(k).equals(DateTimeValue.class)) {
                x = dated.creator("0", dated.dataBase.get(dated.cols.get(k)).get(0));
                for (Value z : dated.dataBase.get(dated.cols.get(k))) {
                    x.add(z);
                }
                if (keyed.size() > iterator)
                    input[iterator] = keys.get(iterator);
                else
                    input[iterator] = x;
                iterator++;
                counter = 0;
                if (iterator == computed.cols.size()) {
                    iterator = 0;
                    computed.addElement(input);
                }
            }
        }
        counted.addRow(computed);
    }

    public void varing(List<Value> keyed, DataFrame dated) {
        DataFrame computed = counted.emptyWithoutData();
        Value[] input = new Value[computed.cols.size()];
        Value x = null;
        int counter = 0;
        int iterator = 0;
        for (int k = 0; k < dated.cols.size(); k++) {
            if (!dated.getClasses().get(k).equals(DateTimeValue.class)) {
                x = dated.creator("0", dated.dataBase.get(dated.cols.get(k)).get(0));
                for (Value z : dated.dataBase.get(dated.cols.get(k))) {
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
                    return;
                }
                Value sum = x.create(String.valueOf("0"));
                for (Value z : dated.dataBase.get(dated.cols.get(k))) {
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
                    return;
                }
                if (keyed.size() > iterator)
                    input[iterator] = keyed.get(iterator);
                else
                    input[iterator] = x;
                iterator++;
                counter = 0;
                if (iterator == computed.cols.size()) {
                    iterator = 0;
                    computed.addElement(input);
                }
            }
        }
        counted.addRow(computed);
    }

    public void stding(List<Value> keyed, DataFrame dated) {
        DataFrame computed = counted.emptyWithoutData();
        Value[] input = new Value[computed.cols.size()];
        Value x = null;
        int counter = 0;
        int iterator = 0;
        for (int k = 0; k < dated.cols.size(); k++) {
                if (!dated.classes.get(k).equals(DateTimeValue.class)) {
                    x = creator("0", dated.dataBase.get(dated.cols.get(k)).get(0));
                    for (Value z : dated.dataBase.get(dated.cols.get(k))) {
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
                    Value sum = x.create("0");
                    for (Value z : dated.dataBase.get(dated.cols.get(k))) {
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
                    if (keyed.size() > iterator)
                        input[iterator] = keyed.get(iterator);
                    else
                        input[iterator] = x;
                    iterator++;
                    counter = 0;
                    if (iterator == computed.cols.size()) {
                        iterator = 0;
                        computed.addElement(input);
                    }
                }
        }
        counted.addRow(computed);
    }
}
