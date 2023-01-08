package Task;

import Connect.Connect;

import java.sql.SQLException;
import java.util.Scanner;

public class Tasks {
    public static void main(String arg[]) throws SQLException {
        Connect connect = new Connect();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задания: ");
        int n = in.nextInt();
        if (n == 1){connect.Task1();}
        else if (n == 2) {connect.Task2();}
        else if (n == 3) {connect.Task3();}
    }
}

