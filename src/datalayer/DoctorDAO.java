/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import datalayerInterface.IDoctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Doctor;

/**
 *
 * @author Dominique
 */
public class DoctorDAO implements IDoctor {

    private static DoctorDAO instance;

    public static DoctorDAO getInstance() {
        if (instance == null) {
            instance = new DoctorDAO();
        }
        return instance;
    }

    @Override
    public void createDoctor(Doctor d) {
        Connection con = null;
        String query = "INSERT INTO doctor (`firstname`, `lastname`, `account_username`)"
                + "VALUES (?, ?, ?)";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareCall(query);
            st.setString(1, d.getFirstName());
            st.setString(2, d.getLastName());
            st.setString(3, d.getAccountID());

            st.execute();
            st.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateDoctor(Doctor d) {
        Connection con = null;
        String query = "UPDATE doctor SET firstname = ?, lastname = ? WHERE id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, d.getFirstName());
            st.setString(2, d.getLastName());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctor(int id) {
        Connection con = null;
        Doctor d = null;
        String query = "SELECT * FROM doctor WHERE doctor_id = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int doctorID = rs.getInt("id");
                String doctorName = rs.getString("firstname");
                String doctorLastname = rs.getString("lastname");
                String accountID = rs.getString("account_id");

                d = new Doctor(doctorID, accountID, doctorName, doctorLastname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    public Doctor getDoctorByAccountUsername(String username) {
        Connection con = null;
        Doctor d = null;
        String query = "SELECT * FROM doctor WHERE account_username = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int doctorID = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String accountID = rs.getString("account_username");

                d = new Doctor(doctorID, accountID, firstname, lastname);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return d;
    }

}
