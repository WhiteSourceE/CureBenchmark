package ex4;

import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FromWebGoat2 {


    void bad(HttpServletRequest req) throws SQLException {

        String data = null;
        Connection dbConnection = null;
        PreparedStatement sqlStatement = null;
        String sql = "insert into users (status) values ('updated') where name='";

        if(System.getProperty("os.name").equals("IOS")){
            data = req.getParameter("MAC");
            sql = sql + data;
        }else{
            data = req.getParameter("Win");
            sql = sql + data;
        }
        sql = sql + "'";
        sqlStatement = dbConnection.prepareStatement(sql);
        sqlStatement.execute();
    }
}
