package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO> searchSummary (String minDate, String maxDate){
		LocalDate finalDate = maxDate.isEmpty() ? LocalDate.now(ZoneId.systemDefault()) : LocalDate.parse(maxDate);
		LocalDate startDate = minDate.isEmpty() ? finalDate.minusYears(1L) : LocalDate.parse(minDate);
		return repository.searchSummary(startDate, finalDate);

	}

}
