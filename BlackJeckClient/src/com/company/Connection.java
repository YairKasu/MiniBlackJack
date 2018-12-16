package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    public static final int PORT = 3000;
    public static final String HOST = "127.0.0.1";

    public static final int TWO_CARDS = 101;
    public static final int ADD_A_NUM = 102;
    public static final int SHOW_DEALER = 103;

    public static Socket socket;
    public static InputStream inputStream;
    public static OutputStream outputStream;

    public static int[] Actions(int action){
        int[] arr = new int[2];
        try {
            socket = new Socket(HOST, PORT);            //Connect to the match server "BlackJackServer".
            inputStream = socket.getInputStream();      //Get all the information from the server
            outputStream = socket.getOutputStream();    //Putout all the information back to the server
            switch (action){
                case TWO_CARDS:
                    arr = getTwoCards();
                    break;
                case ADD_A_NUM:
                    arr = addNum();
                    break;
                case SHOW_DEALER:
                    arr = dealerHand();
                    break;
            }
        } catch (UnknownHostException e){
            e.printStackTrace();
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
        return arr;
    }

    public static int[] dealerHand() throws IOException {   //dealer against you
        outputStream.write(SHOW_DEALER);
        int[] dealerArr = new int[1];
        int dealer = inputStream.read();
        dealerArr[0] = dealer;

        return dealerArr;
    }

    public static int[] addNum() throws IOException {            //the number that you choose if u want to add it .
        outputStream.write(ADD_A_NUM);
        int[] add = new int[1];
        int plusNum = inputStream.read();
        add[0] = plusNum;

        return add;
    }

    public static int[] getTwoCards() throws IOException {  //get two cards that show you your opening game.
        outputStream.write(TWO_CARDS);
        int[] cards = new int[2];
        cards[0] =inputStream.read();
        cards[1]= inputStream.read();
        return cards;
    }
}
