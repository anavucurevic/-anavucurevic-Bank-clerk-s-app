/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class Administrator extends AbstractDomainObject implements Serializable {
    
    private int administratorID;
    private String adminName;
    private String adminLastName;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(int administratorID, String adminName, String adminLastName, String username, String password) {
        this.administratorID = administratorID;
        this.adminName = adminName;
        this.adminLastName = adminLastName;
        this.username = username;
        this.password = password;
    }
            
            
    
    
        @Override
    public String toString() {
        return adminName + " " + adminLastName;
    }

    @Override
    public String nazivTabele() {
        return " Administrator ";
    }

    @Override
    public String alijas() {
        return " d ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator d = new Administrator(rs.getInt("AdministratorID"),
                    rs.getString("AdminName"), rs.getString("AdminLastname"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(d);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (AdminName, AdminLastname, Username, Password) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " AdministratorID = " + administratorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + adminName + "', '" + adminLastName + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + adminName + "', Prezime = '" + adminLastName + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public int getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(int administratorID) {
        this.administratorID = administratorID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
