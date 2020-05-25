package br.com.jobtechIO.domain.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Profile extends BaseEntity implements GrantedAuthority {

    private  String profileName;

    @Override
    public String getAuthority() {
        return this.profileName;
    }
}
