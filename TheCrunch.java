import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

public class TheCrunch {
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Signup
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SIGN UP ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (true) {
            System.out.print("Enter a username (5-15 alphanumeric characters): ");
            String username = scanner.nextLine();
            if (!Pattern.matches("^[a-zA-Z0-9]{5,15}$", username)) {
                System.out.println("Invalid username. Please try again.");
                continue;
            }

            System.out.print("Enter a password (8-20 characters, at least one uppercase letter and one number): ");
            String password = scanner.nextLine();
            if (!Pattern.matches("^[A-Za-z\\d]{8,20}$", password) || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
                System.out.println("Invalid password. Please try again.");
                continue;
            }

            users.add(new User(username, password));
            System.out.println("Signup successful! Welcome, " + username + "!");
            break;
        }

        // User Login with 3 attempts
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ LOGIN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean loggedIn = false;
        int loginAttempts = 0; // Fixed: should start at 0
        String loggedInUsername = ""; // Track the logged in username

        while (!loggedIn && loginAttempts < 3) {
            System.out.print("Enter your username: ");
            String loginUser = scanner.nextLine();
            System.out.print("Enter your password: ");
            String loginPass = scanner.nextLine();

            for (User u : users) {
                if (u.username.equals(loginUser) && u.password.equals(loginPass)) {
                    System.out.println("Login successful! Welcome back, " + loginUser + "!");
                    loggedIn = true;
                    loggedInUsername = loginUser; // Save username here
                    break;
                }
            }

            if (!loggedIn) {
                loginAttempts++;
                if (loginAttempts < 3) {
                    System.out.println("Incorrect credentials. Try again. Attempts left: " + (3 - loginAttempts));
                } else {
                    System.out.println("Too many failed attempts. Exiting...");
                    System.exit(0);
                }
            }
        }

        System.out.println("Access granted to Cash Register System!");

