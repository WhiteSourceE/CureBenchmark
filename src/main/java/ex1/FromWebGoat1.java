package ex1;

import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FromWebGoat1 {


    void bad(HttpServletRequest req) throws SQLException {

        String data = null;
        Connection dbConnection = null;
        PreparedStatement sqlStatement = null;
        try {
            data = req.getParameter("one");
            dbConnection = IO.getDBConnection();
        }catch (Exception e){
            data = "dfgdfg";
        }


        sqlStatement = dbConnection.prepareStatement("insert into users (status) values ('updated') where name='"+data+"'");

          sqlStatement.execute();
    }
}
