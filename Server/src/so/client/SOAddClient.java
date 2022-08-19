/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.client;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Account;
import domain.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ana
 */
public class SOAddClient extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("The forwarded object is not an instance of the class Client");
        }
        
        Client c  = (Client) ado;
        
         if (c.getAccounts().isEmpty()) {
            throw new Exception("You must have at least one account");
        }
     
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
         PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long clientID = tableKeys.getLong(1);
       
        Client c = (Client) ado;
        c.setId(clientID);
       
        for (Account account : c.getAccounts()) {
            account.setClient(c);
            DBBroker.getInstance().insert(account);
        }
        
     
      
    }
        
    }


   

