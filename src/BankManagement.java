import javax.lang.model.type.NullType;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class BankManagement {


    public static boolean createAccount(String name, int passCode) {
        Bank bank = new Bank();
        System.out.println("Entered the loop with " + name + " " + passCode);
        try {
            if (name == "" || passCode == 0) {
                System.out.println("All Field Required!");
                return false;
            }

            File myObj = new File("D:/MADT/Java/finalProject.csv");
//            if (myObj.exists()) {
//                myObj.delete();
//                System.out.println("File deleted successfully. Now creating new file");
//                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter("D:/MADT/Java/finalProject.csv", true);
            PrintWriter out = new PrintWriter(myWriter);
            out.print(name);
            out.print(",");
            out.println(passCode);
//                BufferedWriter br = new BufferedWriter(myWriter);
//                br.write(System.getProperty("line.separator") + name + System.getProperty("line.separator") + passCode);
//                myWriter.write(name + "" + System.getProperty("line.separator") + "Password" + passCode);
                out.close();
                System.out.println("File wrote successfully");
            System.out.println("------------------------------------------------------");
            System.out.println("Welcome " + name + " account successfully created");
            System.out.println("------------------------------------------------------");

                bank.showSubMenu();


        } catch (Exception e) {

        }
        return true;
    }

    public static boolean loginAccount(String name, int passCode) throws IOException {
        Bank bank = new Bank();
        try {
            if (name == "" || passCode == 0) {
                System.out.println("All Field Required!");
                return false;
            }
            String line = null;
            BufferedReader myWriter = new BufferedReader(new FileReader("D:/MADT/Java/finalProject.csv"));
//            PrintWriter pw = new PrintWriter(myWriter);
            // Read file
            File myObj = new File("D:/MADT/Java/finalProject.csv");
            while ((line = myWriter.readLine()) != null) {
                final String[] userDetails = line.split(",");
                try {
                    final String nameOfUser = userDetails[0];
                    final String passOfUser = userDetails[1];
                    if(Objects.equals(nameOfUser, name) && Integer.parseInt(passOfUser) == passCode) {
                        System.out.println("Username and password matched " + nameOfUser + " " + passOfUser);
                        bank.showSubMenu();
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect number of values Provided. \nEXITING.");

                }
            }
            System.out.println("Wrong username or password");
        }catch (Exception e) {

        }
        return true;
    }
}
