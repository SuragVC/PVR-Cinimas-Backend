package com.pvr.util;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pvr.entity.PrimaryKeyGenerator;
import com.pvr.repository.PrimaryKeyGeneratorDAO;

@Component
public class PrimaryKeyInitializer {
	private PrimaryKeyGeneratorDAO primaryKeyGeneratorDao;
	@Autowired
	public  PrimaryKeyInitializer(PrimaryKeyGeneratorDAO primaryKeyGeneratorDao) {
		this.primaryKeyGeneratorDao=primaryKeyGeneratorDao;
	}
	@PostConstruct
    public void init() {
		Optional<PrimaryKeyGenerator> keyObjectOpt=primaryKeyGeneratorDao.findById(1L);
		if(keyObjectOpt.isEmpty()) {
			Long id=(long)1;
			Long key=(long)1;
			PrimaryKeyGenerator keyObject=new PrimaryKeyGenerator(id,key);
			primaryKeyGeneratorDao.save(keyObject);
		}
    }
}

