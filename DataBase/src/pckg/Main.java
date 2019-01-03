package pckg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException {
        DB base=new DB();
        base.connect();
//        base.insertToTable("groupby.csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
//        base.insertPartToTable("groupby.csv",new Class[] {StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class}, 100);
        DataFrame data=base.selectDataFrame("SELECT id, total FROM DataFrame where id='a';");
        System.out.println(data.toString());
//        System.out.println(base.max());
//        DataFrame.DataMap map=base.groupby("id");
//        System.out.println(map.toString());
    }
}
