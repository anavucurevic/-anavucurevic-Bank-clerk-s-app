/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Client;
import domain.Account;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Ana
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addClient(Client client) throws Exception {
        sendRequest(Operation.ADD_CLIENT, client);
    }
    
    public void addAccount(Account account) throws Exception {
        sendRequest(Operation.ADD_ACCOUNT, account);
    }
    
  
    public void deleteAccount(Account account) throws Exception {
        sendRequest(Operation.DELETE_ACCOUNT, account);
    }

    public void deleteClient(Client client) throws Exception {
        sendRequest(Operation.DELETE_CLIENT, client);
    }
    

    public void updateClient(Client client) throws Exception {
        sendRequest(Operation.UPDATE_CLIENT, client);
    }
    
    public void updateAccount(Account account) throws Exception {
        sendRequest(Operation.UPDATE_ACCOUNT, account);
    }
    
    
    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }
    
    public ArrayList<Client> getAllClient() throws Exception {
        return (ArrayList<Client>) sendRequest(Operation.GET_ALL_CLIENT, null);
    }
    
    public ArrayList<Account> getAllAccount(Client c) throws Exception {
        return (ArrayList<Account>) sendRequest(Operation.GET_ALL_ACCOUNT,c);
    }
    
   
    
    
    private Object sendRequest(int operation, Object data) throws Exception {
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        if (res.getResponseStatus().equals(ResponseStatus.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }
}
