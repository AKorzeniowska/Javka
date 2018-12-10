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
//        DataFrame data=base.selectDataFrame("SELECT id, dated, total FROM DataFrame where id in ('a','c','f');");
//        System.out.println(data.toString());
        System.out.println(base.max());
//        System.out.println(base.groupby().toString());
    }
}
