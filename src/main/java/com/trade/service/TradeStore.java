package com.trade.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.trade.exception.TradeCustomException;
import com.trade.model.Trade;
import com.trade.validate.TradeValidator;

public class TradeStore {
	private static Map<String,Object> tradeStoreMap = new HashMap<String, Object>();
	private TradeValidator tradeValidator = new TradeValidator();
	private Timer timer;
	public static final String VERSION_LOW = "Lower version received";
	public static final String LESS_MATURITY_DATE = "Maturity date should not be less than current date";
	
	public static Map<String, Object> getTradeStoreMap() {
		return tradeStoreMap;
	}

	public TradeStore() {
		scheduleTask();
	}
	
	public boolean addTrade(Trade trade) throws TradeCustomException{
		String id = trade.getTradeId().trim()+trade.getCounterPartId().trim();
		if(tradeStoreMap.containsKey(id)) {
			if(trade.getVersion()< ((Trade) tradeStoreMap.get(id)).getVersion()) {
				throw new TradeCustomException(VERSION_LOW);
			}else {
				tradeStoreMap.put(id,trade);
				return true;
			}
		}else if(tradeValidator.validateMaturityDate(trade)) {
			throw new TradeCustomException(LESS_MATURITY_DATE);
		}else {
			tradeStoreMap.put(id, trade);
			return true;
		}
	}
	
	private TimerTask customTimeTask() {
		return new TimerTask() {
			@Override
			public void run() {
				updateStore();
			}
		};
	}
	
	private void scheduleTask() {
		TimerTask timerTask = customTimeTask();
		timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, 3600000);
	}
	
	private void updateStore() {
		if(tradeStoreMap!=null && !tradeStoreMap.isEmpty()) {
			for(Map.Entry<String, Object> m : tradeStoreMap.entrySet()) {
				Trade t = (Trade) m.getValue();
				if(tradeValidator.validateMaturityDate(t)) {
					t.setExpired("Y");
				}
			}
		}
	}
	
	public void stopRunningTimer() {
		timer.cancel();
	}
}
