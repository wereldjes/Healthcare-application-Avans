/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import datalayerInterface.IAppointment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Appointment;

/**
 *
 * @author Dominique
 */
public class AppointmentDAO implements IAppointment {

    private static AppointmentDAO instance;

    public static AppointmentDAO getInstance() {
        if (instance == null) {
            instance = new AppointmentDAO();
        }
        return instance;
    }

    @Override
    public void createAppointment(Appointment a) {
        Connection con = null;
        String query = "INSERT INTO appointment (`problem_description`, `startdate_medicine`, `enddate_medicine`, `disease_name`, `medicine_name`, `patient_id`)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, a.getProblemDescription());
            st.setDate(2, a.getStartdateMedicine());
            st.setDate(3, a.getEnddateMedicine());
            st.setString(4, a.getDiseaseName());
            st.setString(5, a.getMedicineName());
            st.setInt(6, a.getPatientId());

            st.execute();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Appointment> getAllAppointmentsFromPatient(int id) {
        Connection con = null;
        List<Appointment> allAppointments = new ArrayList<>();
        String query = "SELECT * FROM appointment WHERE patient_id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("id");
                String problemDescription = rs.getString("problem_description");
                Date startdateMedicine = rs.getDate("startdate_medicine");
                Date enddateMedicine = rs.getDate("enddate_medicine");
                String diseaseName = rs.getString("disease_name");
                String medicineName = rs.getString("medicine_name");
                int patientId = rs.getInt("patient_id");

                Appointment a = new Appointment(appointmentId, problemDescription, startdateMedicine, enddateMedicine, diseaseName, medicineName, patientId);
                allAppointments.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return allAppointments;
    }

    @Override
    public void updateAppointment(Appointment a) {
        Connection con = null;
        String query = "UPDATE appointment SET id= ?, problem_description = ?, startdate_medicine = ?, "
                + "enddate_medicine = ?, disease_name = ?, medicine_name = ?, patient_id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, a.getId());
            st.setString(2, a.getProblemDescription());
            st.setDate(3, a.getStartdateMedicine());
            st.setDate(4, a.getEnddateMedicine());
            st.setString(5, a.getDiseaseName());
            st.setString(6, a.getMedicineName());
            st.setInt(7, a.getPatientId());

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAppointment(int id) {
        Connection con = null;
        String query = "DELETE FROM appointment WHERE id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);

            st.execute();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Appointment getAppointment(int id) {
        Connection con = null;
        Appointment appointment = null;
        String query = "SELECT * FROM appointment WHERE id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("id");
                String problemDescription = rs.getString("problem_description");
                Date startdateMedicine = rs.getDate("startdate_medicine");
                Date enddateMedicine = rs.getDate("enddate_medicine");
                String diseaseName = rs.getString("disease_name");
                String medicineName = rs.getString("medicine_name");
                int patientId = rs.getInt("patient_id");

                appointment = new Appointment(appointmentId, problemDescription, startdateMedicine, enddateMedicine, diseaseName, medicineName, patientId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appointment;
    }

}
