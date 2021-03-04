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
import org.portfolio.model.Trades;
import org.portfolio.service.TradeService;

@RestController
@RequestMapping("/trades/")
public class TradeController {

	@Autowired
	private final TradeService service;

	public TradeController(@Autowired TradeService service) {
		this.service = service;
	}

	@ApiOperation("This handler for view all tardes")
	@GetMapping(value = "/getAll")
	@ResponseBody
	public ResponseEntity<List<Trades>> getTrade() {
		List<Trades> response = null;
		HttpStatus status = HttpStatus.OK;
		try {
			response = service.getRow();

			if (response == null) {
				throw new Exception("Trades Not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = ExceptionHandler.getStatus(e);
			return new ResponseEntity<List<Trades>>(response, status);
		}
		return new ResponseEntity<List<Trades>>(response, status);
	}

	@ApiOperation("This handler add a trade")
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createTrade(@RequestBody Trades tradeDetails) {

		Map<String, Object> response = null;
		HttpStatus status = HttpStatus.CREATED;
		try {
			response = service.createRow(tradeDetails);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler updates a trade")
	@PutMapping(value = "/update/{trade_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateLead(@PathVariable("trade_id") String tradeId,
			@RequestBody Trades tradeDetails) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			service.updateRow(Long.parseLong(tradeId), tradeDetails);
			response.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler deletes a trade")
	@DeleteMapping(value = "/delete/{trade_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteLead(@PathVariable("trade_id") String tradeId) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			boolean isDeleted = service.deleteRow(Long.parseLong(tradeId));
			response.put("status", isDeleted?"success":"Trade not deleted");
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.gotFailure(e);
			status = ExceptionHandler.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

}
