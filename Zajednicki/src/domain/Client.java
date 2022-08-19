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
public class Client extends AbstractDomainObject implements Serializable{

    private Long id;
    private String name;
    private String lastname;
    private String dateOfBirth;
    private String adress;
    private String country;
    private String typeCustomer;
    private ArrayList<Account> accounts;

    public Client() {
    }

    public Client(Long id, String name, String lastname, String dateOfBirth, String adress, String country, String typeCustomer, ArrayList<Account> accounts) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
        this.country = country;
        this.typeCustomer = typeCustomer;
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return name + " " + lastname;
    }

 

 
    
    
    
          
    
    
    
    @Override
    public String nazivTabele() {
         return " Client ";
    }

    @Override
    public String alijas() {
         return "c";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Client c = new Client(rs.getLong("Id"),
                    rs.getString("Name"), rs.getString("Lastname"),
                   rs.getString("DateOfBirth"), rs.getString("Adress"), rs.getString("Country"),
                    rs.getString("TypeCustomer"),null);

            lista.add(c);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
          return " (Name,Lastname,DateOfBirth,Adress,Country,TypeCustomer) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
         return " Id = " + id;
    }

    @Override
    public String vrednostiZaInsert() {
         return "'" + name + "', '" + lastname + " ' , '" +dateOfBirth  + "' , '" +adress+  "', ' "
                + country + " ' , '" + typeCustomer + " ' ";
    }

    @Override
    public String vrednostiZaUpdate() {
       
      return "Adress =  ' " + adress +" ' ";
      
      
    }

    @Override
    public String getByID() {
         return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

 

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
