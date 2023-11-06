/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plmviewer.dao;

import com.plmviewer.model.UserAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JA306771
 */
@Repository("useractionimpl")
public class UserActionImpl implements UserActionDAO {

    @Autowired
    SessionFactory sessionfactory;

    @Override
    public void addaction(UserAction useraction) {
        try {
            Session session = sessionfactory.openSession();
            Transaction transcation=session.getTransaction();
            transcation.begin();
            session.save(useraction);
            session.flush();
            transcation.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
