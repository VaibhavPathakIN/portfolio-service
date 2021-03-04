package org.portfolio.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.portfolio.model.Portfolio;
import org.portfolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepository;
	@Autowired
	RestTemplate restTemplate;

	private static final String url = "http://localhost:8080/api/get-resource-info";

	public List<Portfolio> getRow() throws Exception {
		return (List<Portfolio>) portfolioRepository.findAll();
	}

	public Map<String, Object> createRow(Portfolio porfolio) throws Exception {
		Portfolio saved = portfolioRepository.save(porfolio);

		if (saved == null)
			throw new Exception("Portfolio could not be saved");

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("portfolioId", saved.getId());
		return result;
	}

	public void updateRow(long portfolioId, Portfolio porfolio) throws Exception {
		Portfolio existed = portfolioRepository.existsById(portfolioId)
				? portfolioRepository.findById(portfolioId).get()
				: null;
		if (existed == null) {
			throw new Exception("Nothing to update");
		}

		existed.setMarketValue(porfolio.getMarketValue());
		existed.setCurrentPrice(porfolio.getCurrentPrice());
		existed.setAvgBuyPrice(porfolio.getAvgBuyPrice());
		existed.setQuantity(porfolio.getQuantity());
		existed.setStockName(porfolio.getStockName());
		existed.setUnrealizedReturns(porfolio.getUnrealizedReturns());
		existed.setRealizedReturns(porfolio.getRealizedReturns());

		portfolioRepository.save(existed);
	}

	public boolean deleteRow(long portfolioId) throws Exception {
		if (!portfolioRepository.existsById(portfolioId)) {
			throw new Exception("Nothing to delete");
		}
		portfolioRepository.deleteById(portfolioId);
		return !portfolioRepository.existsById(portfolioId);
	}

	public Map<String, Object> getStockPrice(Map<String, Object> keyData) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(keyData, headers);
		Map<String, Object> result =  restTemplate.exchange(url, HttpMethod.POST, entity, Map.class).getBody();
		
		if(result == null || result.isEmpty()) {
			throw new Exception("No price info found");
		}
		
		return result;
	}

}
