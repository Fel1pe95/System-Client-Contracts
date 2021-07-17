package model.entities;

public class Locations {

	private Integer id;
	private Integer cod;
	private String productName;
	private Integer quantity;
	private Double totalValue;

	public Locations() {
		super();
	}

	public Locations(Integer id, Integer cod, String productName, Integer quantity, Double totalValue) {
		super();
		this.id = id;
		this.cod = cod;
		this.productName = productName;
		this.quantity = quantity;
		this.totalValue = totalValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((totalValue == null) ? 0 : totalValue.hashCode());
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
		Locations other = (Locations) obj;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (totalValue == null) {
			if (other.totalValue != null)
				return false;
		} else if (!totalValue.equals(other.totalValue))
			return false;
		return true;
	}

}
