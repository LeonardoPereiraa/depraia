package uff.dac.depraia.apidepraia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banhista implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    private Integer tipoUsuario;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    private User user;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;
    
    public Banhista() {}

    public Banhista(Integer tipoUsuario, User user, Agenda agenda) {
        this.tipoUsuario = tipoUsuario;
        this.user = user;
        this.agenda = agenda;
    }
}
