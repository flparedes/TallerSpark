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
@Table(name = "Documents")
public class Document extends Validable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	private String name;
	private byte[] data;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_USER")
	private User user;

	public Document() {
		// No-arg constructor
	}

	public Document(Integer id, String name, User user) {
		this.id = id;
		this.name = name;
		this.user = user;
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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	protected boolean check() {
		boolean result = true;
		List<String> errors = new LinkedList<String>();
		
		if (name == null || "".equals(name)) {
			result = false;
			errors.add("Name must be set");
		}
		
		if (user == null) {
			result = false;
			errors.add("User must be specified");
		}
		
		setErrors(errors);
		
		return result;
	}
}
