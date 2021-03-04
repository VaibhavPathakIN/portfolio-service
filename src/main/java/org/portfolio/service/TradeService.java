package org.portfolio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.portfolio.model.Trades;
import org.portfolio.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	public List<Trades> getRow() throws Exception{
		return (List<Trades>) tradeRepository.findAll();
	}
	
	public Map<String, Object> createRow(Trades trade) throws Exception{
		Trades saved = tradeRepository.save(trade);
		
		if(saved == null)
			throw new Exception("Trade could not be saved");
		
		Map<String, Object> result = new HashMap<>();
		result.put("tradeId", saved.getId());
		return result;
	}
	
	public void updateRow(long tradeId, Trades trade) throws Exception{
		Trades existed = tradeRepository.existsById(tradeId)?tradeRepository.findById(tradeId).get():null;
		if(existed == null) {
			throw new Exception("Nothing to update");
		}
		existed.setDate(trade.getDate());
		existed.setKey(trade.getKey());
		existed.setPrice(trade.getPrice());
		existed.setQuantity(trade.getQuantity());
		existed.setStockName(trade.getStockName());
		tradeRepository.save(existed);
	}
	
	public boolean deleteRow(long tradeId) throws Exception{
		if(!tradeRepository.existsById(tradeId)) {
			throw new Exception("Nothing to delete");
		}
		tradeRepository.deleteById(tradeId);
		return !tradeRepository.existsById(tradeId);
	}
}
