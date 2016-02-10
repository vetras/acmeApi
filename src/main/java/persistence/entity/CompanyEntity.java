package persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
@NamedQuery(name = "Film.findAll", query = "SELECT c FROM company c order by c.id")
public class CompanyEntity implements java.io.Serializable {

    private static final long serialVersionUID = -4974733123803118872L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer Id;
    
    @Column(name = "NAME")
    private String Name;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
    

}
