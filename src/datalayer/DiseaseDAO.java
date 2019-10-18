/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import datalayerInterface.IDisease;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Disease;

/**
 *
 * @author Dominique
 */
public class DiseaseDAO implements IDisease {

    private static DiseaseDAO instance;

    public static DiseaseDAO getInstance() {
        if (instance == null) {
            instance = new DiseaseDAO();
        }
        return instance;
    }

    @Override
    public void createDisease(Disease d) {
        Connection con = null;
        String query = "INSERT INTO disease (`name`, `description`)"
                + " VALUES (?, ?)";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, d.getName());
            st.setString(2, d.getDescription());

            st.execute();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Disease getDisease(String name) {
        Connection con = null;
        Disease d = null;
        String query = "SELECT * FROM disease WHERE name = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String medicineName = rs.getString("name");
                String description = rs.getString("description");

                d = new Disease(medicineName, description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void deleteDisease(String id) {
        Connection con = null;
        String query = "DELETE FROM disease WHERE name = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, id);
            st.execute();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DiseaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateDisease(Disease d, String name) {
        Connection con = null;
        String query = "UPDATE disease SET name = ?, description = ? WHERE name =  ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, d.getName());
            st.setString(2, d.getDescription());
            st.setString(3, name);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DiseaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Disease> getAllDiseases() {
        Connection con = null;
        List<Disease> allDiseases = new ArrayList<>();
        String query = "SELECT * FROM disease";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Disease d = new Disease(rs.getString("name"), rs.getString("description"));
                allDiseases.add(d);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allDiseases;
    }
}
