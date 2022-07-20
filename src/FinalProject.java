import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class FinalProject {


    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.showMenu();
    }
}
     class Bank {
        String accno;
        String name;
        int acc_type;
        long balance;
        int pass_code;

        boolean found = false;
        public static final Scanner scan = new Scanner(System.in);
        void showMenu() {
            while(true) {
                System.out.println(
                        "\n ->||    Welcome to Scotia Bank    ||<- \n");
                System.out.println("1)Create Account");
                System.out.println("2)Login Account");

try {
    System.out.println("\n    Enter input:");
    int choice = scan.nextInt();
    switch(choice) {
        case 1:
            System.out.println("Enter Unique Username:");
            String username = scan.next();
            System.out.println("Enter New Password");
            int password = scan.nextInt();

            if(BankManagement.createAccount(username, password)) {
                System.out.println("MSG: Account Created Successfully");
            } else {
                System.out.println("ERR: Account Creation Failed!\n");
            }
            break;

        case 2:

            try{
                System.out.println("Enter UserName:");
                String username1 = scan.next();
                System.out.println("Enter Password:");
                int password1 = scan.nextInt();

                BankManagement.loginAccount(username1, password1);
                break;
            } catch (Exception e) {
                System.out.println(" ERR: Enter Valid Data:: Login Failed\n");
            }
    }
} catch (Exception e) {
    System.out.println("ERR : Enter Valid Data::Insertion Failed!\n");
}

            }
        }

        void showSubMenu() {
            while(true) {
                System.out.println("\nBanking Menu: ");
                System.out.println("1. Enter User details as per account type");
                System.out.println("2. Display Current Balance");
                System.out.println("3. Deposit Money");
                System.out.println("4. Withdraw Money");
                System.out.println("5. Transfer between accounts within bank");
                System.out.println("6. Saving to the file");
                System.out.println("7. Pay Utility Bills");
                System.out.println("8. Exit");

                System.out.println("\nEnter your choice: ");
                final int choice = scan.nextInt();
//                FinalProject bank = new FinalProject();

                switch (choice) {

                    case 1:
                        accountCreation();
//                        BankManagement.createAccount(name, pass_code);
                        break;
                    case 2:
                        System.out.println("*****************************************");
                        showAccount();
//                        System.out.println("Your Current Balance = " + balance);
                        System.out.println("*****************************************");
//
                        break;
                    case 3:
                        System.out.println("Enter the Account Number where to deposit");
                        String acn = scan.next();
                        // boolean found = false;
                        found = search(acn);
                        if (found){
                            System.out.println("Enter an amount to deposit: ");
                            System.out.println("*****************************************");
                            int amount = scan.nextInt();
                            deposit(amount);
                            System.out.println("Balance after deposit: " + balance);
                            break;
                        }
                        else{
                            System.out.println("Account does not exist");
                        }
                        break;
                    case 4:
                        System.out.println("Enter the Account Number where to withdraw");
                        acn = scan.next();
                        found = search(acn);
                        if (found){
                            System.out.println("*****************************************");
                            System.out.println("Enter an amount to withdraw: ");
                            System.out.println("*****************************************");

                            int amount = scan.nextInt();
                            withdraw(amount);
                            System.out.println("Balance after withdrawal: " + balance);
                            break;
                        }
                        else{
                            System.out.println("Account does not exist");
                        }
                        break;

                    case 5:
                        try{
                            transferMoney();
                        } catch (Exception e) {
                            System.out.println("Error");
                        }

                        break;
                    case 6:
                        try{
                            if(name != null && pass_code != 0 && accno != null) {
                                System.out.println("*****************************************");
                                System.out.println("Inserting all details in file" );
                                System.out.println("*****************************************");

                                File myObj = new File("src/finalProject.csv");
                                System.out.println("Inserting details " + myObj.getName());
                                FileWriter myWriter = new FileWriter("src/finalProject.csv", true);
                                PrintWriter out = new PrintWriter(myWriter);
                                out.print(name);
                                out.print(",");
                                out.print(pass_code);
                                out.print(",");
                                out.print(acc_type);
                                out.print(",");
                                out.print(accno);
                                out.print(",");
                                out.println(balance);

                                out.close();
                            } else {
                                System.out.println("The account details are not available");
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        break;
                    case 7:
                        System.out.println("*****************************************");
                        System.out.println("7. Pay Utility Bills (Credit Card) ");
                        System.out.println("*****************************************");
                        System.out.println("\n Please enter amount to pay ");
                        int amount3 = scan.nextInt();
                        if(balance >= amount3) {
                            bills(amount3);
                            System.out.println("Balance after Paying Bills: " + balance);
                        }else{
                            System.out.println("OOPS!! Low balance, cannot perform Payment");
                        }
                        break;

                    case 8:
                        System.out.println("*********Exit************");
                        System.out.println("Thank You for using our services ");
                        System.out.println("*****************************************");

                        System.exit(0);
                        break;

                    default:
                        if (isValidEntry(choice)) {
                            System.out.println("\nInvalid Entry. Please enter between 1-9.");
                        }
                }

            }
        }

        public void accountCreation() {
            System.out.print("Enter Account No: ");
            accno = scan.next();

            boolean flag = true;
            while (flag) {
                System.out.print("Enter Account Type: \n 1) Chequing Account \n 2) Saving Account");
                System.out.println();
                acc_type = scan.nextInt();
                if (acc_type == 1 || acc_type == 2) {
                    flag = false;
                } else {
                    System.out.println("Please enter valid value either 1 for Chequing Account or 2 for Saving Account");
                }
            }
            System.out.print("Enter Account Holder Name: ");
            name = scan.next();
            name = name;

            System.out.println("Enter password");
            pass_code = scan.nextInt();
            System.out.println("-----------------------------------------------------");
            System.out.println("Hello " + name + " Account created successfully");
            System.out.println("-----------------------------------------------------");

        }
//
//        public void balanceReturn(long balance) {
//            System.out.println("balance only" + balance);
//
//        }
//
//        public void showAccount() {
//            System.out.println("Name of account holder: " + name);
//            System.out.println("Account no.: " + accno);
//            System.out.println("Account type: " + acc_type);
//            System.out.println("Balance: " + balance);
//        }
//
//        public void currentBalance(long newBal) {
//            System.out.println("Your balance is " + newBal);
//        }


        //Account deposit and withdraw

        void deposit(int amount) {
            if (amount != 0) {
                balance = balance + amount;
            }
        }

        void withdraw(int amount) {
            if (amount != 0) {
                balance = balance - amount;

            }
        }

         void bills(int amount) {
             if (amount != 0) {
                 balance = balance - amount;

             }
         }

          boolean search(String acn){
             if(accno.equals(acn)){
                 showAccount();
                 return (true);
             }
             else {
                 return (false);
             }
         }

         public void showAccount(){
             System.out.println("Account Number = "+accno);
             System.out.print("Note: 1) Chequing Account 2) Saving Account");
             System.out.println("\n Account Type of Account " +accno+ " = " +acc_type);
             System.out.println("Name of Account Holder = "+name);
             System.out.println("Balance in Account Number " +accno+ " = "+balance);
         }

         public void transferMoney() throws IOException {
             String line = null;
             String oldContent = "";
             FileWriter writer = null;
             File fileToBeModified = new File("src/finalProject.csv");
             BufferedReader myWriter = new BufferedReader(new FileReader("src/finalProject.csv"));

             System.out.println("Please enter receiver's account");
             String receiverAcc = scan.next();
             System.out.println("Enter amount you want to deposit");
             int amount = scan.nextInt();
             while(amount > balance) {
                 System.out.println("Enter correct amount. It seems you don't have enough balance");
                 amount = scan.nextInt();
             }
             while ((line = myWriter.readLine()) != null) {
                 final String[] userDetails = line.split(",");
                 try {
                     final String accNumOfUser = userDetails[3];
                     final String balanceOfUser = userDetails[4];
                     final String nameOfUser = userDetails[0];
                     final String passwordOfUser = userDetails[1];
                     final String accountType = userDetails[2];

                     int balanceInteger = Integer.parseInt(balanceOfUser);
                     System.out.println(accNumOfUser);
                     if(Objects.equals(receiverAcc, accNumOfUser)) {
                         System.out.println("The matched account number is " + accNumOfUser);
                         System.out.println(accNumOfUser + " is having the balance " + balanceOfUser);
                         int newBalance = balanceInteger + amount;
                         System.out.println(nameOfUser + " " + passwordOfUser + " " + accountType + " " + accNumOfUser + " " + newBalance );
                         System.out.println(newBalance);
                         FileWriter newWriter = new FileWriter("src/finalProject.csv", true);
                         PrintWriter out = new PrintWriter(newWriter);
                         out.print(nameOfUser);
                         out.print(",");
                         out.print(passwordOfUser);
                         out.print(",");
                         out.print(accountType);
                         out.print(",");
                         out.print(accNumOfUser);
                         out.print(",");
                         out.println(newBalance);
out.close();
balance = balance - amount;
//                         System.out.println(newBalance);
//
//
//                         String newContent = oldContent.replaceAll("891", "1000");
//
//                         //Rewriting the input text file with newContent
//
//                         writer = new FileWriter(fileToBeModified);
//
//                         writer.write(newContent);

//                         Path path = Paths.get("src/finalProject.csv");
//                         Charset charset = StandardCharsets.UTF_8;
//
//                         String content = new String(Files.readString(path), charset);
//                         content = content.replaceAll("0", "1000");
//                         Files.write(path, content.getBytes(charset));



                         break;
                     } else {
                         System.out.println("The entered account is not available");
                     }
                 } catch (Exception e) {
                     System.out.println("Incorrect number of values Provided. \nEXITING.");

                 }
             }
             System.out.println("Money got transfer successfully");
         }


        private static boolean isValidEntry ( int choice){
            return choice != 1 || choice != 9;
        }

    }

