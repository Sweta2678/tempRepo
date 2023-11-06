/**
 *
 */
package com.plmviewer.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Subselect;
import org.springframework.stereotype.Component;

/**
 * @author uthanasekarapandian
 *
 */

@Entity
@Table(name = "useraction")
@Access(value = AccessType.FIELD)
public class UserAction {

    
 
    
    @Id
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "ACTIONDATE")
    private Timestamp actiontime;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ACTION")
    private String action;
    

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getActiontime() {
        return actiontime;
    }

    public void setActiontime(Timestamp actiontime) {
        this.actiontime = actiontime;
    }

}
