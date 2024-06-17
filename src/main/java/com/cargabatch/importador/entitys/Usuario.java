package com.cargabatch.importador.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Usuario {


	    @Id
	    private Long id;
	    private String name;
	    private String email;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

}
