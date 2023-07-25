package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.Casino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccountManager` stores, manages, and retrieves `ArcadeAccount` objects
 * it is advised that every instruction in this class is logged
 */
public class CasinoAccountManager {
    HashMap<String, CasinoAccount> listOfAccounts = new HashMap<>();

    public HashMap<String, CasinoAccount> getListOfAccounts() {
        return listOfAccounts;
    }

    /**
     * @param accountName     name of account to be returned
     * @param accountPassword password of account to be returned
     * @return `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount getAccount(String accountName, String accountPassword) {
//        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//        String currentClassName = getClass().getName();
//        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
//        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));
        if (listOfAccounts.containsKey(accountName)) {
            if (listOfAccounts.get(accountName).getAccPassword().equals(accountPassword)) {
                return listOfAccounts.get(accountName);
            }
        }
        return null;
//        String errorMessage = "There is no account with this name and password!";
//        throw new RuntimeException(errorMessage);
    }

    /**
     * logs & creates a new `ArcadeAccount`
     *
     * @param accountName     name of account to be created
     * @param accountPassword password of account to be created
     * @return new instance of `ArcadeAccount` with specified `accountName` and `accountPassword`
     */
    public CasinoAccount createAccount(String accountName, String accountPassword) {
//        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//        String currentClassName = getClass().getName();
//        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
//        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));

        return new CasinoAccount(accountName, accountPassword, 1_000);
    }

    /**
     * logs & registers a new `ArcadeAccount` to `this.getArcadeAccountList()`
     *
     * @param casinoAccount the arcadeAccount to be added to `this.getArcadeAccountList()`
     */
    public void registerAccount(CasinoAccount casinoAccount) {
//        String currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
//        String currentClassName = getClass().getName();
//        String errorMessage = "Method with name [ %s ], defined in class with name [ %s ] has  not yet been implemented";
//        throw new RuntimeException(String.format(errorMessage, currentMethodName, currentClassName));
        listOfAccounts.put(casinoAccount.getAccOwner(), casinoAccount);
    }

    public void readAccounts() {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new File("/Users/christine/LocalProjects/Group Projects/GroupCasino/login.txt"));
        } catch (FileNotFoundException e) {
            Casino.errorMessage.println("File not found");
        }

        while (fileIn.hasNext()) {
            String lineIn = fileIn.nextLine();

            String[] input = lineIn.split(",");

            if (input.length == 3) {
                CasinoAccount newAccount = createAccount(input[0], input[1], Integer.valueOf(input[2]));
                registerAccount(newAccount);
            }

        }
    }

    public void saveAccounts() {
        try {
            // Create the file to write to
            PrintWriter fileOut = new PrintWriter("/Users/christine/LocalProjects/Group Projects/GroupCasino/login.txt");
            // Write text just like we would to the console
            for (String i : getListOfAccounts().keySet()) {
                CasinoAccount account = getListOfAccounts().get(i);
                fileOut.println(String.format("%s,%s,%d", account.getAccOwner(), account.getAccPassword(), account.getAccBalance()));
            }

            // Close out file
            fileOut.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public CasinoAccount createAccount(String accountName, String accountPassword, int accBalance) {
        return new CasinoAccount(accountName, accountPassword, accBalance);
    }
}
