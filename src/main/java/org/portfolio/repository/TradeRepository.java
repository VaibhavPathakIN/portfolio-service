package org.portfolio.repository;

import org.portfolio.model.Trades;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trades, Long>{

}
