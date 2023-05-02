package co.com.ath.calculadora.pruebas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "contactos")
public class ContactoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dni_contacto")
	private Integer dni;
	
	@Column(name = "nombre_contacto")
	private String nombre;
	
	@Column(name = "correo_contacto")
	private String correo;
	
	@Column(name = "cargo_contacto")
	private String cargo;
	
	@Column(name = "dni_fabrica")
	private Integer dniFabrica;
}
