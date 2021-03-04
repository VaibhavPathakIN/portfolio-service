package org.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.portfolio.exception.ExceptionHandler;
import org.portfolio.model.Portfolio;
import org.portfolio.service.PortfolioService;

@RestController
@RequestMapping("/portfolio/")
public class PorfolioController {
	
	@Autowired
	private final PortfolioService service;

	public PorfolioController(@Autowired PortfolioService service) {
		this.service = service;
	}
	
	@ApiOperation("This handler for view all portfolio")
	@GetMapping(value = "/getAll")
	@ResponseBody
	public ResponseEntity<List<Portfolio>> getPortfolio() {
		List<Portfolio> response = null;
		HttpStatus status = HttpStatus.OK;
		try {
			response = service.getRow();

			if (response == null) {
				throw new Exception("Trades Not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = ExceptionHandler.getStatus(e);
			return new ResponseEntity<List<Portfolio>>(response, status);
		}
		return new ResponseEntity<List<Portfolio>>(response, status);
	}

	@ApiOperation("This handler add a portfolio")
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createPortfolio(@RequestBody Portfolio portfolio) {

		Map<String, Object> response = null;
		HttpStatus status = HttpStatus.CREATED;
		try {
			response = service.createRow(portfolio);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler updates a portfolio")
	@PutMapping(value = "/update/{portfolio_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updatePortfolio(@PathVariable("portfolio_id") String portfolioId,
			@RequestBody Portfolio portfolio) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.updateRow(Long.parseLong(portfolioId), portfolio);
			response.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler deletes a portfolio")
	@DeleteMapping(value = "/delete/{portfolio_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deletePortfolio(@PathVariable("portfolio_id") String portfolioId) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			boolean isDeleted = service.deleteRow(Long.parseLong(portfolioId));
			response.put("status", isDeleted?"success":"Portfolio not deleted");
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	
	@ApiOperation("This handler fetches a stock price")
	@PostMapping(value = "/getPrice")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getStockPrice(@RequestBody Map<String, Object> keyData) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			response = service.getStockPrice(keyData);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

}
