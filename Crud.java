
import java.sql.*;
import java.util.Scanner;

public class Crud {

    static void  showUsers(Connection con) throws Exception {

        PreparedStatement ps = con.prepareStatement("select * from users2");
        ResultSet rs = ps.executeQuery();
        System.out.println("----------------------");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " " + rs.getString("Name") + " " + rs.getString("Phone_Number"));
        }
        System.out.println("----------------------");

    }

    static void insertData(Connection con, Scanner sc) throws Exception {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "Insert into users2 (ID,Name,Phone_Number) Values (?,?,?)");
            System.out.println("Enter ID of the  Client : ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Name of the  Client : ");
            String name = sc.nextLine();
            System.out.println("Enter Phone Number of the  Client : ");
            String phone = sc.nextLine();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, phone);

            ps.executeUpdate();
            System.out.println(" -------------------- Your data is Successfully Updated --------------------- ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }

    static void updateDetails(Connection con, Scanner sc) throws Exception {
        PreparedStatement ps = con.prepareStatement("Update users2 set Name = ? where ID = ?");
        System.out.println("Enter the id no of the User which you want to update");
        int id = sc.nextInt();
        sc.nextLine();
        ps.setInt(2, id);
        System.out.println("Enter the Updated name ");
        String updatedname = sc.nextLine();
        ps.setString(1, updatedname);

        int rows = ps.executeUpdate();
        if (rows == 0) {
            System.out.println("-------------- User's id/Name Not Found  ---------------- ");
        } else {
            System.out.println("-------------- Data updated successfully ---------------- ");
        } 
    }

    static void deleteUser( Connection con , Scanner sc) throws Exception {
        PreparedStatement ps = con.prepareStatement("delete from users2 where id = ?");
        System.out.println("Enter the Id of the User which you want to delete from this DataBase ");
        int id = sc.nextInt();
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("------------- User Deleted Successfully -------------");        
    }

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/mydb2";
        Connection con = DriverManager.getConnection(url, "root", "Your_Password");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.printf("Enter 1 for Show Users \nEnter 2 for insert data \nEnter 3 for Update details \nEnter 4 for Delete User \nEnter 5 for Exit \n");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            int choice = sc.nextInt();
            if (choice == 1) {
                showUsers(con);
            } else if (choice == 2) {
                insertData(con,sc);
            } else if (choice == 3) {
                updateDetails(con,sc);
            } else if (choice == 4) {
                deleteUser(con,sc);
            } else if (choice == 5) {
                System.out.println("---------------  BYE-BYE  ---------------");
                break;
            } else {
                System.out.println("------------- !!!  Enter a Valid choice ------------");
            }
        }
        con.close();
        sc.close();
    }
}
