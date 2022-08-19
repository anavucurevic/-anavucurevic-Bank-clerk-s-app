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
public class Account extends AbstractDomainObject implements Serializable{

    private Client client;
    private int accountID;
    private String accountNumber;
    private String currency;
    private double depositedAmount;
    private int cardLimit;
    private double withdrawalFee;
    private String typeAccount;
    private String typeCreditPayment;

    public Account() {
    }

    public Account(Client client, int accountID, String accountNumber, String currency, double depositedAmount, int cardLimit, double withdrawalFee, String typeAccount, String typeCreditPayment) {
        this.client = client;
        this.accountID = accountID;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.depositedAmount = depositedAmount;
        this.cardLimit = cardLimit;
        this.withdrawalFee = withdrawalFee;
        this.typeAccount = typeAccount;
        this.typeCreditPayment = typeCreditPayment;
    }

   
    
    
    
    
    @Override
    public String nazivTabele() {
        return "Account";
    }

    @Override
    public String alijas() {
        return "a";
    }

    @Override
    public String join() {
         return " JOIN client c ON (c.id = a.clientID) ";
                
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Client c = new Client(rs.getLong("Id"),
                    rs.getString("Name"), rs.getString("Lastname"),
                   rs.getString("DateOfBirth"), rs.getString("Adress"), rs.getString("Country"),
                    rs.getString("TypeCustomer"),null);

                    Account a = new Account(c, rs.getInt("AccountID"),
                            rs.getString("AccountNumber"), rs.getString("Currency"),
                            rs.getDouble("DepositedAmount"),rs.getInt("CardLimit"),
                            rs.getDouble("WithdrawalFee"), rs.getString("TypeAccount"),
                           rs.getString("TypeCreditPayment"));
            
           
            lista.add(a);
        }

        rs.close();
        return lista;
    }
    
    
   

    @Override
    public String koloneZaInsert() {
        return " (ClientID, AccountID, AccountNumber, Currency, DepositedAmount, CardLimit, WithdrawalFee, TypeAccount,  TypeCreditPayment) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
         return " ClientID = " + client.getId() +  "   AND AccountID = " + accountID ;
    }

    @Override
    public String vrednostiZaInsert() {
         return client.getId() + ", " + accountID + ", '" + accountNumber + " ' , '" + currency + 
                 " ' ,  " + depositedAmount + ", " + cardLimit + ", " + withdrawalFee +
                 ", '"+ typeAccount + "',' " + typeCreditPayment + "'";
                
    }

    @Override
    public String vrednostiZaUpdate() {
         return " DepositedAmount =" + depositedAmount + " ";
    }

    @Override
    public String getByID() {
         return " WHERE c.id = " + client.getId();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    

    public int getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(int cardLimit) {
        this.cardLimit = cardLimit;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(double withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getTypeCreditPayment() {
        return typeCreditPayment;
    }

    public void setTypeCreditPayment(String typeCreditPayment) {
        this.typeCreditPayment = typeCreditPayment;
    }

    public double getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(double depositedAmount) {
        this.depositedAmount = depositedAmount;
    }
    
}
