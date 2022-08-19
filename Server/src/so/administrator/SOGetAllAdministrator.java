/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.administrator;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Administrator;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Ana
 */
public class SOGetAllAdministrator extends AbstractSO {

    private ArrayList<Administrator> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("The forwarded object is not an instance of the class Administrator");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> listaAdministrator = DBBroker.getInstance().select(ado);
        list = (ArrayList<Administrator>) (ArrayList<?>) listaAdministrator;
    }

    public ArrayList<Administrator> getLista() {
        return list;
    }

}