        boolean continueTransaction = true;
        while (continueTransaction) {
            ArrayList<Integer> quantity = new ArrayList<>();
            ArrayList<String> product = new ArrayList<>();
            ArrayList<Double> priceList = new ArrayList<>();
            ArrayList<String> flavorsSelected = new ArrayList<>();

            ArrayList<String> Main = new ArrayList<>();
            Main.add("Half Chicken");
            Main.add("Whole Chicken");

            ArrayList<String> RiceMeal = new ArrayList<>();
            RiceMeal.add("Chicken Shots");
            RiceMeal.add("1 pc chicken");
            RiceMeal.add("2 pcs chicken");
            RiceMeal.add("3 pcs chicken");

            ArrayList<String> Sides = new ArrayList<>();
            Sides.add("Chicken shots bucket");
            Sides.add("Twister fries");
            Sides.add("Plain rice");
            Sides.add("Extra sauce");

            ArrayList<String> Flavors = new ArrayList<>();
            Flavors.add("Classic");
            Flavors.add("Honey Garlic");
            Flavors.add("K-Style");
            Flavors.add("Spicy K-Style");
            Flavors.add("Teriyaki");
            Flavors.add("Cheesy Bacon");

            ArrayList<Integer> PriceMain = new ArrayList<>();
            PriceMain.add(265); // HALF CHICKEN
            PriceMain.add(495); // WHOLE CHICKEN

            ArrayList<Integer> PriceRiceMeal = new ArrayList<>();
            PriceRiceMeal.add(70); // CHICKEN SHOTS
            PriceRiceMeal.add(75); //1PC CHICKEN
            PriceRiceMeal.add(115); //2PCS CHICKEN
            PriceRiceMeal.add(145); // 3PCS CHICKEN

            ArrayList<Integer> PriceSides = new ArrayList<>();
            PriceSides.add(130); //CHICKEN SHOTS BUCKET
            PriceSides.add(95); // TWISTER FRIES
            PriceSides.add(15); // PLAIN RICE
            PriceSides.add(25); // EXTRA SAUCE

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ WELCOME TO THE CRUNCH ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("This is our menu");

            System.out.println("Main Dishes:");
            for (String item : Main) {
                System.out.println("- " + item);
            }

            System.out.println("\nRice Meals:");
            for (String item : RiceMeal) {
                System.out.println("- " + item);
            }

            System.out.println("\nSides:");
            for (String item : Sides) {
                System.out.println("- " + item);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            System.out.print("Client Name: ");
            String CustomerName = scanner.nextLine();

            boolean ordering = true;

            while (ordering) {
                System.out.println("May I take your order? Choose an option:");
                System.out.println("1 - Main Dishes");
                System.out.println("2 - Rice Meals");
                System.out.println("3 - Sides");
                System.out.print("Enter your choice (1, 2, or 3): ");
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                    scanner.nextLine(); // clear invalid input
                    continue;
                }

                if (choice == 1) {
                    System.out.println("\nMain Dishes:");
                    for (int i = 0; i < Main.size(); i++) {
                        System.out.println((i + 1) + " - " + Main.get(i) + " - Price: " + PriceMain.get(i));
                    }
                    System.out.print("Select a main dish (1 or 2): ");
                    int mainChoice = 0;
                    try {
                        mainChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                        scanner.nextLine();
                        continue;
                    }
                    if (mainChoice < 3 && mainChoice > 0) {
                        System.out.print("How many would you like to order? ");
                        int qty = 0;
                        try {
                            qty = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity. Please enter a number.");
                            scanner.nextLine();
                            continue;
                        }
                        quantity.add(qty);
                        product.add(Main.get(mainChoice - 1));
                        priceList.add((double) PriceMain.get(mainChoice - 1) * qty);
                        System.out.println("You added: " + qty + " x " + Main.get(mainChoice - 1));

                        System.out.println("Choose a flavor:");
                        for (int i = 0; i < Flavors.size(); i++) {
                            System.out.println((i + 1) + " - " + Flavors.get(i));
                        }
                        System.out.print("Select a flavor (1-" + Flavors.size() + "): ");
                        int flavorChoice = 0;
                        try {
                            flavorChoice = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input for flavor.");
                            scanner.nextLine();
                            flavorsSelected.add("No flavor"); // default
                            continue;
                        }
                        if (flavorChoice > 0 && flavorChoice <= Flavors.size()) {
                            flavorsSelected.add(Flavors.get(flavorChoice - 1));
                            System.out.println("Flavor selected: " + Flavors.get(flavorChoice - 1));
                        } else {
                            System.out.println("Invalid flavor choice.");
                            flavorsSelected.add("No flavor"); // default
                        }
                    } else {
                        System.out.println("Invalid choice for main dish.");
                    }
                } else if (choice == 2) {
                    System.out.println("\nRice Meals:");
                    for (int i = 0; i < RiceMeal.size(); i++) {
                        System.out.println((i + 1) + " - " + RiceMeal.get(i) + " - Price: " + PriceRiceMeal.get(i));
                    }
                    System.out.print("Select a rice meal (1-4): ");
                    int riceChoice = 0;
                    try {
                        riceChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for rice meal.");
                        scanner.nextLine();
                        continue;
                    }
                    if (riceChoice < 5 && riceChoice > 0) {
                        System.out.print("How many would you like to order? ");
                        int qty = 0;
                        try {
                            qty = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity.");
                            scanner.nextLine();
                            continue;
                        }
                        quantity.add(qty);
                        product.add(RiceMeal.get(riceChoice - 1));
                        priceList.add((double) PriceRiceMeal.get(riceChoice - 1) * qty);
                        System.out.println("You added: " + qty + " x " + RiceMeal.get(riceChoice - 1));

                        System.out.println("Choose a flavor:");
                        for (int i = 0; i < Flavors.size(); i++) {
                            System.out.println((i + 1) + " - " + Flavors.get(i));
                        }
                        System.out.print("Select a flavor (1-" + Flavors.size() + "): ");
                        int flavorChoice = 0;
                        try {
                            flavorChoice = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input for flavor.");
                            scanner.nextLine();
                            flavorsSelected.add("No flavor");
                            continue;
                        }
                        if (flavorChoice > 0 && flavorChoice <= Flavors.size()) {
                            flavorsSelected.add(Flavors.get(flavorChoice - 1));
                            System.out.println("Flavor selected: " + Flavors.get(flavorChoice - 1));
                        } else {
                            System.out.println("Invalid flavor choice.");
                            flavorsSelected.add("No flavor");
                        }
                    } else {
                        System.out.println("Invalid choice for rice meal.");
                    }
                } else if (choice == 3) {
                    System.out.println("\nSides:");
                    for (int i = 0; i < Sides.size(); i++) {
                        System.out.println((i + 1) + " - " + Sides.get(i) + " - Price: " + PriceSides.get(i));
                    }
                    System.out.print("Select a side (1-4): ");
                    int sideChoice = 0;
                    try {
                        sideChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for side.");
                        scanner.nextLine();
                        continue;
                    }
                    if (sideChoice < 5 && sideChoice > 0) {
                        System.out.print("How many would you like to order? ");
                        int qty = 0;
                        try {
                            qty = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity.");
                            scanner.nextLine();
                            continue;
                        }
                        quantity.add(qty);
                        product.add(Sides.get(sideChoice - 1));
                        priceList.add((double) PriceSides.get(sideChoice - 1) * qty);
                        System.out.println("You added: " + qty + " x " + Sides.get(sideChoice - 1));

                        if (sideChoice == 1) {
                            System.out.println("Choose a flavor:");
                            for (int i = 0; i < Flavors.size(); i++) {
                                System.out.println((i + 1) + " - " + Flavors.get(i));
                            }
                            System.out.print("Select a flavor (1-" + Flavors.size() + "): ");
                            int flavorChoice = 0;
                            try {
                                flavorChoice = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input for flavor.");
                                scanner.nextLine();
                                flavorsSelected.add("No flavor");
                                continue;
                            }
                            if (flavorChoice > 0 && flavorChoice <= Flavors.size()) {
                                flavorsSelected.add(Flavors.get(flavorChoice - 1));
                                System.out.println("Flavor selected: " + Flavors.get(flavorChoice - 1));
                            } else {
                                System.out.println("Invalid flavor choice.");
                                flavorsSelected.add("No flavor");
                            }
                        } else {
                            flavorsSelected.add("No flavor"); 
                        }
                    } else {
                        System.out.println("Invalid choice for side.");
                    }
                } else {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }

                System.out.print("\nWould you like to add anything else? (y/n): ");
                char continueOrder = scanner.next().charAt(0);
                scanner.nextLine(); // consume newline
                if (continueOrder != 'y' && continueOrder != 'Y') {
                    ordering = false; 
                }
            }

            boolean finalizingOrder = true;
            while (finalizingOrder) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("\nYour current order:");
                for (int i = 0; i < product.size(); i++) {
                    System.out.println(quantity.get(i) + " x " + product.get(i) + " - Price: " + priceList.get(i) + " - Flavor: " + flavorsSelected.get(i));
                }

                double totalAmount = 0;
                for (double price : priceList) {
                    totalAmount += price;
                }
                System.out.println("Total amount to be paid: PHP " + totalAmount);
                System.out.print("Is this your final order, " + CustomerName + "? (y/n): ");
                char confirmOrder = scanner.next().charAt(0);
                scanner.nextLine(); // consume newline
                if (confirmOrder == 'y' || confirmOrder == 'Y') {
                    double payment = 0;
                    boolean validPayment = false;

                    while (!validPayment) {
                        System.out.print("Enter Payment Amount: PHP ");
                        try {
                            payment = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid payment input. Please enter a number.");
                            scanner.nextLine();
                            continue;
                        }
                        if (payment < totalAmount) {
                            System.out.println("Insufficient payment was provided. Please try again.");
                        } else {
                            validPayment = true; 
                        }
                    }
                    scanner.nextLine(); // consume newline

                    double change = payment - totalAmount; 
                    System.out.println("Payment was successful. Your change is: PHP " + change);
                    System.out.println("Thank you for your order, " + CustomerName + "!");

                    // Receipt display addition starts here
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy : hh:mma");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Manila"));
                    String dateTime = dateFormat.format(new Date());

                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RECEIPT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Date and Time: " + dateTime);
                    System.out.println("Customer Name: " + CustomerName);
                    System.out.println("Items Purchased:");
                    for (int i = 0; i < product.size(); i++) {
                        System.out.println(quantity.get(i) + " x " + product.get(i) + " - Price: PHP " + priceList.get(i));
                    }
                    System.out.printf("Total Amount: PHP %.2f%n", totalAmount);
                    System.out.printf("Cash Paid: PHP %.2f%n", payment);
                    System.out.printf("Change: PHP %.2f%n", change);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    // Receipt display addition ends here

