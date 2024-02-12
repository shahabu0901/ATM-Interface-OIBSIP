package Oasis.Atm.Interface;

import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
	static void register() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------");
		System.out.println("Enter your Name :");
		ATM.name = sc.nextLine();
		System.out.println("Enter User_Name :");
		String user = sc.nextLine();
		System.out.println("Enter Password :");
		String pass = sc.nextLine();
		System.out.println("Enter your Account Number :");
		ATM.accnumber = sc.nextLine();
		System.out.println("REGISTRATION SUCCESSFULLY!");
		System.out.println("---------------------------");
		ATM.prompt();
		while (true) {
			display(ATM.name);
			int choice = sc.nextInt();
			if (choice == 1) {
				login(user, pass);
				break;
			} else {
				if (choice == 2) {
					System.exit(0);
				} else {
					System.out.println("Bad Value! Enter again!");
					sc.close();
				}
			}
		}
	}

	static void display(String name) {
	}

	static void login(String user, String pass) {
	}

}

class Transaction {
	static void withdraw() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------");
		System.out.println("Enter amount to Withdraw :");
		int Withdrawcash = sc.nextInt();
		if (Withdrawcash <= ATM.balance) {
			ATM.balance = ATM.balance - Withdrawcash;
			ATM.history.add(Integer.toString(Withdrawcash));
			ATM.history.add("Withdraw");
			System.out.println("Amount Rs" + Withdrawcash + "/-Withdraw Successfully");
			System.out.println("---------------------------");
		} else {
			System.out.println("Insufficient balance to Withdraw the Cash");
			System.out.println("---------------------------");
			sc.close();
		}
		ATM.prompt();
	}

	static void deposit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------");
		System.out.print("Enter amount to Deposit :");
		int dcash = sc.nextInt();
		ATM.updatebalance(dcash);
		ATM.history.add(Integer.toString(dcash));
		ATM.history.add("Deposit");
		System.out.println("Amount Rs." + dcash + "/- Deposit Successful!");
		System.out.println("---------------------------");
		ATM.prompt();
		sc.close();
	}

	@SuppressWarnings("unused")
	static void transfer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Receiving Name :");
		String s = sc.nextLine();
		System.out.println("Enter the account Number of the Receiving");
		int num = sc.nextInt();
		System.out.println("Enter the amount to be Transferred :");
		int tcash = sc.nextInt();
		if (tcash <= ATM.balance) {
			ATM.balance = ATM.balance - tcash;
			ATM.history.add(Integer.toString(tcash));
			ATM.history.add("transferred");
			System.out.println("Amount Rs." + tcash + "/- transferred Successfully");
			System.out.println("---------------------------");
		} else {
			System.out.println("Insufficient Balance to Transfer the cash");
			System.out.println("---------------------------");
			sc.close();
		}
	}

}

class Check {
	static void checkbalance() {
		System.out.println("------------------");
		System.out.println("Available Balance : ");
		ATM.showbalance();
		System.out.println("---------------------------");
		ATM.prompt();
	}
}

class History {
	static void transactionhistory() {
		System.out.println("---------------------");
		System.out.println("Transaction History :");
		int k = 0;
		if (ATM.balance > 0) {
			for (int i = 0; i < (ATM.history.size() / 2); i++) {
				for (int j = 0; j < 2; j++) {
					System.out.print(ATM.history.get(k) + " ");
					k++;
				}
				System.out.println("---------------------");
			}
		} else {
			System.out.println("your Account is Empty");
		}
		ATM.prompt();
	}

}

public class ATM {
	public static String name;
	public static int balance = 0;
	public static String accnumber;
	public static ArrayList<String> history = new ArrayList<String>();

	static void updatebalance(int dcash) {
		balance = balance + dcash;
	}

	static void showbalance() {
		System.out.println(balance); 
	}

	public static void homepage() {
		System.out.println("\033[H\033[2J");
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO ATM INTERFACE");
		System.out.println("--------------------------");
		System.out.println("Select Option :");
		System.out.println("1. Register");
		System.out.println("2. Exit");
		System.out.println("Enter choice:");
		int choice = sc.nextInt();
		if (choice == 1) {
			BankAccount.register();
		} else {
			if (choice == 2) {
				System.exit(0);
			} else {
				System.out.println("select a value only from the given options :");
				ATM.homepage();
			}
			sc.close();
		}
	}

	static void prompt() {
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME " + ATM.name + "! TO ATM SYSTEM");
		System.out.println("---------------------");
		System.out.println("Select Option : ");
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Transfer");
		System.out.println("4. Check balance");
		System.out.println("5. Transaction History");
		System.out.println("6. Exit");
		System.out.print("Enter your choice : ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			Transaction.withdraw();
		case 2:
			Transaction.deposit();
		case 3:
			Transaction.transfer();
		case 4:
			Check.checkbalance();
		case 5:
			History.transactionhistory();
		case 6:
			System.exit(0);
		}
		sc.close();
	}

	public static void main(String[] args) {
		ATM.homepage();
	}

}
