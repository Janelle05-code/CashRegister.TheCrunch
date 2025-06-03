import java.util.*;
import java.util.regex.*;
// Janelle E. Ladera
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
    if (!Pattern.matches("^[A-Za-z\\d]{8,20}$", password) || !password.matches(".*[A-Z].*") ||  !password.matches(".*\\d.*")) {
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
        int loginAttempts = 3;

        while (!loggedIn && loginAttempts < 3) {
            System.out.print("Enter your username: ");
            String loginUser = scanner.nextLine();
            System.out.print("Enter your password: ");
            String loginPass = scanner.nextLine();

            for (User u : users) {
                if (u.username.equals(loginUser) && u.password.equals(loginPass)) {
                    System.out.println("Login successful! Welcome back, " + loginUser + "!");
                    loggedIn = true;
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

                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("\nMain Dishes:");
                    for (int i = 0; i < Main.size(); i++) {
                        System.out.println((i + 1) + " - " + Main.get(i) + " - Price: " + PriceMain.get(i));
                    }
                    System.out.print("Select a main dish (1 or 2): ");
                    int mainChoice = scanner.nextInt(); 
                    if (mainChoice < 3 && mainChoice > 0) {
                        System.out.print("How many would you like to order? ");
                        int qty = scanner.nextInt(); 
                        quantity.add(qty);
                        product.add(Main.get(mainChoice - 1));
                        priceList.add((double) PriceMain.get(mainChoice - 1) * qty);
                        System.out.println("You added: " + qty + " x " + Main.get(mainChoice - 1));

                        System.out.println("Choose a flavor:");
                        for (int i = 0; i < Flavors.size(); i++) {
                            System.out.println((i + 1) + " - " + Flavors.get(i));
                        }
                        System.out.print("Select a flavor (1-" + Flavors.size() + "): ");
                        int flavorChoice = scanner.nextInt(); 
                        if (flavorChoice > 0 && flavorChoice <= Flavors.size()) {
                            flavorsSelected.add(Flavors.get(flavorChoice - 1));
                            System.out.println("Flavor selected: " + Flavors.get(flavorChoice - 1));
                        } else {
                            System.out.println("Invalid flavor choice.");
                        }
                    } else {
                        System.out.println("Invalid choice for main dish.");
                    }
                } 
                else if (choice == 2) {
                    System.out.println("\nRice Meals:");
                    for (int i = 0; i < RiceMeal.size(); i++) {
                        System.out.println((i + 1) + " - " + RiceMeal.get(i) + " - Price: " + PriceRiceMeal.get(i));
                    }
                    System.out.print("Select a rice meal (1-4): ");
                    int riceChoice = scanner.nextInt();
                    if (riceChoice < 5 && riceChoice > 0) {
                        System.out.print("How many would you like to order? ");
                        int qty = scanner.nextInt();
                        quantity.add(qty);
                        product.add(RiceMeal.get(riceChoice - 1));
                        priceList.add((double) PriceRiceMeal.get(riceChoice - 1) * qty);
                        System.out.println("You added: " + qty + " x " + RiceMeal.get(riceChoice - 1));

                        System.out.println("Choose a flavor:");
                        for (int i = 0; i < Flavors.size(); i++) {
                            System.out.println((i + 1) + " - " + Flavors.get(i));
                        }
                        System.out.print("Select a flavor (1-" + Flavors.size() + "): ");
                        int flavorChoice = scanner.nextInt(); 
                        if (flavorChoice > 0 && flavorChoice <= Flavors.size()) {
                            flavorsSelected.add(Flavors.get(flavorChoice - 1));
                            System.out.println("Flavor selected: " + Flavors.get(flavorChoice - 1));
                        } else {
                            System.out.println("Invalid flavor choice.");
                        }
                    } else {
                        System.out.println("Invalid choice for rice meal.");
                    }
                } 
                else if (choice == 3) {
                    System.out.println("\nSides:");
                    for (int i = 0; i < Sides.size(); i++) {
                        System.out.println((i + 1) + " - " + Sides.get(i) + " - Price: " + PriceSides.get(i));
                    }
                    System.out.print("Select a side (1-4): ");
                    int sideChoice = scanner.nextInt(); 
                    if (sideChoice < 5 && sideChoice > 0) {
                        System.out.print("How many would you like to order? ");
                        int qty = scanner.nextInt();
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
                            int flavorChoice = scanner.nextInt(); 
                            if (flavorChoice > 0 && flavorChoice <= Flavors.size()) {
                                flavorsSelected.add(Flavors.get(flavorChoice - 1));
                                System.out.println("Flavor selected: " + Flavors.get(flavorChoice - 1));
                            } else {
                                System.out.println("Invalid flavor choice.");
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
                if (confirmOrder == 'y' || confirmOrder == 'Y') {
                    double payment = 0;
                    boolean validPayment = false;

                    while (!validPayment) {
                        System.out.print("Enter Payment Amount: PHP ");
                        payment = scanner.nextDouble();
                        if (payment < totalAmount) {
                            System.out.println("Insufficient payment was provided. Please try again.");
                        } else {
                            validPayment = true; 
                        }
                    }

                    double change = payment - totalAmount; 
                    System.out.println("Payment was successful. Your change is: PHP " + change);
                    System.out.println("Thank you for your order, " + CustomerName + "!");
                    finalizingOrder = false; 
                } else {
                    System.out.print("Would you like to void an item? (y/n): ");
                    char voidItem = scanner.next().charAt(0);
                    if (voidItem == 'y' || voidItem == 'Y') {
                        System.out.print("Enter the item number to void (1-" + product.size() + "): ");
                        int itemToVoid = scanner.nextInt();
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
            if (anotherTransaction != 'y' && anotherTransaction != 'Y') {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ THANK YOU FOR DINING IN THE CRUNCH! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
                continueTransaction = false;
            }
        }
        scanner.close(); 
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