package HeartGuide;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	   public static void main(String[] args) throws Exception {
		   createDB();
		   createTables();
		   
	   }
	   public static void createTables() {
		   try {
	            Connection con = getConnection();
	            PreparedStatement create1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS user_account(account_id int PRIMARY KEY AUTO_INCREMENT, user_id int, username varchar(25), password varchar(25),FOREIGN KEY (user_id)REFERENCES user_info(user_id))");
	            PreparedStatement create2 = con.prepareStatement("CREATE TABLE IF NOT EXISTS user_record(record_id int PRIMARY KEY AUTO_INCREMENT, account_id int, record_date date, schedule TEXT, systolic_bp int(3), diastolic_bp int(3), pulse_rate int(3), FOREIGN KEY (account_id)REFERENCES user_account(account_id))");
	            PreparedStatement create3 = con.prepareStatement("CREATE TABLE IF NOT EXISTS user_schedule(schedule_id int PRIMARY KEY AUTO_INCREMENT, user_id int, morning_time time, afternoon_time time, time_notify varchar(5), FOREIGN KEY (user_id)REFERENCES user_account(user_id))");
	            PreparedStatement create4 = con.prepareStatement("CREATE TABLE IF NOT EXISTS user_info(user_id int PRIMARY KEY AUTO_INCREMENT, user_first varchar(20), user_middle varchar(20), user_last varchar(20), user_address varchar(75), user_height double(5, 2), user_weight double(5, 2), user_birthdate date)");
	            PreparedStatement create5 = con.prepareStatement("CREATE TABLE IF NOT EXISTS remember_user(cookie_id int PRIMARY KEY AUTO_INCREMENT, account_id int, FOREIGN KEY (account_id)REFERENCES user_account(account_id))");
	            create4.executeUpdate();
	            create1.executeUpdate();
	            create2.executeUpdate();
	            create3.executeUpdate();
	            create5.executeUpdate();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	   }
	   public static void createDB(){
		   String userQuery = "CREATE DATABASE IF NOT EXISTS heart_guide";
		   try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","falculan1234");//change password
	            Statement stmt = con.createStatement();
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            stmt.executeUpdate(userQuery);
	            System.out.println("Success!");
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		   
	   }
	   public static Connection getConnection() throws Exception{
		   try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/heart_guide","root","falculan1234");//change password
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("Success!");
	            return con;
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }return null;
		   
	   }
	   
}
