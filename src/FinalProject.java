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
                        System.out.println("Your Current Balance = " + balance);
                        System.out.println("*****************************************");
//
                        break;
                    case 3:
                        System.out.println("*****************************************");
                        System.out.println("Enter an amount to deposit: ");
                        System.out.println("*****************************************");
                        int amount = scan.nextInt();
                        deposit(amount);
                        System.out.println("Balance after deposit: " + balance);
                        break;
                    case 4:
                        System.out.println("*****************************************");
                        System.out.println("Enter an amount to withdraw: ");
                        System.out.println("*****************************************");
                        int amount2 = scan.nextInt();
                        if(balance >= amount2) {
                            withdraw(amount2);
                            System.out.println("Balance after withdrawal: " + balance);
                        }else{
                            System.out.println("OOPS!! Low balance, cannot perform withdraw");
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


        private static boolean isValidEntry ( int choice){
            return choice != 1 || choice != 9;
        }

    }

