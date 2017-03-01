package de.taimos.gpsd4java.api;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import de.taimos.gpsd4java.types.TPVObject;

public class DistanceListenerTest {

	double baseLat		= 46.245797833;
	double baseLng		= 7.001465949;
	double baseAltitude = 400;
	double latOffsetLat = 47.145119;
	double lngOffsetLng = 8.301895;
	double altOffsetAlt = 500;
	
	TPVObject basePosition;
	TPVObject offsetPosition;
	TPVObject nanAltitudePosition;
	TPVObject altitudeOffsetPosition;
	TPVObject nullPosition;
	TPVObject nanPosition;

	@Before
	public void setupTPVObjects() {
		basePosition = new TPVObject();
		basePosition.setLatitude(baseLat);
		basePosition.setLongitude(baseLng);
		basePosition.setAltitude(baseAltitude);
		
		offsetPosition = new TPVObject();
		offsetPosition.setLatitude(latOffsetLat);
		offsetPosition.setLongitude(lngOffsetLng);
		offsetPosition.setAltitude(baseAltitude);
		
		nanAltitudePosition = new TPVObject();
		nanAltitudePosition.setLatitude(baseLat);
		nanAltitudePosition.setLongitude(baseLng);
		nanAltitudePosition.setAltitude(Double.NaN);
		
		altitudeOffsetPosition = new TPVObject();
		altitudeOffsetPosition.setLatitude(baseLat);
		altitudeOffsetPosition.setLongitude(baseLng);
		altitudeOffsetPosition.setAltitude(altOffsetAlt);
		
		nullPosition = null;
		
		nanPosition = new TPVObject();
		nanPosition.setLatitude(Double.NaN);
		nanPosition.setLongitude(Double.NaN);
		nanPosition.setAltitude(Double.NaN);
	}
	
	public boolean called = false;
	public DistanceListener distanceListener;
	
	@Before
	public void setUp() {
		distanceListener = new DistanceListener(1) {
			@Override
			protected void handleLocation(TPVObject tpv) {
				called = true;
			}
		};
	}
	
	@Test
	public void testHandleTPV() {
		// appelé lors d'un changement de position
		called = false;
		distanceListener.handleTPV(basePosition);
		distanceListener.handleTPV(offsetPosition);
		assertThat(called, is(true));
		
		// non-appelé lorsque la position est la même
		called = false;
		distanceListener.handleTPV(offsetPosition);
		assertThat(called, is(false));
		
		
		called = false;
		distanceListener.handleTPV(null);
		assertThat(called, is(false));
	}
	
	
	@Test
	public void testHandleTPVWithNaNOriginalPosition() {
		// appelé pour un passage de null vers NaN
		called = false;
		distanceListener.handleTPV(nanPosition);
		assertThat(called, is(true));
		
		// appelé pour un passage de NaN vers une position valide
		called = false;
		distanceListener.handleTPV(basePosition);
		assertThat(called, is(true));
		
		// non-appelé lorsque la position redevient NaN, on garde la position d'avant
		called = false;
		distanceListener.handleTPV(nanPosition);
		assertThat(called, is(false));
		
		// non-appelé, la position d'avant a été conservé, la meme ne provoque pas de changement
		called = false;
		distanceListener.handleTPV(basePosition);
		assertThat(called, is(false));
	}
	
	@Test
	public void testHandleTPV_2D_to_3D_fix() {
		// appelé pour un passage de null vers une position, même si l'altitude est NaN
		called = false;
		distanceListener.handleTPV(nanAltitudePosition);
		assertThat(called, is(true));
		
		// appelé lorsque l'altitude devient connue
		called = false;
		distanceListener.handleTPV(basePosition);
		assertThat(called, is(true));
		
		// non appelé, si on perd l'altitude
		called = false;
		distanceListener.handleTPV(nanAltitudePosition);
		assertThat(called, is(false));
	}
}
