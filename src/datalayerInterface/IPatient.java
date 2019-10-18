/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayerInterface;

import java.util.List;
import model.Doctor;
import model.Patient;

/**
 *
 * @author Dominique
 */
public interface IPatient {

    void createPatient(Patient p, Doctor d);

    void updatePatient(Patient p, Doctor d);

    void deletePatient(int id);

    Patient getPatient(int id);

    Patient getPatientByName(String firstname);

    List<Patient> getAllPatients();

}
