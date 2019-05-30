package networking;

import gamelogic.MatchCharacterFamilyGame;
import gamelogic.MatchCharacterFamilyGameItem;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static gamelogic.Levels.LEVEL_EASY;

public class MultiplayerServer {

    private InetAddress connectedDeviceAddress;
    private ServerSocket mainSocket;
    private Thread mainSocketThread;
    public static final int ETHIOPIS_PORT = 21109;


    @SuppressWarnings("InfiniteLoopStatement")
    public MultiplayerServer() throws IOException {
        mainSocket = new ServerSocket(ETHIOPIS_PORT);
        System.out.println("SERVER: " + "init server");
        mainSocketThread = new Thread(() -> {
            while(true)
                try {
                    System.out.println("SERVER: " + "listening to connections");
                    Socket connectedSocket = mainSocket.accept();
                    connectedDeviceAddress = connectedSocket.getInetAddress();
                    initProtocol(connectedSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    public void startServer() {
        if (mainSocketThread != null) {
            mainSocketThread.start();
            System.out.println("SERVER: " + "starting server");
        }
    }

    public void stopServer() {
        if (mainSocketThread != null) mainSocketThread.interrupt();
    }

    private void initProtocol(Socket connectedSocket) throws IOException {
        // This is how the protocol works
        // HI -> HI
        // SHALL I SEND GAME -> YES
        //      |__ __ __
        // - - -|__-__-__| Game goes to the client
        // RECEIVED? -> YES
        // TELL ME WHEN TO START -> START
        // `BOTH PLAY` ====> until they hit the tekedemk rmi method

        Scanner scanner = new Scanner(connectedSocket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(connectedSocket.getOutputStream());
        PrintWriter out = new PrintWriter(connectedSocket.getOutputStream(), true);

        out.println("HI");
        while (scanner.hasNext()) {
            String i = scanner.nextLine();
            i = i.toUpperCase();    // make everything cap
            if (i.equals("HI:*")) out.println("COMMAND:" + "SEND_GAME"); //client sent "HI:*"
            else if (i.startsWith("ANSWER:")) {
                if (i.substring(i.indexOf("ANSWER:") + "ANSWER:".length()).startsWith("SEND_GAME:")) {
                    //client sent "ANSWER:SEND_GAME:YES or ANSWER:SEND_GAME:NO
                    if (i.contains("YES")) {
                        out.println("COMMAND:" + "ABOUT_TO_SEND_GAME");
                        outputStream.writeObject(new MatchCharacterFamilyGame(LEVEL_EASY).getASingleGame(8));
                    }
                    else out.println("COMMAND:" + "ABORTED_SENDING_GAME");
                } else if (i.substring(i.indexOf("ANSWER:") + "ANSWER:".length()).startsWith("RECEIVED:")) {
                    //client sent "ANSWER:RECEIVED:YES or ANSWER:RECEIVED:NO
                    if (i.contains("YES")) out.println("COMMAND:" + "CALL_TO_START");
                    else out.println("COMMAND:" + "ABORTED_STARTING_GAME");
                }
            }
            else if (i.startsWith("COMMAND:")) {
                if (i.substring(i.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("START_GAME")) {
                    //client sent "COMMAND:START_GAME"
                    //now we start the game on our side
                } else if (i.substring(i.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("TEKEDEMEK")) {
                    //client sent "COMMAND:TEKEDEMEK"
                    //so i lost
                }
            }
        }



     }



}
