package org.portfolio.repository;

import org.portfolio.model.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long>{

}
