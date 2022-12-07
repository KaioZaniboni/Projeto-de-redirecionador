package br.com.redirect.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.redirect.entity.RedirectForm;
import br.com.redirect.repository.IFormRepository;
import br.com.redirect.utils.UUIDTimeIDGenerator;
import lombok.Getter;

@Service
public class RedirectService {
	
	@Autowired
	private IFormRepository fromRepository;
	
	@Getter
	@Value("${appName}")
	private String appName;
	
	public RedirectForm save(RedirectForm entity, String idUpdate, String customerId) {
		if(entity != null) {
			entity.setDtUpdate(ZonedDateTime.now());
			entity.setIdUpdate(idUpdate);
			entity.setCustomerId(entity.getCustomer().getId());
			if(entity.getId() != null) {
				RedirectForm entitySaved = findById((String) entity.getId(), (String) customerId);
				if(entitySaved != null)
					return fromRepository.save(entity);
			}else
				entity.setId(getNextSeriesId());
			    entity.setIdInsert(idUpdate);
			    entity.setDtInsert(ZonedDateTime.now());
			    return fromRepository.save(entity);
			    }
		return null;
	}
	
	public RedirectForm findById(String id, String customerId) {
		return fromRepository.findByIdAndCustomerId(id,customerId);
	}
	
	private String getNextSeriesId() {
		return UUIDTimeIDGenerator.getId(this.appName);
	}

}