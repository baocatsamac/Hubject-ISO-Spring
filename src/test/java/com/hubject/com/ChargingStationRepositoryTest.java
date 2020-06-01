package com.hubject.com;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hubject.com.model.ChargingStation;
import com.hubject.com.repository.ChargingStationRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ChargingStationRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ChargingStationRepository chargingStationRepository;
	
	@Test
	public void findByUID() {
		// given
		ChargingStation guckelsberg = new ChargingStation("Guckelsberg", 49.261387, 7.777777,"66125");
		entityManager.persist(guckelsberg);
		entityManager.flush();
		
		// when 
		ChargingStation found = chargingStationRepository.findById(guckelsberg.getUID()).get();
		
		// then 
		assertThat(found.getUID()).isEqualTo(guckelsberg.getUID());
	}

}
