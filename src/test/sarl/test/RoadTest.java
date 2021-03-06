package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.UUID;

import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;

import road_elements.Car;
import road_elements.Road;
import road_elements.RoadObject;
import road_elements.TrafficLight;

class RoadTest {

	static Road road;
	static RoadConnection entryRoadConnection;
	static Car car, car2;
	static TrafficLight tLight;
	
	@BeforeAll
	static void setUp() {
		road = new Road(10,20,30,40,50);
		tLight = new TrafficLight(new Point1d(),null);
		car = new Car(new Point1d(),null,null);
		car2 = new Car(new Point1d(),null,null);
		road.addObject(car);
		road.addObject(car2);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void test_road_constructor() {
		if(road == null) fail("Road class doesn't exist");
		assertEquals("Should show BeginX loc", 10, road.getBeginX());
		assertEquals("Should show BeginY loc", 20, road.getBeginY());
		assertEquals("Should show EndX loc", 30, road.getEndX());
		assertEquals("Should show EndY loc", 40, road.getEndY());
		assertEquals("Should show SpeedLimit", 50, road.getSpeedLimitKMH());
		assertEquals("Should calculate Distance", (int)Math.sqrt(800), (int)road.getDistanceKilometers());
	}

	@Test
	void testAddObject() {
		assertEquals("Should be the same object", car, road.getObjectsByUUID(car.getUUID()));		
	}

	@Test
	void remove_object_should_return_object() {
		assertTrue(car == road.removeObject(car.getUUID()));
	}

	@Test
	void get_object_by_car_type_should_only_return_cars() {
		ArrayList<Car> carList = road.listOfCars();
		assertEquals("Should has 2 object", 2, carList.size());
		assertEquals("Should be a Car object returned", car.getUUID(), carList.get(0).getUUID());
	}

	@Test
	void get_front_car_distance() {
		double expectedDistance = car2.getPosition().getX() - car.getPosition().getX();
		//assertTrue(expectedDistance == road.getFrontCarDistance(car));
	}

	@Test
	void get_object_by_uuid_return_the_object() {
		assertEquals("Should be the same UUID", car, road.getObjectsByUUID(car.getUUID()));
	}
}
