package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {
    String dbUrl="jdbc:oracle:thin:@44.202.105.211:1521:XE";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void test1(){

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData= new ArrayList<>();


        Map<String,Object> row1= new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");

        System.out.println("row1"+row1);

        Map<String,Object> row2= new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");
        System.out.println("row2"+row2);
        //adding rows to my List
        queryData.add(row1);
        queryData.add(row2);

        //get the steven lastname directly from the list

        System.out.println(queryData.get(0).get("last_name"));

    }
    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT employee_id, first_name, last_name, salary\n" +
                "from employees\n" +
                "where rownum<6;");

        //in order to get column names we need ResultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData= new ArrayList<>();


        Map<String,Object> row1= new HashMap<>();
        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());

        //move to second row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();

        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());
        //BREAK UNTIL 2:20

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }



}
