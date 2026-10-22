package test;
import java.sql.*;
/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 11, 2003
 * Time: 9:56:02 PM
 * To change this template use Options | File Templates.
 */
public class TestJDBC
{
   public static void main( String args[] )
 {
  try
     {
      // Load the database driver
      Class.forName( "com.mysql.jdbc.Driver" ) ;

      // Get a connection to the database
      Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost/proto?user=vincent&password=vincent" ) ;

      // Print all warnings
      for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
         {
          System.out.println( "SQL Warning:" ) ;
          System.out.println( "State  : " + warn.getSQLState()  ) ;
          System.out.println( "Message: " + warn.getMessage()   ) ;
          System.out.println( "ErrorCode  : " + warn.getErrorCode() ) ;
         }

      // Get a statement from the connection
      Statement stmt = conn.createStatement() ;

      // Execute the query
      ResultSet rs = stmt.executeQuery( "SELECT * FROM account" ) ;

      // Loop through the result set
      while( rs.next() )
         System.out.println( rs.getString(1) ) ;

      // Close the result set, statement and the connection
      rs.close() ;
      stmt.close() ;
      conn.close() ;
     }
  catch( SQLException se )
     {
      System.out.println( "SQL Exception:" ) ;

      // Loop through the SQL Exceptions
      while( se != null )
         {
          System.out.println( "State  : " + se.getSQLState()  ) ;
          System.out.println( "Message: " + se.getMessage()   ) ;
          System.out.println( "ErrorCode  : " + se.getErrorCode() ) ;

          se = se.getNextException() ;
         }
     }
  catch( Exception e )
     {
      System.out.println( e ) ;
     }
 }

}
