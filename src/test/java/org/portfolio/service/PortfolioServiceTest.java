package org.portfolio.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.portfolio.model.Portfolio;
import org.portfolio.repository.PortfolioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PortfolioServiceTest {
	
	private Portfolio input;
	private Portfolio expected;
	private PortfolioRepository repo;
	private RestTemplate restTemplate;
	private PortfolioService service;
	private ResponseEntity<Map> response;
	private Map<String, Object> price;
	
	@Before
	public void init() {
		initData();
		repo = Mockito.mock(PortfolioRepository.class);
		restTemplate = Mockito.mock(RestTemplate.class);
		response = Mockito.mock(ResponseEntity.class);
		service = new PortfolioService(repo, restTemplate);
	}
	
	@After
	public void destroy() {
		input = null;
		expected = null;
		repo = null;
		restTemplate = null;
		service = null;
	}
	
	
	/**
	 * Method to initialize mock data
	 */
	public void initData() {

		input = new Portfolio();
		input.setAvgBuyPrice(2005.154);
		input.setCurrentPrice(148.153);
		input.setMarketValue(545.15);
		input.setQuantity(200);
		input.setRealizedReturns(8735.15);
		input.setStockName("AAN");
		input.setUnrealizedReturns(4848.487);

		expected = new Portfolio();
		expected.setId(1l);
		expected.setAvgBuyPrice(2005.154);
		expected.setCurrentPrice(148.153);
		expected.setMarketValue(545.15);
		expected.setQuantity(200);
		expected.setRealizedReturns(8735.15);
		expected.setStockName("AAN");
		expected.setUnrealizedReturns(4848.487);
		
		price = new HashMap<>();
		price.put("1. open", "22.554");
	}
	
	@Test
	public void getRowTest() throws Exception {
		Mockito.when(repo.findAll()).thenReturn(new ArrayList<Portfolio>() {{ add(expected); }});
		List<Portfolio> result = service.getRow();
		assertEquals(true, expected.equals(result.get(0)));
	}
	
	@Test
	public void createRowTest() throws Exception {
		Mockito.when(repo.save(Mockito.any())).thenReturn(expected);
		Map<String, Object> result = service.createRow(input);
		assertEquals(1l, result.get("portfolioId"));
	}
	
	@Test(expected = Exception.class)
	public void updateRow() throws Exception {
		Mockito.when(repo.existsById(Mockito.anyLong())).thenReturn(false);
		service.updateRow(2l, input);
	}
	
	@Test
	public void deleteRow() throws Exception {
		Mockito.when(repo.existsById(Mockito.anyLong())).thenReturn(true);
		Mockito.doNothing().when(repo).deleteById(1l);
		service.deleteRow(1l);
	}

}
