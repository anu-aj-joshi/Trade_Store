package com.trade.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trade.exception.TradeCustomException;
import com.trade.model.Trade;
import com.trade.service.TradeStore;

public class TradeStoreTests {
	static TradeStore tradeStore;
	
	@BeforeClass
	public static void initTradeStore() {
		tradeStore = new TradeStore();
	}
	
	@Test
	public void addValidTrade() {
		Trade t1 = new Trade("T1", 2, "CP-1", "B1", "20/05/2022", "25/04/2021","N");
		try {
			assertTrue(tradeStore.addTrade(t1));
		} catch (TradeCustomException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addMinimumVersionTrade() {
		Trade t1 = new Trade("T1", 1, "CP-1", "B1", "20/05/2022", "25/04/2021","N");
		Exception exception = assertThrows(TradeCustomException.class, ()->{
			tradeStore.addTrade(t1);
		});
		String expectedMessage = TradeStore.VERSION_LOW;
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void addExpiredTrade() {
		Trade t1 = new Trade("T2", 1, "CP-2", "B2", "20/03/2021", "25/02/2021","N");
		Exception exception = assertThrows(TradeCustomException.class, ()->{
			tradeStore.addTrade(t1);
		});
		String expectedMessage = TradeStore.LESS_MATURITY_DATE;
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	
	@Test
	public void addTradeToOverrideExistingTrade() {
		Trade t1 = new Trade("T1", 2, "CP-1", "B2", "20/05/2022", "25/04/2021","N");
		try {
			tradeStore.addTrade(t1);
			Trade storedTrade = (Trade) TradeStore.getTradeStoreMap().get(t1.getTradeId()+t1.getCounterPartId());
			assertEquals(t1.getBookId(), storedTrade.getBookId());
		} catch (TradeCustomException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void stop() {
		tradeStore.stopRunningTimer();
	}
}
