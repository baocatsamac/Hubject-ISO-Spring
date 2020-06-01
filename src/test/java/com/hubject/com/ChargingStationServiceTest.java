package com.hubject.com;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hubject.com.model.ChargingStation;
import com.hubject.com.repository.ChargingStationRepository;
import com.hubject.com.service.ChargingStationService;

@ExtendWith(SpringExtension.class)
public class ChargingStationServiceTest {

	@TestConfiguration
	static class ChargingStationTestContextConfiguration {

		@Bean
		public ChargingStationService chargingStationService() {
			return new ChargingStationService();
		}
	}

	@Autowired
	private ChargingStationService chargingStationService;

	@MockBean
	private ChargingStationRepository chargingStationRepository;

	@BeforeEach
	public void setUp() {
		ChargingStation guckelsberg = new ChargingStation("Guckelsberg", 49.261387, 7.777777, "66125");
		Mockito.when(chargingStationRepository.findById(guckelsberg.getUID())).thenReturn(Optional.of(guckelsberg));
	}

	@Test
	public void whenValidUID_thenChargingStationBeFound() {
		String UID = "Guckelsberg";
		ChargingStation found = chargingStationService.findSpecificStation(UID);

		assertThat(found.getUID()).isEqualTo(UID);
	}
}
