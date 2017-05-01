package App;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {

        DBController dbc = new DBController();
        Scanner scan = new Scanner(System.in);

        System.out.println("QUERIES");
        System.out.println("Get All Users = a");
        System.out.println("Get Most Links = b");
        System.out.println("Get Most Mentioned = c");
        System.out.println("Get Most Active = d");
        System.out.println("Get Most Grumpy = e");
        System.out.println("Get Most Happy = f");
        System.out.println("Close application = close");

        while (scan.hasNext()) {
            String str = scan.nextLine();

            if (str.equals("a")) {
                System.out.println("All Users count");
                dbc.howManyUsers();
            } else if (str.equals("b")) {
                System.out.println("Most linked users");
                dbc.mostLinkedUsers(5);
            } else if(str.equals("c")){
                System.out.println("Most mentioned users");
                System.out.println("Not implemented");
            } else if (str.equals("d")) {
                System.out.println("Most active users");
                dbc.mostActiveUsers(10);
            } else if (str.equals("e")) {
                System.out.println("Most Grumpy negative tweets (keyword: fuck)");
                dbc.mostGrumpyUsers(5);
            } else if (str.equals("f")) {
                System.out.println("Most Happy positive tweets (keyword: love)");
                dbc.mostHappyUsers(5);
            } 
            else if (str.equals("close")) {
                System.exit(0);
            }
        }
    }
}
