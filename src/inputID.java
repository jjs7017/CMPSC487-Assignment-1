import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Scanner;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class inputID {
   public static void main(String[] args) {
   
   //Oracle Enterprise Manager Database Express URL: https://localhost:5500/em
   
      Connection conn = null;

      //connect to MySql Database
      try {
         String url = "jdbc:mysql://localhost:3306/mydb";
         String username = "js1";
         String password = "1225";

         System.out.println("Connecting database...");

         conn = DriverManager.getConnection(url, username, password);

         System.out.println("Database connected!");

         String enterPrompt = "Please enter your PSU ID number";
         Scanner myObj = new Scanner(System.in);  // Create a Scanner object

         System.out.println(enterPrompt);
         String idNum = myObj.nextLine();  // Read user input

         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
         Calendar cal = Calendar.getInstance();
         Date date = new Date();
         cal.add(Calendar.HOUR_OF_DAY, 1);
         String currDate = formatter.format(date);
         String endDate = formatter.format(cal.getTime());

         try {
            Statement stmt = null;
            stmt = (Statement) conn.createStatement();
            String query1 = "INSERT INTO labLog " +
                    "VALUES ('student','" + idNum + "', '" + currDate + "', '" + endDate + "' )";
            stmt.executeUpdate(query1);
            System.out.println("Recorded swipe successfully.");
         } catch (SQLException e) {
            e.printStackTrace();
         }
      } catch (SQLException ex) {
         // handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }

      //extract

      // SQL example statements
      // createStatement().("sql statement here")
      // using J connect
      // download the package (j/connect) import in the beginning 
      // insert into transactions
      // select * from transactions
      // put the values into a "result" object i.e. resultSet
      // test program by printing to system
   }

}