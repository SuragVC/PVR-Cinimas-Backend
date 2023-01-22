package com.pvr.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pvr.entity.PrimaryKeyGenerator;
import com.pvr.repository.PrimaryKeyGeneratorDAO;

@Component
public class GeneratePrimaryKey {
	private PrimaryKeyGeneratorDAO primarykeyDao;
	@Autowired
	public GeneratePrimaryKey (PrimaryKeyGeneratorDAO primarykeyDao) {
	this.primarykeyDao=primarykeyDao;
	}
	public Long generatePrimaryKey() {
		Optional<PrimaryKeyGenerator> key= primarykeyDao.findById(1L);
		Long value=key.get().getNextValue();
		Long nextValue=value+1;
		key.get().setNextValue(nextValue);
		primarykeyDao.save(key.get());
		return value;
	}
	
}
