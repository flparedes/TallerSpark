package es.franl2p.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name= "Manufacturers")
public class Manufacturer extends Validable {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Integer id;
	
	private String name;
	
	private String country;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	protected boolean check() {
		boolean result = true;
		List<String> errores = new LinkedList<String>();
		
		if (name == null || "".equals(name)) {
			result = false;
			errores.add("El nombre es obligatorio");
		}
		
		if (country == null || "".equals(country)) {
			result = false;
			errores.add("El país es obligatorio");
		}
		
		setErrors(errores);
		
		return result;
	}
}
