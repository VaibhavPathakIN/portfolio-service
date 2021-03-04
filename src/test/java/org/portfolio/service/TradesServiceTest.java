package org.portfolio.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.portfolio.model.BuySell;
import org.portfolio.model.Trades;
import org.portfolio.repository.TradeRepository;

public class TradesServiceTest {
	
	private Trades trade;
	private Trades expected;
	private TradeRepository repo;
	private TradeService service;
	
	@Before
	public void init() {
		initData();
		repo = Mockito.mock(TradeRepository.class);
		service = new TradeService(repo);	
	}
	
	@After
	public void destroy() {
		trade = null;
		expected = null;
		repo = null;
		service = null;
	}
	
	
	/**
	 * Method to initialize mock data
	 */
	public void initData() {

		trade = new Trades();
		trade.setDate("2021-03-01");
		trade.setId(1l);
		trade.setKey(BuySell.BUY);
		trade.setPrice(201.05);
		trade.setQuantity(200);
		trade.setStockName("AAN");

		expected = new Trades();
		expected.setDate("2021-03-01");
		expected.setId(1l);
		expected.setKey(BuySell.BUY);
		expected.setPrice(201.05);
		expected.setQuantity(200);
		expected.setStockName("AAN");
	}
	
	@Test
	public void getRowTest() throws Exception {
		Mockito.when(repo.findAll()).thenReturn(new ArrayList<Trades>() {{ add(expected); }});
		List<Trades> result = service.getRow();
		assertEquals(true, expected.equals(result.get(0)));
	}
	
	@Test
	public void createRowTest() throws Exception {
		Mockito.when(repo.save(Mockito.any())).thenReturn(expected);
		Map<String, Object> result = service.createRow(trade);
		assertEquals(1l, result.get("tradeId"));
	}
	
	@Test(expected = Exception.class)
	public void updateRow() throws Exception {
		Mockito.when(repo.existsById(Mockito.anyLong())).thenReturn(false);
		service.updateRow(2l, trade);
	}
	
	@Test
	public void deleteRow() throws Exception {
		Mockito.when(repo.existsById(Mockito.anyLong())).thenReturn(true);
		Mockito.doNothing().when(repo).deleteById(1l);
		service.deleteRow(1l);
	}
	

}
