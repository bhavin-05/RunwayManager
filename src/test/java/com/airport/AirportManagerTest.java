package com.airport;

import com.airport.core.AirportManager;
import com.airport.entity.RunwayRequest.RunwayRequestType;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AirportManagerTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AirportManagerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AirportManagerTest.class );
    }

	static AirportManager manager = AirportManager.getInstance();

    /**
     * Rigourous Test :-)
     * @throws InterruptedException 
     */
    public void testApp() throws InterruptedException
    {
    	manager.createTakeOffRequest("1");
		manager.createLandingRequest("10");
		manager.createTakeOffRequest("5");
		manager.createLandingRequest("11");
		assertTrue(manager.getNextRunwayRequest().airplaneId.equals("10"));
		manager.changePriority("5", RunwayRequestType.TAKEOFF, 1);
		assertTrue(manager.getNextRunwayRequest().airplaneId.equals("5"));
		assertTrue(manager.getNextRunwayRequest().airplaneId.equals("11"));
		assertTrue(manager.getNextRunwayRequest().airplaneId.equals("1"));
    }
}
