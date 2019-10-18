/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayerInterface;

import java.util.List;
import model.Appointment;

/**
 *
 * @author Dominique
 */
public interface IAppointment {

    void createAppointment(Appointment a);

    List<Appointment> getAllAppointmentsFromPatient(int id);

    void updateAppointment(Appointment a);

    void deleteAppointment(int id);

}
