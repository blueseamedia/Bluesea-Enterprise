package vn.topmedia.monitor.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import vn.topmedia.monitor.MonitorBean;
import vn.topmedia.monitor.form.SMSMonitor;

/**
 * 
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class Server extends Thread {

    private ServerSocket dateServer;
    public static boolean running = true;
    private SMSMonitor monitor;

    public Server(int port, SMSMonitor monitor) throws IOException {
        this.monitor = monitor;
        dateServer = new ServerSocket(port);
        System.out.println("Server listening on port " + port);
    }

    public void closeSocket() {
        running = false;
        try {
            dateServer.close();
            System.out.println("Server socket closed OK.");
        } catch (IOException e) {
            System.out.println("Error closing server socket " + e.toString());
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("Waiting for connections.");
                Socket client = dateServer.accept();
                System.out.println("Accepted a connection from: " + client.getInetAddress());
                Connect connect = new Connect(client, monitor);
                connect.start();
            } catch (Exception e) {
                if (running) {
                    System.out.println("Error getting connection " + e.toString());
                } else {
                    System.out.println("Server Socket closed");
                }
            }
        }
        try {
            dateServer.close();
        } catch (Exception e) {
        }
    }
}

class Connect extends Thread {

    private Socket client = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private SMSMonitor monitor;

    public Connect(Socket clientSocket, SMSMonitor monitor) {
        this.monitor = monitor;
        client = clientSocket;
        try {
            ois = new ObjectInputStream(client.getInputStream());
            oos = new ObjectOutputStream(client.getOutputStream());
        } catch (Exception ex) {
            System.out.println("Error open stream " + ex);
            closeConnect();
        }
    }

    /**
     * Close connect to client
     */
    private void closeConnect() {
        try {
            oos.close();
        } catch (Exception e) {
        }
        try {
            ois.close();
        } catch (Exception e) {
        }
        try {
            client.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        try {
            Object object = ois.readObject();
            if (object instanceof MonitorBean) {
                MonitorBean smsError = (MonitorBean) object;
                if (smsError != null) {
                    monitor.alert(smsError);
                }
            } else {
                System.out.println("Object not instance of MonitorBean.");
            }
        } catch (IOException e) {
            System.out.println("Error getting object " + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Error getting object: " + e.toString());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException ioe) {
            }

        }
    }
}