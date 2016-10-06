package com.systemic.gq.stock.service.spring;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.systemic.gq.entity.Stock;
import com.systemic.gq.stock.command.StockEdit;
import com.systemic.gq.stock.command.StockInfo;
import com.systemic.gq.stock.service.IStockService;
import com.systemic.gq.stock.service.dao.IStockDao;

public class SpringStockService implements IStockService {

	private IStockDao stockDao;
	
	
	public void setStockDao(IStockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Override
	public Stock selectStock(String id) {
		return (Stock) stockDao.load(Stock.class, id);
	}

	@Override
	public List<Stock> selectAllStock() {
		return stockDao.selectAllStock();
	}

	@Override
	public Page selectPageStock(StockInfo stock) {
		return stockDao.selectPageStock(stock);
	}

	@Override
	public void insertStock(StockEdit stockedit) {
			Stock stock=new Stock();
			if(StringUtils.isNotBlank(stockedit.getId())){
				BeanUtils.copyProperties(stockedit,stock);
			}else{
				BeanUtils.copyProperties(stockedit,stock,new String[]{"id"});
			}
			stock.setCreateTime(new Date());
			stockDao.save(stock);
		
		
	}

	@Override
	public void deleteStock(String[] idstr) {
		for (String id : idstr) {
			Stock stock=this.selectStock(id);
			if(stock!=null){
				stockDao.delete(stock);
				stock=null;
			}
			
		}
		
	}

}
