package es.franl2p.model;

import java.util.List;

import javax.persistence.Transient;

public abstract class Validable {
	private List<String> errors;
	
	@Transient
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Transient
	public boolean validate() {
		return check();
	}
	
	/**
	 * Checks if there are errors.
	 * @return 
	 */
	@Transient
	protected abstract boolean check();
}
