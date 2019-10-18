/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import datalayerInterface.IPatient;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;
import model.Gender;
import model.Patient;

/**
 *
 * @author Dominique
 */
public class PatientDAO implements IPatient {

    private static PatientDAO instance;

    public static PatientDAO getInstance() {
        if (instance == null) {
            instance = new PatientDAO();
        }
        return instance;
    }

    //TODO fix shitty code
    @Override
    public void createPatient(Patient p, Doctor d) {
        Connection con = null;
        String query = "INSERT INTO patient (`firstname`, `lastname`, `bsn`, `date_of_birth`, `gender`, `adress`, `doctor_id`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareCall(query);

            st.setString(1, p.getFirstName());
            st.setString(2, p.getLastName());
            st.setInt(3, p.getBsn());
            st.setDate(4, p.getDateOfBirth());
            st.setObject(5, p.getGender().toString());
            st.setString(6, p.getAdress());
            st.setInt(7, d.getDoctorID());

            st.execute();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        Connection con = null;
        List<Patient> allPatients = new ArrayList<>();
        String query = "SELECT * FROM patient";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Patient p = new Patient();

                p.setPatientID(rs.getInt("id"));
                p.setFirstName(rs.getString("firstname"));
                p.setLastName(rs.getString("lastname"));
                p.setBsn(rs.getInt("bsn"));;
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(Gender.valueOf(rs.getString("gender")));
                p.setAdress(rs.getString("adress"));
                p.setDoctorID(rs.getInt("doctor_id"));

                allPatients.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allPatients;
    }

    public List<Patient> getAllPatientsFromDoctor(Doctor d) {
        Connection con = null;
        List<Patient> allPatients = new ArrayList<>();
        String query = "SELECT * FROM patient WHERE doctor_id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, d.getDoctorID());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Patient p = new Patient();

                p.setPatientID(rs.getInt("id"));
                p.setFirstName(rs.getString("firstname"));
                p.setLastName(rs.getString("lastname"));
                p.setBsn(rs.getInt("bsn"));;
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(Gender.valueOf(rs.getString("gender")));
                p.setAdress(rs.getString("adress"));
                p.setDoctorID(rs.getInt("doctor_id"));

                allPatients.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allPatients;
    }

    @Override
    public void updatePatient(Patient p, Doctor d) {
        Connection con = null;
        String query = "UPDATE patient SET id = ?, firstname = ?, lastname = ?, bsn = ?, date_of_birth = ?, gender = ?, adress = ?, doctor_id = ?"
                + " WHERE id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, p.getPatientID());
            st.setString(2, p.getFirstName());
            st.setString(3, p.getLastName());
            st.setInt(4, p.getBsn());
            st.setDate(5, p.getDateOfBirth());
            st.setObject(6, p.getGender().toString());
            st.setString(7, p.getAdress());
            st.setInt(8, d.getDoctorID());
            st.setInt(9, p.getPatientID());

            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int id) {
        Connection con = null;
        String query = "DELETE FROM patient WHERE id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, id);
            st.execute();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient getPatientByName(String firstname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient getPatient(int id) {
        Connection con = null;
        Patient p = null;
        String query = "SELECT * FROM patient WHERE id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int patientID = rs.getInt("id");
                String patientFirstname = rs.getString("firstname");
                String patientLastname = rs.getString("lastname");
                int bsn = rs.getInt("bsn");
                Date dateOfBirth = rs.getDate("date_of_birth");
                Gender gender = Gender.valueOf(rs.getString("gender"));
                String adress = rs.getString("adress");
                int doctorID = rs.getInt("doctor_id");

                p = new Patient(patientID, patientFirstname, patientLastname, bsn, dateOfBirth, adress, gender, doctorID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

}
