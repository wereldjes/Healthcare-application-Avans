/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayerInterface;

import java.util.List;
import model.Disease;

/**
 *
 * @author Dominique
 */
public interface IDisease {

    void createDisease(Disease d);

    Disease getDisease(String name);

    void deleteDisease(String id);

    void updateDisease(Disease d, String name);

    List<Disease> getAllDiseases();
}
