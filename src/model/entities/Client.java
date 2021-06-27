package model.entities;

public class Client {
	
	private String name;
	private String email;
	private Integer cpf;
	private Integer registration;
	
	private Contracts contracts;
	
	public Client() {
		super();
	}

	public Client(String name, String email, Integer cpf, Integer registration, Contracts contracts) {
		super();
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.registration = registration;
		this.contracts = contracts;
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

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Integer getRegistration() {
		return registration;
	}

	public void setRegistration(Integer registration) {
		this.registration = registration;
	}

	public Contracts getContracts() {
		return contracts;
	}

	public void setContracts(Contracts contracts) {
		this.contracts = contracts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registration == null) ? 0 : registration.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registration == null) {
			if (other.registration != null)
				return false;
		} else if (!registration.equals(other.registration))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", email=" + email + ", cpf=" + cpf + ", registration=" + registration
				+ ", contracts=" + contracts + "]";
	}

	
	
	
	
	

}
