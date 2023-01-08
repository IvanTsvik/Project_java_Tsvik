package Connect;

import Parameters.District;
import Parameters.Education;

import java.sql.*;
public class Connect {
    public static Statement statmt;
    public static ResultSet resSet;
    private Connection connect() {
        String url = "jdbc:sqlite:С:/home/ivan/sqliteadmin/school.s3db";
        Connection connect = null;
        try {connect = DriverManager.getConnection(url);}
        catch (SQLException e) {System.out.println(e.getMessage());}
        return connect;
    }

    public void insertToDB(int i, Education education, District district) {
        if (i == 0)
        {
            String sql = "INSERT INTO edu (id,school,country,grades,students," +
                    "teachers,calworks,lunch,computer,expenditure," +
                    "income,english,read,math) VALUES(?,?,?,?,?," +
                                                     "?,?,?,?,?," +
                                                     "?,?,?,?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, education.getId());
                pstmt.setString(2, education.getSchool());
                pstmt.setString(3, education.getCountry());
                pstmt.setString(4, education.getGrades());
                pstmt.setString(5, education.getStudents());
                pstmt.setString(6, education.getTeachers());
                pstmt.setString(7, education.getCalworks());
                pstmt.setString(8, education.getLunch());
                pstmt.setString(9, education.getComputer());
                pstmt.setString(10, education.getExpenditure());
                pstmt.setString(11, education.getIncome());
                pstmt.setString(12, education.getEnglish());
                pstmt.setString(13, education.getRead());
                pstmt.setString(14, education.getMath());
                pstmt.executeUpdate();}
            catch (SQLException e) {System.out.println(e.getMessage());
            }
        }
        else{
            String sql = "INSERT INTO country (dir,school) VALUES(?,?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, district.getDir());
                pstmt.setString(2, district.getSchool());
                pstmt.executeUpdate();}
            catch (SQLException e) {System.out.println(e.getMessage());
            }
        }
    }
    public void Task1()throws SQLException {
        String sql = "SELECT country,students FROM edu";
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            statmt = connect.createStatement();
            resSet = statmt.executeQuery("SELECT country,students FROM edu");
            String[] country = new String[]{"Alameda","Butte","Fresno",
                                            "San Joaquin","Kern",
                                            "Sacramento","Merced","Tulare",
                                            "Los Angeles","Imperial"};
            float[] count = new float[10];
            int i = 0;
            while (i < 10) {
                statmt = connect.createStatement();
                resSet = statmt.executeQuery("SELECT country,students FROM edu WHERE country == \"" + country[i] + "\"");
                int num = 0;
                float students = 0.0F;
                while(resSet.next()){
                    students += resSet.getInt("students");
                    num += 1;}
                students /= num;
                count[i] = students;
                System.out.println(country[i] + " " + count[i]);
                i++;}}
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public void Task2() throws SQLException {
        String sql = "SELECT country,expenditure FROM edu";
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            statmt = connect.createStatement();
            resSet = statmt.executeQuery("SELECT country,expenditure FROM edu");
            String[] country = new String[]{"Fresno","Contra Costa","El Dorado","Glenn"};
            float[] count = new float[4];
            int i = 0;
            while (i < 4) {
                statmt = connect.createStatement();
                resSet = statmt.executeQuery("SELECT country,students FROM edu WHERE country == \"" + country[i] + "\"");
                int num = 0;
                float students = 0.0F;
                while(resSet.next()){
                    students += resSet.getInt("students");
                    num += 1;}
                students /= num;
                count[i] = students;
                System.out.println(country[i] + " " + count[i]);
                i++;}}
        catch (SQLException e) {throw new RuntimeException(e);}
    }

    public void Task3() throws SQLException {
        String sql = "SELECT school,students,math FROM edu ORDER BY math DESC";
        try (Connection connect = this.connect();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            statmt = connect.createStatement();
            resSet = statmt.executeQuery("SELECT school,students,math FROM edu ORDER BY math DESC");
            int i = 0;
            int n = 0;
            while(resSet.next()){
                String a = resSet.getString("school");
                int b = resSet.getInt("students");
                double c = resSet.getDouble("math");
                if (b >= 5000 && b <= 7500 && i == 0) {
                    System.out.println(a + "; Students = " + b + "; Math = " + c);
                    i += 1;}
                if (b >= 10000 && b <= 11000 && n == 0) {
                    System.out.println(a + "; Students = " + b + "; Math = " + c);
                    n += 1;}}}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
}

