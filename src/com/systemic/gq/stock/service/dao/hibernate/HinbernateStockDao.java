package com.systemic.gq.stock.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.stock.command.StockInfo;
import com.systemic.gq.stock.service.dao.IStockDao;

public class HinbernateStockDao extends HibernateCommonDao implements IStockDao {

	
	private IQueryStringUtil stockQueryStringUtil;
	
	public void setStockQueryStringUtil(IQueryStringUtil stockQueryStringUtil) {
		this.stockQueryStringUtil = stockQueryStringUtil;
	}

	

	@Override
	public List<Stock> selectAllStock() {
		List stockList = super.doQuery("from " + Stock.class.getName()
				+ " as stock"); 
		if (stockList.isEmpty()) {
			return null;
		}
		return stockList;
	}

	@Override
	public Page selectPageStock(StockInfo stock) {
		IQueryObject io = this.stockQueryStringUtil.getQueryObject(stock);
		return super.find(io.getQueryString(), io.getParam(),
				stock.getPageNumber(), stock.getPageSize());
	}

}
