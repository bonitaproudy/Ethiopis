package networking;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

import static networking.MultiplayerServer.ETHIOPIS_PORT;

public class NetworkSniffer {

    private OnServerFoundCallback mainCallback;
    private CustomThreadPool mainThreadPool;

    NetworkSniffer(OnServerFoundCallback mainCallback) {
        this.mainCallback = mainCallback;
    }

    public static class ServerItem {
        Inet4Address address;
        String name;

        ServerItem(Inet4Address address, String name) {
            this.address = address;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Address: " + address + ", Name: " + name;
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
        mainThreadPool.shutdownNow();
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

    private boolean isIpAServer(byte[] address) throws IOException{
        Inet4Address inetAddress = (Inet4Address) Inet4Address.getByAddress(address);
        if (inetAddress.isReachable(1000)) {
            //System.out.println("@Reachable: " + getIpFromBytes(ipNormalizer(address)));
            try {
                Socket tester = new Socket(getIpFromBytes(ipNormalizer(address)), ETHIOPIS_PORT);
                tester.setSoTimeout(1000);
                if (tester.isConnected()) return true;
                else {
                    //System.out.println("Failed to connect to : " + getIpFromBytes(ipNormalizer(address)));
                    return false;
                }
            } catch (ConnectException x) {
                x.printStackTrace();
                return false;
            }
        } else {
            //System.out.println("!Reachable: " + getIpFromBytes(ipNormalizer(address)));
            return false;
        }
    }

    @Nullable
    void findServerInMyNetwork(CustomThreadPool threadPool) throws IOException {
        if (mainThreadPool == null) mainThreadPool = threadPool;
        byte[] address = myCurrentIP().getAddress();

        System.out.println("Address: " + Arrays.toString(address));
        System.out.println("Address Normalized: " + Arrays.toString(ipNormalizer(address)));

        for (int lastNode = 0; lastNode < 255; lastNode++) {
            byte[] mutableClone = address.clone();
            mutableClone[3] = (byte) lastNode;

            threadPool.submit(() -> {
                try {
                    if (isIpAServer(mutableClone)) {
                        System.out.println("WE FOUND SERVER @" + getIpFromBytes(
                                new int[]{mutableClone[0], mutableClone[1], mutableClone[2], mutableClone[3]}
                        ));

                        weFoundAServer(new ServerItem(
                                (Inet4Address) Inet4Address.getByAddress(mutableClone),
                                "NOOBIE"
                        ));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            if (mainThreadPool.isThreadPoolEmpty()) break;
        }

        weFoundAServer(null);
    }


}
