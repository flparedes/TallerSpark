package es.franl2p.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name= "Users")
public class User extends Validable {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	private String pass;
	private String salt;
	
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	protected boolean check() {
		boolean result = true;
		List<String> errors = new LinkedList<String>();
		
		if (name == null || "".equals(name)) {
			result = false;
			errors.add("Name must be set");
		}
		
		if (pass == null || "".equals(pass)) {
			result = false;
			errors.add("Password must be set");
		}
		
		setErrors(errors);
		
		return result;
	}
}
