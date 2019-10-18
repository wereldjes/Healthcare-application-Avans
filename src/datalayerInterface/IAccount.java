/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayerInterface;

import model.Account;

/**
 *
 * @author Dominique
 */
public interface IAccount {

    void createAccount(Account a);

    void deleteAccount(Account a);

    public Account getAccount(String username);
}
