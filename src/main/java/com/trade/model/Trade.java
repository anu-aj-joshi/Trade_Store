package com.trade.model;

public class Trade {

	private String tradeId;
	private int version;
	private String counterPartId;
	private String bookId;
	private String maturityDate;
	private String trcreatedDate;
	private String expired;
		
	public Trade(String tradeId, int version, String counterPartId, String bookId, String maturityDate,
			String trcreatedDate, String expired) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartId = counterPartId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.trcreatedDate = trcreatedDate;
		this.expired = expired;
	}
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCounterPartId() {
		return counterPartId;
	}
	public void setCounterPartId(String counterPartId) {
		this.counterPartId = counterPartId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getTrcreatedDate() {
		return trcreatedDate;
	}
	public void setTrcreatedDate(String trcreatedDate) {
		this.trcreatedDate = trcreatedDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	
	
	
}
