package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
    public void test1(){
        //create connection
        DBUtils.createConnection();

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from departments");
        for (Map<String, Object> Map : queryResultMap) {
            System.out.println(Map.toString());

        }

        //close connection
        DBUtils.destroy();
    }
    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();

        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name,last_name,salary,job_id from employees\n" +
                "where employee_id = 100");

        System.out.println(rowMap.toString());


        //close connection
        DBUtils.destroy();
    }

}
