package networking;

import gamelogic.RearrangeCharacterGameItem;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class MultiplayerServer {

    private InetAddress connectedDeviceAddress;
    private ServerSocket mainSocket;
    private ServerSocket snifferSocket;
    private Thread mainSocketThread;
    private PrintWriter printWriter;
    private RearrangeCharacterGameItem sharedItem;
    public static final int ETHIOPIS_PORT = 21109;
    public static final int ETHIOPIS_SNIFFER__PORT = 21209;
    private OnGameStarted mainCallback;

    public interface OnGameStarted {
        void startYourEngine(RearrangeCharacterGameItem game) throws IOException;
        void tekedemk(Date time);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public MultiplayerServer(RearrangeCharacterGameItem item, OnGameStarted callback) throws IOException {
        this.mainCallback = callback;
        this.sharedItem = item;

        mainSocket = new ServerSocket(ETHIOPIS_PORT);
        System.out.println("SERVER: " + "init server");
        snifferSocket = new ServerSocket(ETHIOPIS_SNIFFER__PORT);
        System.out.println("SNIFFER_SERVER: " + "init sniffer receiver server");

        mainSocketThread = new Thread(() -> {
            try {
                System.out.println("SERVER: " + "listening to connections");
                while (true) {
                    Socket connectedSocket = mainSocket.accept();
                    connectedDeviceAddress = connectedSocket.getInetAddress();
                    ObjectOutputStream outputStream = new ObjectOutputStream(connectedSocket.getOutputStream());
                    outputStream.flush();

                    initProtocol(connectedSocket, outputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        new Thread(() -> {
            try {
                System.out.println("SNIFFER_SERVER: " + "listening to connections");
                while (true) {
                    Socket connectedSocket = snifferSocket.accept();
                    connectedSocket.getOutputStream().write(55);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startServer() {
        if (mainSocketThread != null) {
            mainSocketThread.start();
            System.out.println("SERVER: " + "starting server");
        }
    }

    public void kedem() {
        if (mainSocket != null && printWriter != null) {
            printWriter.println("COMMAND:TEKEDEMEK");
        }
    }

    public void stopServer() throws IOException {
        if (mainSocket != null) mainSocket.close();
        if (printWriter != null) printWriter.close();
        if (mainSocketThread != null) mainSocketThread.interrupt();
    }

    private void initProtocol(Socket connectedSocket, ObjectOutputStream outputStream) throws IOException {
        // This is how the protocol works
        // HI -> HI
        // SHALL I SEND GAME -> YES
        //      |__ __ __
        // - - -|__-__-__| Game goes to the client
        // RECEIVED? -> YES
        // TELL ME WHEN TO START -> START
        // `BOTH PLAY` ====> until the winner sends the tekedemk command

        Scanner scanner = new Scanner(connectedSocket.getInputStream());
        printWriter = new PrintWriter(connectedSocket.getOutputStream(), true);

        printWriter.println("HI");
        while (scanner.hasNext()) {
            String i = scanner.nextLine();
            i = i.toUpperCase();    // make everything cap
            if (i.equals("HI:*")) printWriter.println("COMMAND:" + "SEND_GAME"); //client sent "HI:*"
            else if (i.startsWith("ANSWER:")) {
                if (i.substring(i.indexOf("ANSWER:") + "ANSWER:".length()).startsWith("SEND_GAME:")) {
                    //client sent "ANSWER:SEND_GAME:YES or ANSWER:SEND_GAME:NO
                    if (i.contains("YES")) {
                        printWriter.println("COMMAND:" + "ABOUT_TO_SEND_GAME");
                        outputStream.writeObject(sharedItem);
                    }
                    else printWriter.println("COMMAND:" + "ABORTED_SENDING_GAME");
                } else if (i.substring(i.indexOf("ANSWER:") + "ANSWER:".length()).startsWith("RECEIVED:")) {
                    //client sent "ANSWER:RECEIVED:YES or ANSWER:RECEIVED:NO
                    if (i.contains("YES")) printWriter.println("COMMAND:" + "CALL_TO_START");
                    else printWriter.println("COMMAND:" + "ABORTED_STARTING_GAME");
                }
            }
            else if (i.startsWith("COMMAND:")) {
                if (i.substring(i.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("START_GAME")) {
                    //client sent "COMMAND:START_GAME"
                    //now we start the game on our side
                    if (mainCallback != null) mainCallback.startYourEngine(sharedItem);
                } else if (i.substring(i.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("TEKEDEMEK")) {
                    //client sent "COMMAND:TEKEDEMEK"
                    //so i lost
                    if (mainCallback != null) mainCallback.tekedemk(new Date());
                }
            }
        }

     }

}
