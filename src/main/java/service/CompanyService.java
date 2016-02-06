/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.Company;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ccampos
 */
public class CompanyService {

    public List<Company> getAll() {
        
// TODO this is dummy
        
        List<Company> list = new ArrayList<>();
        
        Company a = new Company();
                Company b = new Company();

        a.setName("barbosa");
        b.setName("vidigal");
        
        a.setAdress("aveiro");
        b.setAdress("viseu");
        
        list.add( a );     
        list.add( b );

        return list;
    }
    
}
