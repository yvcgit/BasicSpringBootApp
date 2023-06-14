package com.developerslike;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(JUnit4ClassRunner.class)
@RunWith(SpringRunner.class)

public class AssertTrueTest {

	@Test
	public void testAssertTrueinBooleanvalues() {
		
		assertTrue(String.valueOf(true), true);
		assertTrue(String.valueOf(false), true);
		assertTrue("anytest",true);
		assertTrue(true);

	}
}
