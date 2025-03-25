import java.sql.*;
import java.util.*;

public class Hotelreservationsystem {
    static String URL = "jdbc:mysql://localhost:3306/Hotel";
    static String username = "root";
    static String password = "@Sha1462";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection con = DriverManager.getConnection(URL, username, password);
            while (true) {
                System.out.println("1.New Reservation\n" +
                        "2.Check Reservation\n" +
                        "3.Update Details\n" +
                        "4.Get Room Number\n5.Delete Reservation\n6.Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        newreservation(con, sc);
                        break;
                    case 2:
                        checkreservation(con, sc);
                        break;
                    case 3:
                        updatedetails(con, sc);
                        break;
                    case 4:
                        roomnumber(con, sc);
                        break;
                    case 5:
                        deleteid(con,sc);
                        break;
                    case 6:
                        break;
                }
                if (choice == 6) break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }

    }

    public static void newreservation(Connection con, Scanner sc) {
//        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the Name : ");
        String guest_name = sc.next();
        sc.nextLine();
        System.out.println("Enter room Number : ");
        String room_number = sc.nextLine();
        System.out.println("Enter the Contact Number : ");
        String contact_number = sc.nextLine();
        try {
            String query = "insert INTO File(guest_name,room_number,contact_number)" +
                    "values('" + guest_name + "'," + room_number + ",'" + contact_number + "')";
            Statement stmt = con.createStatement();
//            PreparedStatement stmt=con.prepareStatement(query);
//            stmt.setString(1,guest_name);
//            stmt.setString(2,room_number);
//            stmt.setString(3,contact_number);
            int i = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkreservation(Connection con, Scanner sc) {
//        Scanner sc=new Scanner(System.in);
        System.out.println("How do you want to check?\n1.By Reservation id\n2.By Name\n3.By Phone Number");
        int choice = sc.nextInt();
        try {
            Statement stmt = con.createStatement();
            if (choice == 1) {
                System.out.print("Enter Reservation ID :-> ");
                int id = sc.nextInt();
                String query = "select * from File where reservation_id='" + id + "'";
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                        "--------------------+");
                System.out.println("| Reservation ID | Guest Name      | Room Number   | Contact Number       | Reservation ID        |");
                System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                        "--------------------+");
                while (rs.next()) {
                    int ide = rs.getInt("reservation_id");
                    String name = rs.getString("guest_name");
                    int room = rs.getInt("room_number");
                    String contact = rs.getString("contact_number");
                    String time = rs.getTimestamp("reservation_date").toString();
                    //format to display in table
                    System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n", ide, name, room, contact, time);

                }
            } else if (choice == 2) {
                System.out.print("Enter Name :-> ");
                sc.nextLine();
                String n = sc.nextLine();
                String query = "select * from File where guest_name='" + n + "'";
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                        "--------------------+");
                System.out.println("| Reservation ID | Guest Name      | Room Number   | Contact Number       | Reservation ID        |");
                System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                        "--------------------+");
                while (rs.next()) {
                    int ide = rs.getInt("reservation_id");
                    String name = rs.getString("guest_name");
                    int room = rs.getInt("room_number");
                    String contact = rs.getString("contact_number");
                    String time = rs.getTimestamp("reservation_date").toString();
                    //format to display in table
                    System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n", ide, name, room, contact, time);

                }
            } else if (choice == 3) {
                System.out.print("Enter Phone Number :-> ");
                sc.nextLine();
                String n = sc.nextLine();
                String query = "select * from File where contact_number='" + n + "'";
                ResultSet rs = stmt.executeQuery(query);
                System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                        "--------------------+");
                System.out.println("| Reservation ID | Guest Name      | Room Number   | Contact Number       | Reservation ID        |");
                System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                        "--------------------+");
                while (rs.next()) {
                    int ide = rs.getInt("reservation_id");
                    String name = rs.getString("guest_name");
                    int room = rs.getInt("room_number");
                    String contact = rs.getString("contact_number");
                    String time = rs.getTimestamp("reservation_date").toString();
                    //format to display in table
                    System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n", ide, name, room, contact, time);

                }
            } else {
                System.out.println("Wrong input! ");
            }
            System.out.println("+----------------+-----------------+---------------+----------------------+---" +
                    "--------------------+");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatedetails(Connection con, Scanner sc) {
        try {
            Statement stmt = con.createStatement();
            System.out.println("Which detail do you want to update : ");
            System.out.println("1.Guest Name\n2.Room Number\n3.Contact Number");
            int choice = sc.nextInt();
            System.out.println("Enter Reservation ID : ");
            int id = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the New Name : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    String query = "update File set guest_name='" + name + "'where reservation_id=" + id;
                    int r = stmt.executeUpdate(query);
                    System.out.println(r + " rows affected!");
                    break;
                case 3:
                    System.out.println("Enter the New Number : ");
                    sc.nextLine();
                    String number = sc.nextLine();
                    String query1 = "update File set contact_number='" + number + "'where reservation_id=" + id;
                    int m = stmt.executeUpdate(query1);
                    System.out.println(m + " rows affected!");
                    break;
                case 2:
                    System.out.println("Enter the New Room : ");
                    sc.nextLine();
                    int room = sc.nextInt();
                    String query2 = "update File set room_number='" + room + "'where reservation_id=" + id;
                    int r1 = stmt.executeUpdate(query2);
                    System.out.println(r1 + " rows affected!");
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void roomnumber(Connection con, Scanner sc) {
        System.out.println("How do you want to check?\n1.By Reservation id\n2.By Name\n3.By Phone Number");
        int choice = sc.nextInt();
        try {
            Statement stmt = con.createStatement();
            if (choice == 1) {
                System.out.print("Enter Reservation ID :-> ");
                int id = sc.nextInt();
                String query = "select room_number from File where reservation_id='" + id + "'";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    int room = rs.getInt("room_number");
                    System.out.println("Room Number :-> "+room);
                }
            } else if (choice == 2) {
                System.out.print("Enter Name :-> ");
                sc.nextLine();
                String n = sc.nextLine();
                String query = "select room_number from File where guest_name='" + n + "'";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    int room = rs.getInt("room_number");
                    System.out.println("Room Number :-> "+room);
                }
            }
            else if (choice == 3) {
                System.out.print("Enter Phone Number :-> ");
                sc.nextLine();
                String n = sc.nextLine();
                String query = "select room_number from File where contact_number='" + n + "'";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    int room = rs.getInt("room_number");
                    System.out.println("Room Number :-> "+room);
                }
            } else {
                System.out.println("Wrong input! ");
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteid(Connection con,Scanner sc){
        try{
            Statement stmt=con.createStatement();
            System.out.print("Enter Reservation ID : ");
            int id=sc.nextInt();
            String query="DELETE from File where reservation_id='"+ id +"'";
            int i=stmt.executeUpdate(query);
            System.out.println(i+" rows affected! ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
