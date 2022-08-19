/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Client;
import domain.Account;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class TableModelAccounts extends AbstractTableModel {

    private ArrayList<Account> lista;
    private String[] kolone = {"AccountID", "Account number","Currency",
        "Deposited amount", "Card limit", "Withradal fee", "Account type", "Type of credit payment"};
    int rb = 0;

    public TableModelAccounts() {
        lista = new ArrayList<>();
    }

    public TableModelAccounts(Client c) {
        try {
            lista = ClientController.getInstance().getAllAccount(c);
        } catch (Exception ex) {
            Logger.getLogger(TableModelAccounts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }
 

    @Override
    public Object getValueAt(int row, int column) {
        Account a = lista.get(row);

        switch (column) {
            case 0:
                return a.getAccountID();
            
            case 1:
                return a.getAccountNumber();
            case 2:
                return a.getCurrency();
            case 3:
                return a.getDepositedAmount();
            case 4:
                return a.getCardLimit();
            case 5:
                return a.getWithdrawalFee();
            case 6:
                return a.getTypeAccount();
            case 7:
                return a.getTypeCreditPayment();

            default:
                return null;
        }
    }

    public void addAcount(Account a) {

      

        rb = 0;
        for (Account account : lista) {
            account.setAccountID(++rb);
        }

        a.setAccountID(++rb);
        lista.add(a);
        fireTableDataChanged();
    }

    public void deleteAccount(int row) {
        lista.remove(row);

        rb = 0;
          for (Account account : lista) {
            account.setAccountID(++rb);
        }

        fireTableDataChanged();

    }

    public Account getSelectedAccount(int row) {
        return lista.get(row);
    }

    public ArrayList<Account> getLista() {
        return lista;
    }

   
    
      

}
