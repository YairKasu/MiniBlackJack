package com.company;
/*
********** Mini BlackJack game play against the computer and see how much you can
           be close to the number 21 for win.
           You against the dealer, the first that most close to 21 win, and if you
           both have the same number you get a push,and nobody win.

           Have fun :):)
*/
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        boolean boo = false;
        int playerSum = 0;
        int dealerSum = 0;

        System.out.println("Welcome To mini BlackJack: ");
        while (true){
            System.out.println("---------------");
            if (!boo) {
                System.out.println("Type 1 to start---");
                System.out.println("1. Get Cards");
                System.out.println("0. Stop the game");
                System.out.println("Type: ");
            }
            if (boo){
                System.out.println("Your option is---");
                System.out.println("2. Take another card");
                System.out.println("3. Show dealer Hand, and See if u win!");
                System.out.println("0. Stop the game");
                System.out.println("Type your option: ");
            }
            int choice =readInteger();

            if (choice == 0){
                System.out.println("Thank you for playing with as!!Good Bye!!");
                return;
            }

            switch (choice){
                case 1:
                    int[] firstArr = Connection.Actions(Connection.TWO_CARDS);
                    System.out.println("Card1: "+ firstArr[0] + "\nCard2: "+firstArr[1]);
                    boo = true;
                    playerSum = firstArr[0]+firstArr[1];
                    System.out.println("Your total card : " + playerSum);

                    break;
                case 2:
                    int[] secArr= Connection.Actions(Connection.ADD_A_NUM);
                    System.out.println("your new card is : " + secArr[0]);
                    playerSum += secArr[0];
                    System.out.println("Your Total card points is : " + playerSum);
                    if (playerSum > 21){
                        System.out.println("You lost in this round, Dealer Win.");
                        boo=false;
                        playerSum=0;
                    }
                    break;
                case 3:
                    int[] dealerArr = Connection.Actions(Connection.SHOW_DEALER);
                    dealerSum = dealerArr[0];
                    System.out.println("Dealer cards: " + dealerSum);
                    System.out.println("Your cards: " + playerSum);
                    if (dealerSum > playerSum){
                        System.out.println("Dealer WIN.");
                    }else if(dealerSum == playerSum){
                        System.out.println("Its a Draw ,PUSH!");
                    }else{
                        System.out.println("You Win!");
                    }
                    boo = false;
                    break;
            }
        }
    }
    private static int readInteger(){                    //General function to read Integer to console.
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }
}
