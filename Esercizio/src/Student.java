
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    String surname;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName () { return name; }

    public String getSurname () { return surname; }


    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3307/newdbdatabase";
        String user = "root";
        String password = "root00";

        Connection conn = DriverManager.getConnection(url,user,password);
        Statement statement = conn.createStatement();

        statement.execute("CREATE TABLE school (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(30) NOT NULL, surname VARCHAR(30) NOT NULL, country VARCHAR(30) NOT NULL)");
        statement.execute("INSERT INTO school (name, surname, country) VALUES ('Ludovico', 'Ariosto', 'Italian')," +
                "('Giovanni','Boccaccio', 'Italian'),('Albert', 'Einstein', 'Tedesco'),('Martin','Lutero','Tedesco')");

        statement.execute("CREATE VIEW italian_students AS SELECT name, surname FROM school WHERE country = 'italian'");
        statement.execute("CREATE VIEW german_students AS SELECT name, surname FROM school WHERE country = 'tedesco'");

        List <String> italianStudents = new ArrayList<>();
        List <String> germanStudents = new ArrayList<>();

        ResultSet rs = statement.executeQuery("SELECT * FROM italian_students");
        while (rs.next()){
            italianStudents.add(rs.getString(1) + " " + rs.getString(2));
        }

        ResultSet rs1 = statement.executeQuery("SELECT * FROM german_students");
        while (rs1.next()){
            germanStudents.add(rs1.getString(1) + " " + rs1.getString(2));
        }

        System.out.println(italianStudents);
        System.out.println(germanStudents);

    }
}
