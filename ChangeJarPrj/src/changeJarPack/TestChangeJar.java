package changeJarPack;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestChangeJar{

	// Testing valid constructors (along with getters)
	@Test
	public void testConstructor() {
		ChangeJar s1 = new ChangeJar(2, 3, 4, 5);	
		assertEquals (s1.getQuarters(), 2);
		assertEquals (s1.getDimes(), 3);
		assertEquals (s1.getNickels(), 4);
		assertEquals (s1.getPennies(), 5);

		ChangeJar s2 = new ChangeJar();
		assertEquals (s2.getQuarters(), 0);
		assertEquals (s2.getDimes(), 0);
		assertEquals (s2.getNickels(), 0);
		assertEquals (s2.getPennies(), 0);

		ChangeJar s3 = new ChangeJar(s1);
		assertEquals (s3.getQuarters(), 2);
		assertEquals (s3.getDimes(), 3);
		assertEquals (s3.getNickels(), 4);
		assertEquals (s3.getPennies(), 5);

		ChangeJar s4 = new ChangeJar(15, 10, 5, 0);	
		assertEquals (s4.getQuarters(), 15);
		assertEquals (s4.getDimes(), 10);
		assertEquals (s4.getNickels(), 5);
		assertEquals (s4.getPennies(), 0);

		ChangeJar s5 = new ChangeJar(s4);
		assertEquals (s5.getQuarters(), 15);
		assertEquals (s5.getDimes(), 10);
		assertEquals (s5.getNickels(), 5);
		assertEquals (s5.getPennies(), 0);
	}

	// Testing setter and getter methods
	@Test
	public void testSetters() {
		ChangeJar s1 = new ChangeJar();	
		s1.setQuarters(2);
		s1.setDimes(3);
		s1.setNickels(4);
		s1.setPennies(5);
		assertEquals (s1.getQuarters(), 2);
		assertEquals (s1.getDimes(), 3);
		assertEquals (s1.getNickels(), 4);
		assertEquals (s1.getPennies(), 5);

		ChangeJar s2 = new ChangeJar(2,4,6,8);	
		s2.setQuarters(3);
		s2.setDimes(5);
		s2.setNickels(7);
		s2.setPennies(1);
		assertEquals (s2.getQuarters(), 3);
		assertEquals (s2.getDimes(), 5);
		assertEquals (s2.getNickels(), 7);
		assertEquals (s2.getPennies(), 1);
	}	

	// testing valid takeOut
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ChangeJar.suspend(false);
		ChangeJar s1 = new ChangeJar(3,3,2,2);
		s1.takeOut(1,1,1,1);
		assertEquals (s1.getQuarters(), 2);
		assertEquals (s1.getDimes(), 2);
		assertEquals (s1.getNickels(), 1);
		assertEquals (s1.getPennies(), 1);

		ChangeJar s2 = new ChangeJar(3,3,2,2);
		s2.takeOut(3,3,2,2);
		assertEquals (s2.getQuarters(), 0);
		assertEquals (s2.getDimes(), 0);
		assertEquals (s2.getNickels(), 0);
		assertEquals (s2.getPennies(), 0);
	}

	// testing valid takeOut
	@Test
	public void testTakeOut2() {
		ChangeJar.suspend(false);
		ChangeJar s1 = new ChangeJar(5,3,4,3);
		ChangeJar s2 = s1.takeOut(1.22);

		assertEquals (s1.getQuarters(), 1);
		assertEquals (s1.getDimes(), 1);
		assertEquals (s1.getNickels(), 4);
		assertEquals (s1.getPennies(), 1);

		assertEquals (s2.getQuarters(), 4);
		assertEquals (s2.getDimes(), 2);
		assertEquals (s2.getNickels(), 0);
		assertEquals (s2.getPennies(), 2);

		ChangeJar s3 = new ChangeJar(4,4,2,2);
		ChangeJar s4 = s3.takeOut(1.52);

		assertEquals (s3.getQuarters(), 0);
		assertEquals (s3.getDimes(), 0);
		assertEquals (s3.getNickels(), 0);
		assertEquals (s3.getPennies(), 0);

		assertEquals (s4.getQuarters(), 4);
		assertEquals (s4.getDimes(), 4);
		assertEquals (s4.getNickels(), 2);
		assertEquals (s4.getPennies(), 2);

		ChangeJar s5 = new ChangeJar(4,3,2,5);
		ChangeJar s6 = s5.takeOut(1.05);

		assertEquals (s5.getQuarters(), 0);
		assertEquals (s5.getDimes(), 3);
		assertEquals (s5.getNickels(), 1);
		assertEquals (s5.getPennies(), 5);

		assertEquals (s6.getQuarters(), 4);
		assertEquals (s6.getDimes(), 0);
		assertEquals (s6.getNickels(), 1);
		assertEquals (s6.getPennies(), 0);

		ChangeJar s7 = new ChangeJar(4,3,0,5);
		ChangeJar s8 = s7.takeOut(1.05);

		assertEquals (s7.getQuarters(), 0);
		assertEquals (s7.getDimes(), 3);
		assertEquals (s7.getNickels(), 0);
		assertEquals (s7.getPennies(), 0);

		assertEquals (s8.getQuarters(), 4);
		assertEquals (s8.getDimes(), 0);
		assertEquals (s8.getNickels(), 0);
		assertEquals (s8.getPennies(), 5);

		ChangeJar s9 = new ChangeJar(4,3,0,2);
		ChangeJar s10 = s9.takeOut(1.05);

		assertEquals (s9.getQuarters(), 1);
		assertEquals (s9.getDimes(), 0);
		assertEquals (s9.getNickels(), 0);
		assertEquals (s9.getPennies(), 2);

		assertEquals (s10.getQuarters(), 3);
		assertEquals (s10.getDimes(), 3);
		assertEquals (s10.getNickels(), 0);
		assertEquals (s10.getPennies(), 0);

		ChangeJar s11 = new ChangeJar(2,10,0,5);
		ChangeJar s12 = s11.takeOut(1.05);

		assertEquals (s11.getQuarters(), 0);
		assertEquals (s11.getDimes(), 5);
		assertEquals (s11.getNickels(), 0);
		assertEquals (s11.getPennies(), 0);

		assertEquals (s12.getQuarters(), 2);
		assertEquals (s12.getDimes(), 5);
		assertEquals (s12.getNickels(), 0);
		assertEquals (s12.getPennies(), 5);

		ChangeJar s13 = new ChangeJar(1,10,0,5);
		ChangeJar s14 = s13.takeOut(1.05);

		assertEquals (s13.getQuarters(), 0);
		assertEquals (s13.getDimes(), 2);
		assertEquals (s13.getNickels(), 0);
		assertEquals (s13.getPennies(), 5);

		assertEquals (s14.getQuarters(), 1);
		assertEquals (s14.getDimes(), 8);
		assertEquals (s14.getNickels(), 0);
		assertEquals (s14.getPennies(), 0);

		ChangeJar s15 = new ChangeJar(3,0,5,52);
		ChangeJar s16 = s15.takeOut(1.52);

		assertEquals (s15.getQuarters(), 0);
		assertEquals (s15.getDimes(), 0);
		assertEquals (s15.getNickels(), 0);
		assertEquals (s15.getPennies(), 0);

		assertEquals (s16.getQuarters(), 3);
		assertEquals (s16.getDimes(), 0);
		assertEquals (s16.getNickels(), 5);
		assertEquals (s16.getPennies(), 52);
	}
	
	// Testing valid takeOut
	@Test
    public void testTakeOut3() {
        ChangeJar s1 = new ChangeJar(5,3,0,4);
        ChangeJar s2 = s1.takeOut(1.34);
        assertEquals (s1.getQuarters(), 1);
        assertEquals (s1.getDimes(), 0);
        assertEquals (s1.getNickels(), 0);
        assertEquals (s1.getPennies(), 0);

        assertEquals (s2.getQuarters(), 4);
        assertEquals (s2.getDimes(), 3);
        assertEquals (s2.getNickels(), 0);
        assertEquals (s2.getPennies(), 4);
    }

	// Testing valid takeOut
    @Test
    public void testTakeOut4() {
        ChangeJar s1 = new ChangeJar(1,30,0,4);
        ChangeJar s2 = s1.takeOut(3.04);
        assertEquals (s1.getQuarters(), 1);
        assertEquals (s1.getDimes(), 0);
        assertEquals (s1.getNickels(), 0);
        assertEquals (s1.getPennies(), 0);

        assertEquals (s2.getQuarters(),0);
        assertEquals (s2.getDimes(), 30);
        assertEquals (s2.getNickels(), 0);
        assertEquals (s2.getPennies(), 4);
    }

    // Testing valid takeOut
    @Test
    public void testTakeOut5() {
        ChangeJar s1 = new ChangeJar(1,3,0,2);
        ChangeJar s2 = s1.takeOut(0.32);
        assertEquals (s1.getQuarters(), 1);
        assertEquals (s1.getDimes(), 0);
        assertEquals (s1.getNickels(), 0);
        assertEquals (s1.getPennies(), 0);

        assertEquals (s2.getQuarters(),0);
        assertEquals (s2.getDimes(), 3);
        assertEquals (s2.getNickels(), 0);
        assertEquals (s2.getPennies(), 2);
    }

	// testing putIn for valid low numbers
	@Test
	public void testPutIn() {
		ChangeJar.suspend(false);
		ChangeJar s1 = new ChangeJar();
		s1.putIn(2,3,4,5);
		assertEquals (s1.getQuarters(), 2);
		assertEquals (s1.getDimes(), 3);
		assertEquals (s1.getNickels(), 4);
		assertEquals (s1.getPennies(), 5);

		s1.putIn(5,4,3,2);
		assertEquals (s1.getQuarters(), 7);
		assertEquals (s1.getDimes(), 7);
		assertEquals (s1.getNickels(), 7);
		assertEquals (s1.getPennies(), 7);
	}

	// testing putIn and takeOut together
	@Test
	public void testPutInTakeOut() {
		ChangeJar.suspend(false);
		ChangeJar s1 = new ChangeJar();
		s1.putIn(3,3,2,2);
		s1.takeOut(1,1,1,1);
		assertEquals (s1.getQuarters(), 2);
		assertEquals (s1.getDimes(), 2);
		assertEquals (s1.getNickels(), 1);
		assertEquals (s1.getPennies(), 1);

		ChangeJar s2 = new ChangeJar();
		s2.putIn(10,10,5,5);
		s2.takeOut(7,6,5,4);
		assertEquals (s2.getQuarters(), 3);
		assertEquals (s2.getDimes(), 4);
		assertEquals (s2.getNickels(), 0);
		assertEquals (s2.getPennies(), 1);
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual () {
		ChangeJar s1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s3 = new ChangeJar(2, 5, 4, 2);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
		assertFalse(s1.equals((Object)s2));
		assertTrue(s1.equals((Object)s3));

		ChangeJar s4 = new ChangeJar(1, 2, 3, 4);
		ChangeJar s5 = new ChangeJar(1, 2, 3, 4);
		ChangeJar s6 = new ChangeJar(2, 0, 2, 4);

		assertTrue(s4.equals(s5));
		assertFalse(s4.equals(s6));
		assertTrue(ChangeJar.equals(s4, s5));
		assertFalse(ChangeJar.equals(s4, s6));
	}

	// testing compareTo all returns
	@Test
	public void testCompareTo () {
		ChangeJar s1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar s4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(s2.compareTo(s1) == 1);
		assertTrue(s3.compareTo(s1) == -1);
		assertTrue(s1.compareTo(s4) == 0);
		assertTrue(ChangeJar.compareTo(s2, s1) == 1);
		assertTrue(ChangeJar.compareTo(s3, s1) == -1);
		assertTrue(ChangeJar.compareTo(s1, s4) == 0);
		
		ChangeJar s5 = new ChangeJar(1, 2, 3, 4);
		ChangeJar s6 = new ChangeJar(1, 2, 3, 4);
		ChangeJar s7 = new ChangeJar(2, 0, 2, 4);
		ChangeJar s8 = new ChangeJar(0, 0, 0, 65);

		assertTrue(s5.compareTo(s6) == 0);
		assertTrue(s5.compareTo(s7) == 0);
		assertTrue(s5.compareTo(s8) == -1);
		assertTrue(s8.compareTo(s5) == 1);
		assertTrue(ChangeJar.compareTo(s5, s6) == 0);
		assertTrue(ChangeJar.compareTo(s5, s7) == 0);
		assertTrue(ChangeJar.compareTo(s5, s8) == -1);
		assertTrue(ChangeJar.compareTo(s8, s5) == 1);
	}

	// load and save combined. 
	@Test
	public void testLoadSave() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);
		s1.save("file1");
		s1 = new ChangeJar();  // resets to zero
		s1.load("file1");
		assertTrue(s1.equals(s2));
		
		ChangeJar s3 = new ChangeJar(1,2,3,4);
		s3.save("file2");
		ChangeJar s4 = new ChangeJar();
		s4.load("file2");
		assertTrue(s3.equals(s4));
	}

	// testing not able to make change
	@Test
	public void testTakeOutNull() {
		ChangeJar s1 = new ChangeJar(5,0,0,0);
		ChangeJar s2 = s1.takeOut(1.24);
		assertEquals(s2, null);
		
		ChangeJar s3 = new ChangeJar(2,2,2,2);
		ChangeJar s4 = s3.takeOut(0.63);
		assertEquals(s4, null);
		
		ChangeJar s5 = new ChangeJar(4,2,0,3);
		ChangeJar s6 = new ChangeJar(1,1,1,1);
		s6 = s5.takeOut(1.05);
		assertEquals(s6, null);
	}

	@Test
	public void testMutate() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar.suspend(true);
		ChangeJar t1 = s1.takeOut(1.22);
		assertEquals (s1.getQuarters(), 6);
		assertEquals (s1.getDimes(), 5);
		assertEquals (s1.getNickels(), 4);
		assertEquals (s1.getPennies(), 2);
		assertEquals (t1, null);
		ChangeJar.suspend(false);
		t1 = s1.takeOut(1.22);
		assertEquals (s1.getQuarters(), 2);
		assertEquals (s1.getDimes(), 3);
		assertEquals (s1.getNickels(), 4);
		assertEquals (s1.getPennies(), 0);
		assertEquals (t1.getQuarters(), 4);
		assertEquals (t1.getDimes(), 2);
		assertEquals (t1.getNickels(), 0);
		assertEquals (t1.getPennies(), 2);
		
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar.suspend(true);
		s2.takeOut(1,1,1,1);
		assertEquals (s2.getQuarters(), 6);
		assertEquals (s2.getDimes(), 5);
		assertEquals (s2.getNickels(), 4);
		assertEquals (s2.getPennies(), 2);
		ChangeJar.suspend(false);
		s2.takeOut(1,1,1,1);
		assertEquals (s2.getQuarters(), 5);
		assertEquals (s2.getDimes(), 4);
		assertEquals (s2.getNickels(), 3);
		assertEquals (s2.getPennies(), 1);
		
		ChangeJar.suspend(true);
		ChangeJar s3 = new ChangeJar(6, 5, 4, 2);
		s3.takeOut(s2);
		assertEquals (s3.getQuarters(), 6);
		assertEquals (s3.getDimes(), 5);
		assertEquals (s3.getNickels(), 4);
		assertEquals (s3.getPennies(), 2);
		ChangeJar.suspend(false);
		s3.takeOut(s2);
		assertEquals (s3.getQuarters(), 1);
		assertEquals (s3.getDimes(), 1);
		assertEquals (s3.getNickels(), 1);
		assertEquals (s3.getPennies(), 1);
		
		ChangeJar.suspend(true);
		s3.putIn(s2);
		assertEquals (s3.getQuarters(), 1);
		assertEquals (s3.getDimes(), 1);
		assertEquals (s3.getNickels(), 1);
		assertEquals (s3.getPennies(), 1);
		ChangeJar.suspend(false);
		s3.putIn(s2);
		assertEquals (s3.getQuarters(), 6);
		assertEquals (s3.getDimes(), 5);
		assertEquals (s3.getNickels(), 4);
		assertEquals (s3.getPennies(), 2);
		
		ChangeJar.suspend(true);
		s3.putIn(1,1,1,1);
		assertEquals (s3.getQuarters(), 6);
		assertEquals (s3.getDimes(), 5);
		assertEquals (s3.getNickels(), 4);
		assertEquals (s3.getPennies(), 2);
		ChangeJar.suspend(false);
		s3.putIn(1,1,1,1);
		assertEquals (s3.getQuarters(), 7);
		assertEquals (s3.getDimes(), 6);
		assertEquals (s3.getNickels(), 5);
		assertEquals (s3.getPennies(), 3);
	}


	// EXCEPTION TESTING

	// testing negative number quarters, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegQuarters() {
		new ChangeJar(-3, 2, 3, 4);		
	}

	// testing negative number dimes, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegDimes() {
		new ChangeJar(1, -2, 3, 4);		
	}

	// testing negative number nickels, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegNickels() {
		new ChangeJar(1, 2, -3, 4);		
	}

	// testing negative number quarters, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegPennies() {
		new ChangeJar(1, 2, 3, -4);		
	}

	// testing multiple negative numbers, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegMultiple() {
		new ChangeJar(-3, -4, 0, 0);		
	}
	
	// testing negative number for quarters, setter
	@Test(expected = IllegalArgumentException.class)
	public void testSetterNegQuarters() {
		ChangeJar s1 = new ChangeJar();
		s1.setQuarters(-2);
	}
	
	// testing negative number for dimes, setter
	@Test(expected = IllegalArgumentException.class)
	public void testSetterNegDimes() {
		ChangeJar s1 = new ChangeJar();
		s1.setDimes(-2);
	}
	
	// testing negative number for nickels, setter
	@Test(expected = IllegalArgumentException.class)
	public void testSetterNegNickels() {
		ChangeJar s1 = new ChangeJar();
		s1.setNickels(-2);
	}
	
	// testing negative number for pennies, setter
	@Test(expected = IllegalArgumentException.class)
	public void testSetterNegPennies() {
		ChangeJar s1 = new ChangeJar();
		s1.setPennies(-2);
	}

	// testing negative number for quarters, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegQuarters() {
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(-30,2,30,2);
	}

	// testing negative number for dimes, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegDimes() {
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,-2,2,2);
	}

	// testing negative number for nickels, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegNickels() {
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,2,-2,2);
	}

	// testing negative number for pennies, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegPennies() {
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,2,2,-2);
	}

	// testing negative number for multiple, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegMultiple() {
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,-2,-2,2);
	}

	// testing negative number for quarters, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegQuarters() {
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(-1,1,1,1);
	}

	// testing negative number for dimes, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegDimes() {
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,-1,1,1);
	}

	// testing negative number for nickels, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegNickels() {
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,1,-1,1);
	}
	
	// testing negative number for pennies, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegPennies() {
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,1,1,-1);
	}

	// testing negative number for multiple, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegMultiple() {
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,1,-1,-1);
	}
	
	// testing too few quarters, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientQuarters() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(2,1,1,1);
	}
	
	// testing Insufficient dimes, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientDimes() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,2,1,1);
	}
	
	// testing Insufficient nickels, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientNickels() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,1,2,1);
	}
	
	// testing Insufficient pennies, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientPennies() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,1,1,2);
	}
	
	// testing Insufficient multiple, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientMultiple() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,2,1,2);
	}
	
	// testing Insufficient quarters, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds1() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(2,1,1,1);
		s1.takeOut(s2);
	}
	
	// testing Insufficient dimes, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds2() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(1,2,1,1);
		s1.takeOut(s2);
	}
	
	// testing Insufficient nickels, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds3() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(1,1,2,1);
		s1.takeOut(s2);
	}
	
	// testing Insufficient pennies, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds4() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(1,1,1,2);
		s1.takeOut(s2);
	}
	
	// testing Insufficient multiple, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds5() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(2,1,1,2);
		s1.takeOut(s2);
	}
	
	// testing Insufficient funds, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds6() {
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(0.42);
	}
	
	// testing Insufficient funds, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds7() {
		ChangeJar s1 = new ChangeJar(2,3,4,5);
		s1.takeOut(1.06);
	}

	// testing Insufficient funds, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds8() {
		ChangeJar s1 = new ChangeJar(2,3,4,5);
		s1.takeOut(4.76);
	}
	
	//Repeat the putIn/takeOut tests with suspend on
	// testing negative number for quarters, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegQuartersSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(-30,2,30,2);
	}

	// testing negative number for dimes, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegDimesSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,-2,2,2);
	}

	// testing negative number for nickels, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegNickelsSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,2,-2,2);
	}

	// testing negative number for pennies, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegPenniesSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,2,2,-2);
	}

	// testing negative number for multiple, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegMultipleSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s = new ChangeJar(2,3,4,5);
		s.putIn(2,-2,-2,2);
	}

	// testing negative number for quarters, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegQuartersSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(-1,1,1,1);
	}

	// testing negative number for dimes, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegDimesSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,-1,1,1);
	}

	// testing negative number for nickels, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegNickelsSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,1,-1,1);
	}
	
	// testing negative number for pennies, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegPenniesSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,1,1,-1);
	}

	// testing negative number for multiple, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegMultipleSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,2,2,2);
		s1.takeOut(1,1,-1,-1);
	}
	
	// testing too few quarters, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientQuartersSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(2,1,1,1);
	}
	
	// testing Insufficient dimes, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientDimesSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,2,1,1);
	}
	
	// testing Insufficient nickels, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientNickelsSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,1,2,1);
	}
	
	// testing Insufficient pennies, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientPenniesSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,1,1,2);
	}
	
	// testing Insufficient multiple, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientMultipleSuspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(1,2,1,2);
	}
	
	// testing Insufficient quarters, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds1Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(2,1,1,1);
		s1.takeOut(s2);
	}
	
	// testing Insufficient dimes, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds2Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(1,2,1,1);
		s1.takeOut(s2);
	}
	
	// testing Insufficient nickels, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds3Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(1,1,2,1);
		s1.takeOut(s2);
	}
	
	// testing Insufficient pennies, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds4Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(1,1,1,2);
		s1.takeOut(s2);
	}
	
	// testing Insufficient multiple, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds5Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		ChangeJar s2 = new ChangeJar(2,1,1,2);
		s1.takeOut(s2);
	}
	
	// testing Insufficient funds, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds6Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(1,1,1,1);
		s1.takeOut(0.42);
	}
	
	// testing Insufficient funds, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds7Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,3,4,5);
		s1.takeOut(1.06);
	}

	// testing Insufficient funds, takeOut
	@Test(expected = RuntimeException.class)
	public void testInsufficientFunds8Suspend() {
		ChangeJar.suspend(true);
		ChangeJar s1 = new ChangeJar(2,3,4,5);
		s1.takeOut(4.76);
	}
}
