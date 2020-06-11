/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.dal;

import hr.bareza.dal.sql.SqlRepository;

/**
 *
 * @author Bareza
 */
public class RepositoryFactory {

    public RepositoryFactory() {
        
    }
    
    public static Repository getRepository() {
        return new SqlRepository();
    }
    
}
