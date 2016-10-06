package com.systemic.gq.stock.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.Stock;
import com.systemic.gq.stock.command.StockEdit;
import com.systemic.gq.stock.command.StockInfo;

public interface IStockService {
	

	Stock selectStock(String id);
	
	List<Stock> selectAllStock();
	
	Page selectPageStock(StockInfo stock);

	void insertStock(StockEdit stockedit);

	void deleteStock(String[] idstr);
}
