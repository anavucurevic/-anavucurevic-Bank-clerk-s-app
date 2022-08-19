/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Account;
import domain.Administrator;
import domain.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Ana
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_CLIENT:
                    ServerController.getInstance().addClient((Client) req.getData());
                    break;
                case Operation.ADD_ACCOUNT:
                    ServerController.getInstance().addAccount((Account) req.getData());
                    break;
                
                case Operation.DELETE_CLIENT:
                    ServerController.getInstance().deleteClient((Client) req.getData());
                    break;
                case Operation.DELETE_ACCOUNT:
                    ServerController.getInstance().deleteAccount((Account) req.getData());
                    break;
               
                case Operation.UPDATE_CLIENT:
                    ServerController.getInstance().updateClient((Client) req.getData());
                    break;
                case Operation.UPDATE_ACCOUNT:
                    ServerController.getInstance().updateAccount((Account) req.getData());
                    break;
                    
                case Operation.GET_ALL_CLIENT:
                    res.setData(ServerController.getInstance().getAllClient());
                    break;
                
                
                case Operation.GET_ALL_ACCOUNT:
                    res.setData(ServerController.getInstance().getAllAccount((Client) req.getData()));
                    break;
                
                case Operation.LOGIN:
                    ArrayList<Administrator> lista = ServerController.getInstance().getAllAdministrator();
                    Administrator a = (Administrator) req.getData();
                    for (Administrator administrator : lista) {
                        if (administrator.getUsername().equals(a.getUsername())
                                && administrator.getPassword().equals(a.getPassword())) {
                            res.setData(administrator);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("There is no administrator with these credentials.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
