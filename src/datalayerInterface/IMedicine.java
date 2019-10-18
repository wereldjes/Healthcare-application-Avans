/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayerInterface;

import java.util.List;
import model.Medicine;

/**
 *
 * @author Dominique
 */
public interface IMedicine {

    void createMedicine(Medicine m);

    Medicine getMedicine(String name);

    void deleteMedicine(String id);

    void updateMedicine(Medicine m, String name);

    List<Medicine> getAllMedicine();

}
