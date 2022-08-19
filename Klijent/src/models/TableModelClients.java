/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Client;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class TableModelClients extends AbstractTableModel implements Runnable {

    private ArrayList<Client> list;
    private String[] kolone = {"ID", "Name", "Lastname", "Date of birth", "Adress", "Country", "Type of customer"};
    private String parametar = "";

    public TableModelClients() {
        try {
            list = ClientController.getInstance().getAllClient();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
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
        Client c = list.get(row);

        switch (column) {
            case 0:
                return c.getId();
            case 1:
                return c.getName();
            case 2:
                return c.getLastname();
            case 3:
                return c.getDateOfBirth();
            case 4:
                return c.getAdress();
            case 5:
                return c.getCountry();
            case 6:
                return c.getTypeCustomer();

            default:
                return null;
        }
    }

    public Client getSelectedClient(int row) {
        return list.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            list = ClientController.getInstance().getAllClient();
            if (!parametar.equals("")) {
                ArrayList<Client> newList = new ArrayList<>();
                for (Client c : list) {
                    if (c.getName().toLowerCase().contains(parametar.toLowerCase())
                            || c.getLastname().toLowerCase().contains(parametar.toLowerCase())) {
                        newList.add(c);
                    }
                }
                list = newList;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
