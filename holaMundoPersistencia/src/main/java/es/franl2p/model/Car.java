package es.franl2p.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Cars")
public class Car extends Validable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	private String name;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_MANUFACTURER")
	private Manufacturer manufacturer;

	public Car() {
		// No-arg constructor
	}

	public Car(Integer id, String name, Manufacturer manufacturer) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
	}

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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	protected boolean check() {
		boolean result = true;
		List<String> errores = new LinkedList<String>();
		
		if (name == null || "".equals(name)) {
			result = false;
			errores.add("El nombre es obligatorio");
		}
		
		if (manufacturer == null) {
			result = false;
			errores.add("El fabricante es obligatorio");
		}
		
		setErrors(errores);
		
		return result;
	}
}
