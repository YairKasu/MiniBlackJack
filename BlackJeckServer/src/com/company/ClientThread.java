package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread {

    public static final int TWO_CARDS = 101;
    public static final int ADD_A_NUM = 102;
    public static final int SHOW_DEALER = 103;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            int action = inputStream.read();
            System.out.println(action); //to look if evey final function is passing.
            switch (action){
                case TWO_CARDS:
                    getTwoCards();
                    break;
                case ADD_A_NUM:
                    addNum();
                    break;
                case SHOW_DEALER:
                    dealerHand();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void dealerHand() throws IOException {
        Random r = new Random();                  //Give a random number to look the chance the dealer could get.
        int chance = r.nextInt(21-16) + 16;
        outputStream.write(chance);
    }

    private void addNum() throws IOException {
        int plusNum = getRandomNumber();
        outputStream.write(plusNum);
    }

    private void getTwoCards() throws IOException {
        int[] cards = new int[2];
        cards[0] = getRandomNumber();
        cards[1] = getRandomNumber();
        outputStream.write(cards[0]);
        outputStream.write(cards[1]);
        }

        private int getRandomNumber(){
            Random r = new Random();             // A general function that give a random card option until 11.
            int number = r.nextInt(10)+1;
            return number;
        }
}
