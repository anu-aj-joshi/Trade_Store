package com.trade.validate;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.trade.model.Trade;

public class TradeValidator {
	
	public boolean validateMaturityDate(Trade trade) {
		return isExpire(trade.getMaturityDate());
	}
	
	private boolean isExpire(String date) {
		if(date.isEmpty() || date.trim().equals("")) {
			return true;
		}else {
			try {
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
				Date d = format1.parse(date);
				Date date1 = sdf.parse(sdf.format(d));
				Date date2 = sdf.parse(getToday("dd-MM-yyyy"));
				if(date1.before(date2)) {
					return true;
				}else {
					return false;
				}
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return true;
			}
			
			
		}
	}
	
	private String getToday(String format) {
		Date date = new Date();
		return new SimpleDateFormat(format).format(date);
	}
}
