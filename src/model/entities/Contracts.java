package model.entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class Contracts {

	private Integer contractId;
	private Integer clientId;
	private Date initialDate;
	private Date finalDate;
	private Double totalValue;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private List<Locations> list;

	public Contracts() {
		super();
	}

	public Contracts(Integer contractId, Integer clientId, Date initialDate, Date finalDate, Double totalValue) {
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

	public void addLocationOnContract(Locations location) {
		list.add(location);
	}

	public Double totalValueContract(Contracts contract, Double totalValue) {

		LocalDate initialDate = contract.getInitialDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate finalDate = contract.getFinalDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		long totalOfDays = ChronoUnit.DAYS.between(initialDate, finalDate);

		Double totalDaily = totalValue * totalOfDays;

		return totalDaily;
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
		return "\nNumero do contrato: " + contractId + "\nMatricula do cliente: " + clientId + "\nData Inicial:"
				+ sdf.format(initialDate) + "\nData final:" + sdf.format(finalDate) + "\nValor total:"
				+ String.format("%.2f", totalValue);
	}

}
