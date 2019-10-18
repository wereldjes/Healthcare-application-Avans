/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import datalayerInterface.IMedicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Medicine;

/**
 *
 * @author Dominique
 */
public class MedicineDAO implements IMedicine {

    private static MedicineDAO instance;

    public static MedicineDAO getInstance() {
        if (instance == null) {
            instance = new MedicineDAO();
        }
        return instance;
    }

    @Override
    public void createMedicine(Medicine m) {
        Connection con = null;
        String query = "INSERT INTO medicine (`name`, `description`)"
                + " VALUES (?, ?)";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, m.getName());
            st.setString(2, m.getDescription());

            st.execute();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medicine getMedicine(String name) {
        Connection con = null;
        Medicine m = null;
        String query = "SELECT * FROM medicine WHERE name = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String medicineName = rs.getString("name");
                String description = rs.getString("description");

                m = new Medicine(medicineName, description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<Medicine> getAllMedicine() {

        Connection con = null;
        List<Medicine> allMedicine = new ArrayList<>();
        String query = "SELECT * FROM medicine";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Medicine m = new Medicine();

                m.setName(rs.getString("name"));
                m.setDescription(rs.getString("description"));

                allMedicine.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allMedicine;
    }

    @Override
    public void deleteMedicine(String id) {
        Connection con = null;
        String query = "DELETE FROM medicine WHERE name = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, id);
            st.execute();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicine(Medicine m, String name) {
        Connection con = null;
        String query = "UPDATE medicine SET name = ?, description = ? WHERE name = ?";

        try {
            con = MysqlConnector.getInstance().connect();
            PreparedStatement st = con.prepareStatement(query);

            st.setString(1, m.getName());
            st.setString(2, m.getDescription());
            st.setString(3, name);
            st.executeUpdate();
            st.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
