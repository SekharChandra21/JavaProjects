package bank_management;

import java.io.*;
import java.util.*;

public class Bank implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    static ArrayList<Bank> accounts = new ArrayList<>();
    static File file = new File("data.txt");
    static ObjectOutputStream out;
    static ObjectInputStream in;
    String accountNumber;
    String holderName;
    static Bank b;
    long balance;

    public Bank(String accountNumber, String holderName, long balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String toString() {
        return accountNumber + "  " + holderName + "  " + balance;
    }

    public static void loadData() throws Exception {
        if(file.isFile()) {
            in = new ObjectInputStream(new FileInputStream(file));
            accounts = (ArrayList<Bank>) in.readObject();
            in.close();
        }
    }

    public static void writeData() throws Exception{
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(accounts);
        out.close();
    }

    public static void main(String[] args) throws Exception{
        

        accounts.add(new Bank("123456789", "Chandra",6000));
        accounts.add(new Bank("987654321", "Lokesh", 9000));
        accounts.add(new Bank("147258369", "Mahesh", 10000));
        accounts.add(new Bank("369258147", "Sekhar", 1500));

        System.out.println(accounts);

        writeData();

        loadData();

        System.out.println(isValidUser("123456789", "user"));

        System.out.println(accounts);
    }

    public static Bank isValidUser(String accountNumber, String password) throws Exception {
        loadData();
        ListIterator<Bank> iterator = accounts.listIterator();
        
        while (iterator.hasNext()) {
            b = iterator.next();
            
            if(b.accountNumber.equals(accountNumber) && b.holderName.substring(0, 4).equals(password)) {
                return b;
            }
        }

        return null;
    }

    public static Bank getAccountDetails(String accountNumber) throws Exception {
        loadData();
        ListIterator<Bank> iterator = accounts.listIterator();
        
        while (iterator.hasNext()) {
            b = iterator.next();
            if(b.accountNumber.equals(accountNumber)) {
                return b;
            }
        }

        return null;
    }

    public static boolean withdrawAmount(String accountNumber, long amount) throws Exception{
        loadData();
        b = getAccountDetails(accountNumber);
        long balance = b.balance;

        if((balance < 0) || (amount > balance)) {
            System.out.println("Transition Failed due to less balance");
            return false;
        }

        else {
            ListIterator<Bank> iterator = accounts.listIterator();

            while (iterator.hasNext()) {
                b = iterator.next();
                if(b.accountNumber.equals(accountNumber)) {
                    b.balance -= amount;
                    iterator.set(b);
                }
            }
            System.out.println("Amount withdraw ed successfully");
            writeData();
            return true;
        }
    }

    public static void depositAmount(String accountNumber, long amount) throws Exception{
        loadData();
        ListIterator<Bank> iterator = accounts.listIterator();

        while (iterator.hasNext()) {
            b = iterator.next();
            if(b.accountNumber.equals(accountNumber)) {
                b.balance += amount;
                iterator.set(b);
            }
        }
        writeData();
        System.out.println("amount added successfully.....!!!");
    }
}
