/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.account;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Account;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ana
 */
public class SOGetAllAccount extends AbstractSO {

    private ArrayList<Account> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Account)) {
            throw new Exception("The forwarded object is not an instance of the class Account!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> accounts = DBBroker.getInstance().select(ado);
        list = (ArrayList<Account>) (ArrayList<?>) accounts;
    }

    public ArrayList<Account> getLista() {
        return list;
    }

}
