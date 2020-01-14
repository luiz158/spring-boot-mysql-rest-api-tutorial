package com.digipay.bils.gateway.model;

import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Where(clause = "deleted IS NULL")
public class Participant {

	@Id
	private String             id;
	private ZonedDateTime      created;
	private ZonedDateTime      updated;
	private ZonedDateTime      deleted;
	private String             terminalId;
	private boolean            overDraftEnabled;
	private String             maxTranAmount;
	private List<Agent> 		agents;
	private boolean            active;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ZonedDateTime getCreated() {
		return created;
	}

	public void setCreated(ZonedDateTime created) {
		this.created = created;
	}

	public ZonedDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(ZonedDateTime updated) {
		this.updated = updated;
	}

	public ZonedDateTime getDeleted() {
		return deleted;
	}

	public void setDeleted(ZonedDateTime deleted) {
		this.deleted = deleted;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public boolean isOverDraftEnabled() {
		return overDraftEnabled;
	}

	public void setOverDraftEnabled(boolean overDraftEnabled) {
		this.overDraftEnabled = overDraftEnabled;
	}

	public String getMaxTranAmount() {
		return maxTranAmount;
	}

	public void setMaxTranAmount(String maxTranAmount) {
		this.maxTranAmount = maxTranAmount;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
