/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayerInterface;

import model.Account;
import model.Doctor;

/**
 *
 * @author Dominique
 */
public interface IDoctor {

    void createDoctor(Doctor d);

    void updateDoctor(Doctor d);

    Doctor getDoctor(int id);

}
