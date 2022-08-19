/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import domain.Account;
import domain.Administrator;
import domain.Client;
import java.util.ArrayList;
import so.AbstractSO;

import so.client.SOAddClient;
import so.client.SODeleteClient;
import so.client.SOGetAllClient;
import so.client.SOUpdateClient;
import so.account.SOAddAccount;
import so.account.SODeleteAccount;
import so.account.SOGetAllAccount;
import so.account.SOUpdateAccount;
import so.administrator.SOGetAllAdministrator;


/**
 *
 * @author Ana
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addClient(Client client) throws Exception {
        AbstractSO aso = new SOAddClient();
        aso.templateExecute(client);
    }
    
    public void addAccount(Account account) throws Exception {
        AbstractSO aso = new SOAddAccount();
        aso.templateExecute(account);
    }
    

    public void deleteClient(Client client) throws Exception {
        AbstractSO aso = new SODeleteClient();
        aso.templateExecute(client);
    }
    
    public void deleteAccount(Account account) throws Exception {
        AbstractSO aso = new SODeleteAccount();
        aso.templateExecute(account);
    }
    
 

    public void updateClient(Client client) throws Exception {
        AbstractSO aso = new SOUpdateClient();
        aso.templateExecute(client);
    }
    
    public void updateAccount(Account account) throws Exception {
        AbstractSO aso = new SOUpdateAccount();
        aso.templateExecute(account);
    }
  
    public ArrayList<Client> getAllClient() throws Exception {
        SOGetAllClient so = new SOGetAllClient();
        so.templateExecute(new Client());
        return so.getLista(); 
    }
     public ArrayList<Administrator> getAllAdministrator() throws Exception {
         SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista(); 
    }
 
    
    
    public ArrayList<Account> getAllAccount(Client c) throws Exception {
        SOGetAllAccount so = new SOGetAllAccount();
        
        Account a = new Account();
        a.setClient(c);
        
        so.templateExecute(a);
        return so.getLista();
    }

}