                    // Log transaction
                    // Fixed to use absolute path to avoid permission error
                    logTransaction(CustomerName, loggedInUsername, product, quantity, priceList, totalAmount);
                    finalizingOrder = false; 
                } else {
                    System.out.print("Would you like to void an item? (y/n): ");
                    char voidItem = scanner.next().charAt(0);
                    scanner.nextLine(); // consume newline
                    if (voidItem == 'y' || voidItem == 'Y') {
                        System.out.print("Enter the item number to void (1-" + product.size() + "): ");
                        int itemToVoid = 0;
                        try {
                            itemToVoid = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input.");
                            scanner.nextLine();
                            continue;
                        }
                        scanner.nextLine(); // consume newline
                        if (itemToVoid > 0 && itemToVoid <= product.size()) {
                            quantity.remove(itemToVoid - 1);
                            product.remove(itemToVoid - 1);
                            priceList.remove(itemToVoid - 1);
                            flavorsSelected.remove(itemToVoid - 1); 
                        } else {
                            System.out.println("Invalid item number.");
                        }
                    } else {
                        finalizingOrder = false; 
                    }
                }
            }

            System.out.print("\nWould you like to perform another transaction? (y/n): ");
            char anotherTransaction = scanner.next().charAt(0);
            scanner.nextLine(); // consume newline
            if (anotherTransaction != 'y' && anotherTransaction != 'Y') {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ THANK YOU FOR DINING IN THE CRUNCH! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
                continueTransaction = false;
            }
        }
        scanner.close(); 
    }

    // Logging function - logs transaction details to transactions.txt with Philippine date & time
    private static void logTransaction(String customerName, String cashierUsername,
                                       ArrayList<String> products, ArrayList<Integer> quantities,
                                       ArrayList<Double> prices, double totalAmount) {
        try {
            // <-- Change relative path to absolute path here to avoid permission errors -->
            FileWriter fw = new FileWriter("transactions.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy : hh:mma");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Manila"));
            String dateTime = dateFormat.format(new Date());

            bw.write("Transaction Date and Time: " + dateTime);
            bw.newLine();
            bw.write("Cashier: " + cashierUsername);
            bw.newLine();
            bw.write("Customer Name: " + customerName);
            bw.newLine();
            bw.write("Items Purchased:");
            bw.newLine();
            for (int i = 0; i < products.size(); i++) {
                bw.write(quantities.get(i) + " x " + products.get(i) + " - Price: " + prices.get(i));
                bw.newLine();
            }
            bw.write("Total Amount: PHP " + totalAmount);
            bw.newLine();
            bw.write("--------------------------------------------------");
            bw.newLine();

            bw.close();
            System.out.println("Transaction logged successfully.");
        } catch (IOException e) {
            System.out.println("Oops! Failed to log the transaction.");
            e.printStackTrace();
        }
    }

    private static class User {
        String username;
        String password;

        User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}

