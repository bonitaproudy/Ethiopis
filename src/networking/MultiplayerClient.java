package networking;

import gamelogic.MatchCharacterFamilyGameItem;
import gamelogic.RearrangeCharacterGameItem;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;

import static networking.MultiplayerServer.ETHIOPIS_PORT;
import static networking.NetworkSniffer.ServerItem;

public class MultiplayerClient {

    private InetAddress serverAddress;
    private Socket mainSocket;
    private ArrayList<ServerItem> serverItems = new ArrayList<>();
    private RearrangeCharacterGameItem receivedGameItem;
    private PrintWriter writer;
    private boolean aboutToReceiveGame = false;
    private boolean gameReceived = false;
    private MultiplayerServer.OnGameStarted mainCallback;

    public MultiplayerClient(MultiplayerServer.OnGameStarted mainCallback) {
        this.mainCallback = mainCallback;
    }

    public void startSniffer() throws IOException {
        NetworkSniffer networkSniffer = new NetworkSniffer(item -> communicateWithAServer(item.address));
        networkSniffer.findServerInMyNetwork(Executors.newFixedThreadPool(200));
    }

    public void communicateWithAServer(byte[] address) throws IOException {
        if (address == null) return;
        mainSocket = new Socket((Inet4Address.getByAddress(address)), ETHIOPIS_PORT);
        Scanner scanner = new Scanner(mainSocket.getInputStream());
        ObjectInputStream dataInputStream = new ObjectInputStream(mainSocket.getInputStream());
        writer  = new PrintWriter(mainSocket.getOutputStream(), true);

        while(scanner.hasNext()) {
            String scannerLine = scanner.nextLine();
            System.out.println(scannerLine);
            figureOutResponseForServer(writer, dataInputStream, scannerLine);
        }
    }

    public void kedem() {
        if (mainSocket != null && writer != null) { writer.println("COMMAND:TEKEDEMEK"); }
//        try {
//            if (mainSocket != null && writer != null) { mainSocket.close(); writer.close(); }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void figureOutResponseForServer(PrintWriter writer, ObjectInputStream inputStream, String serverCommand) {
        if (serverCommand.startsWith("HI")) writer.println("HI:*");
        else if (serverCommand.startsWith("COMMAND:")) {
            if (serverCommand.substring(serverCommand.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("SEND_GAME")) {
                //server said "COMMAND:SEND_GAME"
                writer.println("ANSWER:" + "SEND_GAME:" + "YES");
            } else if (serverCommand.substring(serverCommand.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("ABOUT_TO_SEND_GAME")) {
                //server said "COMMAND:ABOUT_TO_SEND_GAME"
                try {
                    receivedGameItem = (RearrangeCharacterGameItem) inputStream.readObject();
                    writer.println("ANSWER:RECEIVED:YES");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    writer.println("ANSWER:RECEIVED:NO");
                }
            } else if (serverCommand.substring(serverCommand.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("ABORTED_SENDING_GAME")) {
                //server said "COMMAND:ABORTED_SENDING_GAME"

            } else if (serverCommand.substring(serverCommand.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("CALL_TO_START")) {
                //server said "COMMAND:CALL_TO_START"
                if (mainCallback != null) {
                    try {
                        mainCallback.startYourEngine(receivedGameItem);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                writer.println("COMMAND:START_GAME");
            } else if (serverCommand.substring(serverCommand.indexOf("COMMAND:") + "COMMAND:".length()).startsWith("TEKEDEMEK")) {
                if (mainCallback != null) mainCallback.tekedemk(new Date());
            }
        }
    }


}
