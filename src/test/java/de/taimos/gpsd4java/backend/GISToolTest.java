package de.taimos.gpsd4java.backend;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.IsCloseTo.closeTo;


import org.junit.Before;
import org.junit.Test;

import de.taimos.gpsd4java.types.TPVObject;

public class GISToolTest {
	
	double baseLat		= 46.245797833;
	double baseLng		= 7.001465949;
	double baseAltitude = 400;
	double latOffsetLat = 47.145119;
	double lngOffsetLng = 8.301895;
	double altOffsetAlt = 500;
	
	TPVObject basePosition;
	TPVObject latitudeOffsetPosition;
	TPVObject longitudeOffsetPosition;
	TPVObject bothOffsetPosition;
	TPVObject altitudeOffsetPosition;
	TPVObject nullPosition;
	TPVObject nanPosition;

	@Before
	public void setupTPVObjects() {
		basePosition = new TPVObject();
		basePosition.setLatitude(baseLat);
		basePosition.setLongitude(baseLng);
		basePosition.setAltitude(baseAltitude);
		
		latitudeOffsetPosition = new TPVObject();
		latitudeOffsetPosition.setLatitude(latOffsetLat);
		latitudeOffsetPosition.setLongitude(baseLng);
		
		longitudeOffsetPosition = new TPVObject();
		longitudeOffsetPosition.setLatitude(baseLat);
		longitudeOffsetPosition.setLongitude(lngOffsetLng);
		longitudeOffsetPosition.setAltitude(baseAltitude);
		
		bothOffsetPosition = new TPVObject();
		bothOffsetPosition.setLatitude(latOffsetLat);
		bothOffsetPosition.setLongitude(lngOffsetLng);
		bothOffsetPosition.setAltitude(baseAltitude);
		
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
	
	@Test
	public void testGetDistanceTPVObjectTPVObject() {
		assertThat(GISTool.getDistance(basePosition, basePosition), is(closeTo(0, 0.0001)));
		assertThat(GISTool.getDistance(basePosition, latitudeOffsetPosition), is(closeTo(100, 0.0001)));
		assertThat(GISTool.getDistance(basePosition, longitudeOffsetPosition), is(closeTo(100, 0.0001)));
		assertThat(GISTool.getDistance(basePosition, bothOffsetPosition), is(closeTo(141.4214, 2)));
		assertThat(GISTool.getDistance(basePosition, altitudeOffsetPosition), is(closeTo(0, 0.0001)));
	}
	
	@Test
	public void testGetDistanceTPVObjectNaNValueDistanceComparaison() {
		assertThat(GISTool.getDistance(basePosition, nanPosition), is(Double.NaN));
		assertThat(GISTool.getDistance(basePosition, nanPosition) > 10, is(false));
		assertThat(GISTool.getDistance(nanPosition, basePosition) > 10, is(false));
	}

	@Test
	public void testGetDistanceDoubleDoubleDoubleDouble() {
		GISTool.getDistance(0, 0, 0, 0);
	}
}
