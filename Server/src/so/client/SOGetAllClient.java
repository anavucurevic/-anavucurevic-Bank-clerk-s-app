/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.client;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Client;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ana
 */
public class SOGetAllClient extends AbstractSO {

    private ArrayList<Client> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("The forwarded object is not an instance of the class Client!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> clients = DBBroker.getInstance().select(ado);
        list = (ArrayList<Client>) (ArrayList<?>) clients;
    }

    public ArrayList<Client> getLista() {
        return list;
    }

}
