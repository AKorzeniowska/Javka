package pckg;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")

public class DB extends DataFrame{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;

    public DB(){
        super();
        connection = null;
        statement = null;
        resultSet = null;
    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection =
                    DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/akorzeni",
                            "akorzeni","pB1oh6BpBPunRod1");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }

    public void createTable(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String data="CREATE TABLE IF NOT EXISTS DataFrame (" +
                "   id CHAR(1) NOT NULL," +
                "   dated DATE NOT NULL," +
                "   val DOUBLE NOT NULL," +
                "   total DOUBLE NOT NULL);";

        try {
            statement.executeUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataFrame insertToTable(String address, Class<? extends Value>[] typesInput) throws IOException {
        DataFrame output=new DataFrame(address, typesInput);
        connect();
        createTable();

//        FileInputStream fstream = null;
//        BufferedReader br;
//        try {
//            fstream = new FileInputStream(address);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        br = new BufferedReader(new InputStreamReader(fstream));
//
//        String strLine;
//        strLine = br.readLine();
//        String [] colsInput = strLine.split(",");
//
//        for (int i = 0; i < typesInput.length; i++) {
//            classes.add(typesInput[i]);
//            cols.add(colsInput[i]);
//        }

        String data=" LOAD DATA LOCAL INFILE \'" + address +
                "\' REPLACE INTO TABLE DataFrame" +
                " FIELDS TERMINATED BY \',\'" +
                " LINES TERMINATED BY \'\\n\'" +
                " IGNORE 1 LINES";
                try {
                    statement.executeUpdate(data);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        System.out.println("blep");
//        br.close();
        return output;
    }

    public Class classed (String input){
        Class output=null;
        switch (input){
            case "java.lang.String":
                output=StringValue.class;
                break;
            case "java.lang.Double":
                output=DoubleValue.class;
                break;
            case "java.lang.Integer":
                output=IntegerValue.class;
                break;
            case "java.sql.Date":
                output=DateTimeValue.class;
                break;
            default:
                break;
        }
        return output;
    }

    public DataFrame selectDataFrame (String command){
        DataFrame output=null;
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(command);
            metaData=resultSet.getMetaData();

            Class[] clazz=new Class[metaData.getColumnCount()];
            String[] colnames=new String[metaData.getColumnCount()];

            for (int i=0; i<metaData.getColumnCount(); i++) {
                colnames[i] = metaData.getColumnName(i + 1);
                clazz[i]=classed(metaData.getColumnClassName(i+1));
            }

            List <Value []> buff=new ArrayList<>();
            while(resultSet.next()){
                Value[] arr=new Value[metaData.getColumnCount()];
                for (int i=0; i<metaData.getColumnCount(); i++) {
                    arr[i]=creatorFromClass(resultSet.getString(i+1),clazz[i]);
                }
                buff.add(arr);
            }

            output=new DataFrame(colnames, clazz);

            for (Value [] value : buff)
                output.addElement(value);

        }   catch (SQLException ex){ }

        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqlEx) { }
                resultSet = null;
            }
        }
        return output;
    }

    public DataFrame max() {
        return selectDataFrame("SELECT id, MAX(dated), MAX(val), MAX(total) FROM DataFrame group by id;");
    }

    public DataFrame min(){
        return selectDataFrame("SELECT id, MIN(dated), MIN(val), MIN(total) FROM DataFrame group by id;");
    }

    public DataMap groupby(){
        DataMap outputmap= new DataMap();


        DataFrame output=null;
        String command="select id, dated, val, total from DataFrame order by id;";
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(command);
            metaData=resultSet.getMetaData();

            Class[] clazz=new Class[metaData.getColumnCount()];
            String[] colnames=new String[metaData.getColumnCount()];

            for (int i=0; i<metaData.getColumnCount(); i++) {
                colnames[i] = metaData.getColumnName(i + 1);
                clazz[i]=classed(metaData.getColumnClassName(i+1));
            }

            List <Value []> buff=new ArrayList<>();
            while(resultSet.next()){
                Value[] arr=new Value[metaData.getColumnCount()];
                for (int i=0; i<metaData.getColumnCount(); i++) {
                    arr[i]=creatorFromClass(resultSet.getString(i+1),clazz[i]);
                }
                buff.add(arr);
            }

            output=new DataFrame(colnames, clazz);

            Value key=buff.get(0)[0];
            for (Value [] value : buff) {
                if (value[0].eq(key))
                    output.addElement(value);
                else{
                    List<Value> cols=new ArrayList<>();
                    cols.add(key);
                    outputmap.addFrame(cols, output);
                    key=value[0];
                    output=new DataFrame(colnames, clazz);
                    output.addElement(value);
                }
            }
            List<Value> cols=new ArrayList<>();
            cols.add(key);
            outputmap.addFrame(cols, output);

        }   catch (SQLException ex){ }

        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException sqlEx) { }
                resultSet = null;
            }
        }
        return outputmap;
    }
}