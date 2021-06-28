package model.entities;

import java.util.Date;

public class Contracts {

	private Integer contractId;
	private Integer clientId;
	private Date initialDate;
	private Date finalDate;
	private Double totalValue;

	public Contracts() {
		super();
	}

	public Contracts(Integer contractId,Integer clientId, Date initialDate, Date finalDate, Double totalValue) {
		super();
		this.contractId = contractId;
		this.clientId = clientId;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.totalValue = totalValue;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
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
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
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
		Contracts other = (Contracts) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractId:" + contractId + "ClientID: "+ clientId + ", initialDate:" + initialDate + ", finalDate:" + finalDate
				+ ", totalValue:" + String.format("%.2f", totalValue);
	}

}
