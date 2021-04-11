package uff.dac.depraia.apidepraia.dto;

import lombok.Getter;
import uff.dac.depraia.apidepraia.model.Banhista;
import uff.dac.depraia.apidepraia.model.Agenda;

@Getter
public class BanhistaDTO {   
    private UserDTO user;
    private AgendaIdDTO agenda;

    public BanhistaDTO() {
    }
    
    public BanhistaDTO(Banhista n) {
        this.user = new UserDTO(n.getUser());
        this.agenda = new AgendaIdDTO(n.getAgenda());
    }
            
    public Banhista conversor(Agenda n) {
        return new Banhista(user.conversor(), n);
    }
}
