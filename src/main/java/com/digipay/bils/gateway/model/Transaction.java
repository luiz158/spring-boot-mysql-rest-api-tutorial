package com.digipay.bils.gateway.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Where(clause = "deleted IS NULL")
public class Transaction  {

	private String              linkedTransactionId;
	private String              messageType;
	private String              type;
	private String              extendedType;
	private BigDecimal          amount;
	private String              agentId;
	private String              debitAccountType;
	private String              debitAccountId;
	private String              debitAccountProduct;
	private String              creditAccountId;
	private String              debitReference;
	private String              creditReference;
	private String              currencyCode;
	private String              reference;
	private String              originalReference;
	private String              paymentMethod;
	private String              terminalId;
	private Map<String, Object> requestAdditionalData;
	private String              responseCode;
	private String              responseDescription;
	private String              upstreamResponseCode;
	private String              upstreamResponseDescription;
	private Map<String, Object> responseAdditionalData;
	private String              state;
	private String              channel;


	public String getLinkedTransactionId() {
		return linkedTransactionId;
	}

	public void setLinkedTransactionId(String linkedTransactionId) {
		this.linkedTransactionId = linkedTransactionId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtendedType() {
		return extendedType;
	}

	public void setExtendedType(String extendedType) {
		this.extendedType = extendedType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getDebitAccountType() {
		return debitAccountType;
	}

	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}

	public String getDebitAccountId() {
		return debitAccountId;
	}

	public void setDebitAccountId(String debitAccountId) {
		this.debitAccountId = debitAccountId;
	}

	public String getDebitAccountProduct() {
		return debitAccountProduct;
	}

	public void setDebitAccountProduct(String debitAccountProduct) {
		this.debitAccountProduct = debitAccountProduct;
	}

	public String getCreditAccountId() {
		return creditAccountId;
	}

	public void setCreditAccountId(String creditAccountId) {
		this.creditAccountId = creditAccountId;
	}

	public String getDebitReference() {
		return debitReference;
	}

	public void setDebitReference(String debitReference) {
		this.debitReference = debitReference;
	}

	public String getCreditReference() {
		return creditReference;
	}

	public void setCreditReference(String creditReference) {
		this.creditReference = creditReference;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getOriginalReference() {
		return originalReference;
	}

	public void setOriginalReference(String originalReference) {
		this.originalReference = originalReference;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Map<String, Object> getRequestAdditionalData() {
		return requestAdditionalData;
	}

	public void setRequestAdditionalData(Map<String, Object> requestAdditionalData) {
		this.requestAdditionalData = requestAdditionalData;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getUpstreamResponseCode() {
		return upstreamResponseCode;
	}

	public void setUpstreamResponseCode(String upstreamResponseCode) {
		this.upstreamResponseCode = upstreamResponseCode;
	}

	public String getUpstreamResponseDescription() {
		return upstreamResponseDescription;
	}

	public void setUpstreamResponseDescription(String upstreamResponseDescription) {
		this.upstreamResponseDescription = upstreamResponseDescription;
	}

	public Map<String, Object> getResponseAdditionalData() {
		return responseAdditionalData;
	}

	public void setResponseAdditionalData(Map<String, Object> responseAdditionalData) {
		this.responseAdditionalData = responseAdditionalData;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
