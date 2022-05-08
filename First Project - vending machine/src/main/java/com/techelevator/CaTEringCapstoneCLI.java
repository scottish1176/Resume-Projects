package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CaTEringCapstoneCLI {

    private Map<String, Product> inventoryMap = new HashMap<>();

    private Menu menu;
    private Scanner inputScanner;
    Funds funds = new Funds();
    private Logger logger = new Logger("Audit.txt");

    public CaTEringCapstoneCLI(Menu menu) {
        this.menu = menu;
        this.inputScanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
        cli.run();
    }

    public void run() {
        this.logger.write("Machine started.");
        //System.out.println("What catering file would you like to use? ");
        //String input = inputScanner.nextLine();
        File file = new File("catering.csv");
        if (!file.exists()) {
            System.out.println("Invalid file");
            System.exit(0);
        }
       // Scanner inputScanner = null;
       try  {
            inputScanner = new Scanner(file);
            while (inputScanner.hasNextLine()) {            //Puts file contents into a Map
                String lineOfFile = inputScanner.nextLine();
                String[] lineArr = lineOfFile.split(",");
                String key = lineArr[0];
                String name = lineArr[1];
                String description = lineArr[2];
                BigDecimal price = new BigDecimal(lineArr[3]);
                Product currentProduct = new Product(name, description, price);
                inventoryMap.put(key, currentProduct);
            }
            boolean displayMenu1 = true;
            while (displayMenu1) {
                System.out.println("Please make a choice: ");
                Menu menu = new Menu();
                menu.displayLevel1();
                Scanner scanner = new Scanner(System.in);
                String menuOneChoice = scanner.nextLine();
                menuOneChoice = menuOneChoice.toUpperCase();
                if (menuOneChoice.equals("D")) {    //Display Catering Items
                     for (Map.Entry<String, Product> display : inventoryMap.entrySet()) {
                       System.out.println(display); }
                }
                if (menuOneChoice.equals("P")) {                // Purchase
                    // displayMenu1 = false;
                    boolean menu2 = true;
                    while (menu2) {
                        menu.displayLevel2();
                        System.out.println("Current Balance: $" + funds.getBalance());
                        String menuTwoChoice = scanner.nextLine();
                        menuTwoChoice = menuTwoChoice.toUpperCase();
                        if (menuTwoChoice.equals("M")) {       //Feed Money

                            boolean status = true;
                            while (status) {

                                System.out.println("How much money would you like to feed? $1 $5 $10 $20 OR, Press M to go to previous menu \nCurrent Money Provided: $" + funds.getBalance());
                                String scannerStr = scanner.nextLine();
                                scannerStr = scannerStr.toUpperCase();
                                if (scannerStr.equals("M")) {
                                    status = false;

                                }
                                if (scannerStr.equals("$1") || scannerStr.equals("1")) {
                                    BigDecimal oneDollar = new BigDecimal("1.00");
                                    funds.addToBalance(oneDollar);
                                    this.logger.write("Money Fed: $" + oneDollar + " $" + funds.getBalance());
                                }

                                if (scannerStr.equals("$5") || scannerStr.equals("5")) {
                                    BigDecimal fiveDollars = new BigDecimal("5.00");
                                    funds.addToBalance(fiveDollars);
                                    this.logger.write("Money Fed: $" + fiveDollars + " $" + funds.getBalance());
                                }

                                if (scannerStr.equals("$10") || scannerStr.equals("10")) {
                                    BigDecimal tenDollars = new BigDecimal("10.00");
                                    funds.addToBalance(tenDollars);
                                    this.logger.write("Money Fed: $" + tenDollars + " $" + funds.getBalance());
                                }

                                if (scannerStr.equals("$20") || scannerStr.equals("20")) {
                                    BigDecimal twentyDollars = new BigDecimal("20.00");
                                    funds.addToBalance(twentyDollars);
                                    this.logger.write("Money Fed: $" + twentyDollars + " $" + funds.getBalance());
                                }
                            }
                        }
                        if (menuTwoChoice.equals("S")) {        //Select Item
                            for (Map.Entry<String, Product> display2 : inventoryMap.entrySet()) {
                                System.out.println(display2);
                            }
                            System.out.println("\nCurrent Balance: " + funds.getBalance() + " Please make an exact (A4 or C3 or D1 ect..) selection: ");
                            String scannerStr = scanner.nextLine();
                            boolean insufficientFundsLoop = true;
                            while (insufficientFundsLoop) {
                                Product product = inventoryMap.get(scannerStr);
                                if (product != null) {          //This is where we test if the input is valid

                                    if (inventoryMap.get(scannerStr).getInventory() < 0) {   //this is where we check the stock of an item
                                        System.out.println("Inventory unavailable");
                                        break;
                                    }
                                    int check = inventoryMap.get(scannerStr).getPrice().compareTo(funds.getBalance()); // Check if there is enough funds
                                    if (check == 1) {
                                        System.out.println("Insufficient Funds, please add money if you wish to purchase this item");  // Make this not print with a valid selection.
                                        break;
                                    }
                                    funds.subtractFromBalance(inventoryMap.get(scannerStr).getPrice()); // This subtracts the price from the balance
                                    inventoryMap.get(scannerStr).subtractInventory();
                                    System.out.println(inventoryMap.get(scannerStr).getName() + " " + inventoryMap.get(scannerStr).getPrice() + " Remaining Funds: " + funds.getBalance());
                                    this.logger.write(inventoryMap.get(scannerStr).getName() + " Balance:" + funds.getBalance());
                                    if (inventoryMap.get(scannerStr).getDescription().equals("Munchy")) {
                                        System.out.println("Munchy, Munchy, so Good!");
                                        break;
                                    }
                                    if (inventoryMap.get(scannerStr).getDescription().equals("Sandwich")) {
                                        System.out.println("Sandwich So Delicious, Yum!");
                                        break;
                                    }
                                    if (inventoryMap.get(scannerStr).getDescription().equals("Drink")) {
                                        System.out.println("Drinky, Drinky, Slurp Slurp!");
                                        break;
                                    }
                                    if (inventoryMap.get(scannerStr).getDescription().equals("Dessert")) {
                                        System.out.println("Sugar, Sugar, so Sweet!");
                                        break;
                                    }
                                } else {
                                    System.out.println("Please try a different selection");
                                    break;
                                }
                            }
                        }
                        if (menuTwoChoice.equals("F")) {             //Finish Transaction
                            BigDecimal changeBeforeCashout = funds.getBalance();
                            BigDecimal oneHundred = new BigDecimal("100");
                            BigDecimal change = funds.getBalance().multiply(oneHundred);
                            int amountInt = change.intValue();
                            int dividedByDollar = amountInt / 100;
                            int dollarResult = amountInt - (dividedByDollar * 100);
                            int dividedByTwentyFive = dollarResult / 25;
                            int result1 = dollarResult - (dividedByTwentyFive * 25);
                            int dividedByTen = result1 / 10;
                            int result2 = result1 - (dividedByTen * 10);
                            int dividedByFive = result2 / 5;
                            System.out.println("Your change is " + "$" + dividedByDollar + ", " + dividedByTwentyFive + " quarters, " + dividedByTen + " dimes, " + dividedByFive + " nickels");
                            funds.subtractFromBalance(funds.getBalance());
                            this.logger.write( "Change Given: " + changeBeforeCashout + " Balance: " + funds.getBalance());
                            menu2 = false;
                        }
                    }
                }
                if (menuOneChoice.equals("E")) {    //Exit
                    System.exit(0);
                }
            }
       } catch (FileNotFoundException e) {
           System.out.println("File not found");
        }
    }
}

