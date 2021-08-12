/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE89_SQL_Injection__URLConnection_prepareStatement_73b.java
Label Definition File: CWE89_SQL_Injection.label.xml
Template File: sources-sinks-73b.tmpl.java
*/
/*
 * @description
 * CWE: 89 SQL Injection
 * BadSource: URLConnection Read data from a web server with URLConnection
 * GoodSource: A hardcoded string
 * Sinks: prepareStatement
 *    GoodSink: Use prepared statement and execute (properly)
 *    BadSink : data concatenated into SQL statement used in prepareStatement() call, which could result in SQL Injection
 * Flow Variant: 73 Data flow: data passed in a LinkedList from one method to another in different source files in the same package
 *
 * */

package ex2;
import testcasesupport.*;
import java.util.LinkedList;

import javax.servlet.http.*;

import java.sql.*;

import java.util.logging.Level;

public class CWE89_SQL_Injection__URLConnection_prepareStatement_73b
{
    public void badSink(LinkedList<String> dataLinkedList ) throws Throwable
    {

        Connection dbConnection = null;
        PreparedStatement sqlStatement = null;

        try
        {
            /* POTENTIAL FLAW: data concatenated into SQL statement used in prepareStatement() call, which could result in SQL Injection */
            dbConnection = IO.getDBConnection();
            sqlStatement = dbConnection.prepareStatement("insert into users (status) values ('updated') where name='"+dataLinkedList.remove(2)+"'");

            Boolean result = sqlStatement.execute();

            if (result)
            {
                IO.writeLine("Name, " + dataLinkedList.remove(2) + ", updated successfully");
            }
            else
            {
                IO.writeLine("Unable to update records for user: " + dataLinkedList.remove(2));
            }
        }
        catch (SQLException exceptSql)
        {
            IO.logger.log(Level.WARNING, "Error getting database connection", exceptSql);
        }
        finally
        {
            try
            {
                if (sqlStatement != null)
                {
                    sqlStatement.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql);
            }

            try
            {
                if (dbConnection != null)
                {
                    dbConnection.close();
                }
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql);
            }
        }

    }



}
