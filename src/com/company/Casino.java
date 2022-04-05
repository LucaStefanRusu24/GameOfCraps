package com.company;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Casino {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        //initialization of variables
        int won = 0, lost = 0, round = 0, roll, pointValue, menuChoice;
        float budget = 0, amountBet = 0;
        Die dice1 = new Die(6);
        Die dice2 = new Die(6);
        int[] results = new int[13];
        boolean done = false;
        String choice;

        //Main game

        System.out.println("Welcome to the Game of Craps!");
        System.out.println("Here are the rules of the game:" + "\n" +
                "The game of craps consists of one player who rolls two dice." + "\n" +
                "The sum of the value shown by the two dice is the value of the roll of the player for each round." + "\n" +
                "A round consists of the player rolling the dice. If on the first roll, " + "\n" +
                "the player rolls a 7 or an 11, the player wins and the round ends." + "\n" +
                "On the other hand, if on the first roll the player rolls a 2, 3 or 12, " + "\n" +
                "then the player loses and the casino wins and the round ends. " + "\n" +
                "If the player rolls any other value, the round continues until the player rolls the " + "\n" +
                "same amount he rolled initially, in which case he wins, or if he rolls a 7 or an 11, in which case he loses\n");


        System.out.println("\nChoose one option from the menu below:");
        System.out.println("1 - PLAY THE GAME\n2 - SIMULATE 100 TRIALS");

        menuChoice = validIntegerInput();

        switch(menuChoice){
            case 1 :
                System.out.print("\nEnter the amount you want to start with and then press enter to start the game: ");
                budget = validFloatInput();

                while(!done){
                    round++; //Move to next round

                    //Betting
                    System.out.println("Round " + round + ":\n" + "Enter the amount you would like to bet: ");
                    amountBet = validFloatInput();
                    while(amountBet > budget || amountBet <= 0){
                        System.out.println("The amount you bet is more than the amount you have left or is too small, bet a more appropriate amount: ");
                        amountBet = validFloatInput();
                    }
                    budget -= amountBet;

                    System.out.println("Press any key to roll the dice");
                    choice = scan.nextLine();

                    //Rolling the dice
                    roll = rollTheDice(dice1, dice2);
                    System.out.println("You rolled a " + roll);
                    results[roll]++;

                    //Win/loss
                    if(roll == 7 || roll == 11){
                        won++;
                        budget += amountBet * 2;
                        System.out.println("You won, congratulations!\nBudget: " + budget + "\nW-L: " + won + "-" + lost);
                    }
                    else if(roll == 2 || roll == 3 || roll == 12){
                        lost++;
                        System.out.println("You lost, try again!\nBudget: " + budget + "\nW-L: " + won + "-" + lost);
                    }
                    else{
                        System.out.println("You rolled a " + roll + ". This is now your point value. Continue rolling to win!");
                        pointValue = roll;
                        roll = 0;
                        while(roll != pointValue && roll != 7){
                            System.out.println("Press any key to roll the dice");
                            choice = scan.nextLine();

                            roll = rollTheDice(dice1, dice2);
                            results[roll]++;
                            System.out.println("You rolled a " + roll);

                            if(roll == pointValue){
                                won++;
                                budget += amountBet * 2 ;
                                System.out.println("You won, congratulations!\nBudget: " + budget + "\nW-L: " + won + "-" + lost);
                            }
                            else if(roll == 7){
                                lost++;
                                System.out.println("You lost, try again!\nBudget: " + budget + "\nW-L: " + won + "-" + lost);
                            }
                        }
                    }

                    System.out.println("The round has ended. Would you like to continue playing? (Y/N)");
                    choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("N")){
                        done = true;
                        System.out.println("Budget: " + budget + "\nW-L: " + won + "-" + lost +
                                "\nWould you like to see a table of your results? (Y/N)");
                        choice = scan.nextLine();
                        if(choice.equalsIgnoreCase("Y")){
                            printTable(results);
                        }
                    }
                }
                break;


            case 2 :
                System.out.println("Simulating 100 games...");
                Thread.sleep(3000);
                for(int i = 0 ; i < 100 ; i++){
                    roll = rollTheDice(dice1, dice2);
                    results[roll]++;
                }
                System.out.println("The simulation is done! Here are your results: ");
                printTable(results);
                break;

        }
    }

    static private float validFloatInput() {      // inputs a valid floating point number
        float result = 0;
        boolean correct = false;
        while (!correct) {         // validation loop
            try
            {
                result = Float.parseFloat(scan.nextLine());
                correct = true;
            }
            catch (Exception ex)
            {
                System.out.print("Invalid number, try again ");
            }
        }
        return result;
    }

    static private int validIntegerInput() {      // inputs a valid floating point number
        int result = 0;
        boolean correct = false;
        while (!correct) {         // validation loop
            try
            {
                result = Integer.parseInt(scan.nextLine());
                correct = true;
            }
            catch (Exception ex)
            {
                System.out.print("Invalid number, try again ");
            }
        }
        return result;
    }

    static private int rollTheDice(Die dice1, Die dice2){
        int roll;
        dice1.roll();
        dice2.roll();
        roll = dice1.getFaceValue() + dice2.getFaceValue();
        return roll;
    }

    static private void printTable(int results[]){
        System.out.println("+------------------------+");
        System.out.println("| ROLL | NUMBER OF TIMES |");
        for(int i = 2 ; i <= 12 ; i++){
            if(i < 10){
                System.out.println("|  " + i + "   |        " + results[i] + "        |" );
            }
            else{
                System.out.println("|  " + i + "  |        " + results[i] + "        |" );
            }
        }
        System.out.println("+------------------------+");
    }
}



