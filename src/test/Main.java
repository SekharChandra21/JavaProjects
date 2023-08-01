package test;

import java.util.*;

class InValidAgeException extends Exception{
    public InValidAgeException() {
        super("Invalid Age");
    }

    public InValidAgeException(String errorMsg) {
        super("Invalid Age: " + errorMsg );
    }
}

public class Main {
    public static void main(String[] args) throws InValidAgeException, Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your age : ");
        int age = sc.nextInt();

        try {
            if (age < 18) {
                throw new InValidAgeException("Not allowed");
            }

            else {
                System.out.println("Allowed ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    

        System.out.println("Rest of the code");
    }
}
