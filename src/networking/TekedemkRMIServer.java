package networking;

import javafx.scene.Group;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TekedemkRMIServer extends UnicastRemoteObject implements ITekedemkRMI{

    private TekedemkCallback mainCallback;

    public interface TekedemkCallback {
        void onMekedem(boolean tekedemku);
    }

    protected TekedemkRMIServer(TekedemkCallback callback) throws RemoteException {
        this.mainCallback = callback;
    }

    @Override
    public void tekedemk() {
        mainCallback.onMekedem(true);
    }
}
