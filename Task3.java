import java.util.Scanner;
class BankAccount 
{
    private double balance;
    public BankAccount(double initialBalance) 
    {
        this.balance = initialBalance;
    }

    public void deposit(double amount) 
    {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public boolean withdraw(double amount) 
    {
        if (amount > balance) 
	    {
            System.out.println("Insufficient balance.");
            return false;
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
        return true;
    }

    public double getBalance() 
    {
        return balance;
    }
}

class ATM 
{
    private BankAccount account;
    public ATM(BankAccount account) 
    {
        this.account = account;
    }
    public void start() 
    {
        Scanner sc = new Scanner(System.in);
        while (true) 
	    {
            System.out.println("\nATM Options:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            switch (ch) 
	        {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
public class Task3
{
    public static void main(String[] args) 
    {
        BankAccount userAccount = new BankAccount(1000);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}