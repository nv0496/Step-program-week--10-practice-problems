// BankAccount Class
class BankAccount {
    private String accountNumber;
    private double balance;
    private int pin;

    public BankAccount(String accountNumber, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void debit(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance. Transaction failed.");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn. Remaining balance: ₹" + balance);
        }
    }
}

// ATM Class
class ATM {
    private BankAccount linkedAccount;

    public ATM(BankAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public void withdraw(int enteredPin, double amount) {
        System.out.println("ATM: Processing withdrawal of ₹" + amount);
        if (linkedAccount.validatePin(enteredPin)) {
            linkedAccount.debit(amount);
            System.out.println("ATM: Please collect your cash.");
        } else {
            System.out.println("ATM: Invalid PIN. Transaction failed.");
        }
    }
}

// Customer Class
class Customer {
    private String name;
    private ATM atm;

    public Customer(String name, ATM atm) {
        this.name = name;
        this.atm = atm;
    }

    public void performWithdrawal(int pin, double amount) {
        System.out.println(name + " is requesting withdrawal of ₹" + amount + "...");
        atm.withdraw(pin, amount);
        System.out.println(name + " received transaction confirmation.\n");
    }
}

// Demo
public class ATMDemo {
    public static void main(String[] args) {
        // Step 1 - Create BankAccount
        BankAccount account = new BankAccount("1234567890", 50000, 1234);

        // Step 2 - Create ATM linked to BankAccount
        ATM atm = new ATM(account);

        // Step 3 - Create Customer associated with ATM
        Customer customer = new Customer("Ravi", atm);

        // Step 4 - Perform withdrawal with correct PIN
        customer.performWithdrawal(1234, 10000);

        // Step 5 - Perform withdrawal with incorrect PIN
        customer.performWithdrawal(1111, 5000);
    }
}