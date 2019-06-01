package networking;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

import static networking.MultiplayerServer.ETHIOPIS_PORT;
import static networking.MultiplayerServer.ETHIOPIS_SNIFFER__PORT;

public class NetworkSniffer {

    private OnServerFoundCallback mainCallback;
    private CustomThreadPool mainThreadPool;
    private boolean isHostFound = false;

    NetworkSniffer(OnServerFoundCallback mainCallback) {
        this.mainCallback = mainCallback;
    }

    public static class ServerItem {
        byte[] address;
        String name;

        ServerItem(byte[] address, String name) {
            this.address = address;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Address: " + Arrays.toString(address) + ", Name: " + name;
        }
    }

    public interface OnServerFoundCallback {
        void onServerFound(ServerItem item) throws IOException;
    }

    private String getIpFromBytes(int[] address) {
        return String.valueOf(address[0]) + "."
                + String.valueOf(address[1]) + "."
                + String.valueOf(address[2]) + "."
                + String.valueOf(address[3]);
    }

    private int[] ipNormalizer(byte[] ipAddress) {
        // if the ip is negative ... that means we are working with
        // 7-bit and we should switch to 8-bit
        int[] returnable = new int[4];
        int position = 0;
        for (byte node: ipAddress) {
            if (node < 0) returnable[position] = 128 - node;
            else returnable[position] = node;
            position++;
        }
        return returnable;
    }

    private void weFoundAServer(ServerItem item) throws IOException {
        if (mainCallback != null) mainCallback.onServerFound(item);
        isHostFound = true;
    }

    private Inet4Address myCurrentIP() throws IOException {
        Inet4Address ip = null;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(Inet4Address.getByName("8.8.8.8"), 10002);
            ip = (Inet4Address) socket.getLocalAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    private boolean isIpAServer(byte[] address, byte[] myIp) throws IOException{

        if (address[3] == 0 || address[3] == 1 || address[3] == myIp[3]) return false;

        Inet4Address inetAddress = (Inet4Address) Inet4Address.getByAddress(address);
        if (inetAddress.isReachable(1000)) {
            System.out.println("@Reachable: " + getIpFromBytes(ipNormalizer(address)));
            try {
                Socket tester = new Socket((Inet4Address.getByAddress(address)), ETHIOPIS_SNIFFER__PORT);
                System.out.println("Failed to connect to : " + getIpFromBytes(ipNormalizer(address)));
                return true;
            } catch (ConnectException x) {
                x.printStackTrace();
                return false;
            }
        } else {
           // System.out.println("!Reachable: " + getIpFromBytes(ipNormalizer(address)));
            return false;
        }
    }

    void findServerInMyNetwork(CustomThreadPool threadPool) throws IOException {
        new Thread(() -> {
            if (mainThreadPool == null) mainThreadPool = threadPool;
            byte[] address = new byte[0];
            try {
                address = myCurrentIP().getAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Address: " + Arrays.toString(address));
            System.out.println("Address Normalized: " + Arrays.toString(ipNormalizer(address)));

            for (int lastNode = 0; lastNode < 255; lastNode++) {
                byte[] mutableClone = address.clone();
                mutableClone[3] = (byte) lastNode;

                byte[] finalAddress = address;
                threadPool.submit(() -> {
                    try {
                        if (isIpAServer(mutableClone, finalAddress)) {
                            System.out.println("WE FOUND SERVER @" + getIpFromBytes(
                                    new int[]{mutableClone[0], mutableClone[1], mutableClone[2], mutableClone[3]}
                            ));

                            weFoundAServer(new ServerItem(
                                    mutableClone,
                                    "NOOBIE"
                            ));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            while (true) {
                if (isHostFound) {
                    mainThreadPool.shutdownNow();
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

}
