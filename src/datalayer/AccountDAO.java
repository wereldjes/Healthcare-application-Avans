/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import datalayerInterface.IAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author Dominique
 */
public class AccountDAO implements IAccount {

    private static AccountDAO instance;

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    @Override
    public void createAccount(Account a) {
        Connection con = null;
        String query = "INSERT INTO account (`username`, `password`)"
                + " VALUES (?, ?)";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAccount(Account a) {
        Connection con = null;
        String query = "DELETE FROM account WHERE username = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareCall(query);
            st.setString(1, a.getUsername());

            st.execute();
            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Account getAccount(String username) {
        Connection con = null;
        Account account = null;
        String query = "SELECT * FROM account WHERE username = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String accountUsername = rs.getString("username");
                String password = rs.getString("password");

                account = new Account(accountUsername, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }

}
