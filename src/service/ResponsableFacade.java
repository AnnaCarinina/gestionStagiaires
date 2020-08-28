/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Responsable;

/**
 *
 * @author LEILA
 */
public class ResponsableFacade extends AbstractFacade<Responsable>{
    
    public ResponsableFacade() {
        super(Responsable.class);
    }
    
     public Object[] seConnecter(String login, String password) {
        Responsable responsable = find(login);
        if (responsable == null) {
            return new Object[]{-1, null};
        } else if (!responsable.getPassword().equals(password)) {
            return new Object[]{-2, null};
        } else if (responsable.getBlocked()==1) {
            return new Object[]{-3, null};
        } else {
            return new Object[]{1, responsable};
        }
    }
    
}
