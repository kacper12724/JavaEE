/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Łukasz
 */
@Entity
@Table(name = "orderr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderr.findAll", query = "SELECT o FROM Orderr o")
    , @NamedQuery(name = "Orderr.findById", query = "SELECT o FROM Orderr o WHERE o.id = :id")
    , @NamedQuery(name = "Orderr.findByDate", query = "SELECT o FROM Orderr o WHERE o.date = :date")})
public class Orderr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "id_food", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Food idFood;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idUser;

    public Orderr() {
    }

    public Orderr(Integer id) {
        this.id = id;
    }

    public Orderr(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Food getIdFood() {
        return idFood;
    }

    public void setIdFood(Food idFood) {
        this.idFood = idFood;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Orderr)) {
            return false;
        }
        Orderr other = (Orderr) object;
        return this == other;
    }

    @Override
    public String toString() {
        return "application.ejb.entity.Orderr[ id=" + id + " ]";
    }

}
