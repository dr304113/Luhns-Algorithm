import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Boolean isValidInput;
        String userInput = "";
        Boolean goAgain = false;
        do {

            do {
                isValidInput = true;
                //Getting input from user
                System.out.println("Please enter your card number to check validity: ");
                userInput = sc.nextLine();

                //Validating input to make sure only numbers are present, if not it'll throw NumberFormatException,
                //we catch the exception, print an error, then restart method.
                try {
                    Long.parseLong(userInput);
                } catch (NumberFormatException ex) {
                    System.out.println("Card number must contain only numeric digits");
                    System.out.println("Press any key to try again....");
                    sc.nextLine();
                    isValidInput = false;
                }
            } while (!isValidInput);
            if (validateCreditCard(userInput)) {
                System.out.println("Valid card number!");
                System.out.println("Want to enter another card? (Y/N)");
                userInput = sc.nextLine();
            } else {
                System.out.println("INVALID card number!");
                System.out.println("Would you like to re-enter the card number? (Y/N)");
                userInput = sc.nextLine();
            }
            if (!userInput.equalsIgnoreCase("y")) {
                System.out.println("Goodbye..");
            } else {
                goAgain = true;
            }
        } while (goAgain);

    }

    private static boolean validateCreditCard(String number) {
        boolean isValid = false;
        int sum = 0;
        List<Integer> productList = new ArrayList<>();

        //Converting String to Integer
        // int stringAsNum = Integer.parseInt(number);

        //Splitting number into individual characters
        String[] numArray = number.split("");


        //looping through every other number starting with the second to last digit
        for (int i = numArray.length - 2; i >= 0; i -= 2) {
            //Multiplying each num by 2
            int num = Integer.parseInt(numArray[i]) * 2;
            //Checking for double-digit product. If product of the above line has 2 digits,
            //then we split num and add each digit separately to a list to prep for next steps
            if (num >= 10) {
                String[] tempNum = valueOf(num).split("");
                productList.add(Integer.parseInt(tempNum[0]));
                productList.add(Integer.parseInt(tempNum[1]));
            } else {
                //If product is only 1 digit then we just simply add it our list
                productList.add(num);
            }
        }
        //Adding all numbers together, pulling them out of the list one by one
        //Storing list total in local 'sum' variable (initialized in the beginning of this method)
        for (int product : productList) {
            sum += product;
        }
        //Looping through remaining numbers in our original numArray
        for (int i = numArray.length - 1; i >= 0; i -= 2) {
            sum += Integer.parseInt(numArray[i]);
        }
        //Finally, we check if sum modulo 10 is 0
        if (sum % 10 == 0) {
            isValid = true;
        }
        return isValid;
    }
}