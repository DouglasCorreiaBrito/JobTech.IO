package br.com.jobtechIO.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Candidate extends BaseEntity implements UserDetails {

	@Column(nullable = false, length = 250)
	private String name;

	@Column(nullable = false, length = 250)
	private String CPF;

	@Column(nullable = false, length = 250)
	private String email;

	@Column(nullable = false, length = 250)
	private String password;

	@Column(nullable = false, length = 250)
	private String telephone;

	@Column(nullable = false, length = 250)
	private String address;

	@Column(nullable = false)
	private LocalDate birthdate;

	@Column(nullable = false)
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profile = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "candidateSkill", joinColumns = { @JoinColumn(name = "candidateId") }, inverseJoinColumns = {
			@JoinColumn(name = "skillId") })
	private List<Skill> skills;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profile;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}