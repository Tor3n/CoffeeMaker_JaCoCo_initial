package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * 
 * @author Sarah Heckman
 *
 * Extended by Mike Whalen
 *
 * Unit tests for CoffeeMaker class.
 */

public class CoffeeMakerTest extends TestCase {
	
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;
	private Recipe r5;
	private CoffeeMaker cm;
	private RecipeBook recipeBookStub;
	private Recipe [] stubRecipies; 
	
	protected void setUp() throws Exception {
		
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
		//Set up for r5 (added by MWW)
		r5 = new Recipe();
		r5.setName("Super Hot Chocolate");
		r5.setAmtChocolate("6");
		r5.setAmtCoffee("0");
		r5.setAmtMilk("1");
		r5.setAmtSugar("1");
		r5.setPrice("100");

		stubRecipies = new Recipe [] {r1, r2, r3};
		
		super.setUp();
	}
	
	
	// put your tests here!

	@Test
	public void testMakeCoffee() {
		cm.addRecipe(r1);
		//int i = cm.makeCoffee(0,100);
		assertEquals(50,cm.makeCoffee(0,100));

	}

	@Test
	public void testAddInventory() throws InventoryException {
		cm.addInventory("1","1","1","1");
	}

	@Test
	public void testcheckInventory() throws InventoryException {
		cm.addInventory("10","10","10","10");
		//String output = "Coffee: 25"+System.getProperty("line.separator")+"Milk: 25"+System.getProperty("line.separator")+"Sugar: 25"+System.getProperty("line.separator")+"Chocolate: 25"+System.getProperty("line.separator");
		String output = "Coffee: 25\nMilk: 25\nSugar: 25\nChocolate: 25\n";
		assertEquals(cm.checkInventory(), output);
	}

	@Test(expected = RecipeException.class)
	public void testBadRecipe() throws RecipeException {
		cm.addRecipe(r5);
		r5.setAmtChocolate("1");
	}

	@Test
	public void testMakeCoffee1() {
		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));
	}


	@Test
	public void testMakeCoffee2() throws InventoryException {
		cm.addInventory("10", "10", "10", "10");
		cm.addRecipe(r2);
		assertEquals(0, cm.makeCoffee(0, 75));
	}

	@Test
	public void testMakeCoffee3() {
		cm.addRecipe(r3);
		assertEquals(25, cm.makeCoffee(0, 125));
	}

	@Test
	public void testMakeCoffee4() {
		cm.addRecipe(r5);
		assertEquals(0, cm.makeCoffee(0, 100));
	}

	@Test
	public void testdelete(){
		cm.addRecipe(r1);
		cm.deleteRecipe(0);
		Recipe[] recepieees2 = cm.getRecipes();
		//System.out.println(recepieees2[2]);
		assertEquals(null,recepieees2[2]);
	}

	@Test
	public void testaddRecipe5(){
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		Recipe[] recepieees2 = cm.getRecipes();
		assertNotEquals(null,recepieees2[2]);
	}

	@Test
	public void testdeleteRecipe1() {
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		Recipe[] recepieees = cm.getRecipes();
		String a = recepieees[0].toString();
		cm.deleteRecipe(0);
		Recipe[] recepieees2 = cm.getRecipes();
		String b = recepieees[1].toString();
		//	System.out.println(b);
		assertEquals("Mocha", b);
	}


	@Test
	public void testEdit(){
		//coffeeMaker.editRecipe(0,recipe1);
		assertEquals(null, cm.editRecipe(0,r1));
	}

	@Test
	public void testMakeCoffee12() throws InventoryException {
		cm.addRecipe(r4);
		assertEquals(1, cm.makeCoffee(1, 1));
	}

	@Test
	public void testTostring() throws InventoryException {
		cm.addInventory("10", "10", "10", "10");
		cm.addRecipe(r2);
		assertNotEquals(null,cm.checkInventory());
	}

	@Test
	public void testMakeCoffee5() throws InventoryException {
		cm.addRecipe(r2);
		assertEquals(0, cm.makeCoffee(0, 0));
	}

	@Test
	public void testMakeCoffee6() throws InventoryException {
		assertEquals(100, cm.makeCoffee(0, 100));
	}

	@Test
	public void testInventory() throws InventoryException {
		cm.addRecipe(r5);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		cm.makeCoffee(0,100);
		String output = "Coffee: 15\nMilk: 14\nSugar: 14\nChocolate: 9\n";
		assertEquals(cm.checkInventory(), output);
	}

	@Test
	public void testInventory2(){
		cm.addRecipe(r5);
		cm.makeCoffee(0,100);
		String output = "Coffee: 15\nMilk: 14\nSugar: 14\nChocolate: 9\n";
		assertEquals(cm.checkInventory(), output);
		Recipe[] sa = cm.getRecipes();
		//System.out.println(sa[0]);
		assertEquals(r5.getName(), sa[0].toString());
	}

	@Test
	public void testdelete1()  {
		assertEquals(null, cm.deleteRecipe(0));
	}

	@Test
	public void testEdit2(){
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.editRecipe(0,r4);
		assertEquals(r4, cm.getRecipes()[0]);
	}

	@Test
	public void testGetRecipes123() {
		cm.addRecipe(r1);
		//cm.addRecipe(r2);
		//cm.addRecipe(r3);
		//cm.addRecipe(r4);
		//System.out.println(cm.getRecipes()[1]);
		assertEquals(null, cm.getRecipes()[1]);
	}

	@Test
	public void testGetRecipes122() {
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		//cm.addRecipe(r3);
		//cm.addRecipe(r4);
		//System.out.println(cm.getRecipes()[1]);
		assertEquals(null, cm.getRecipes()[2]);
		assertNotEquals(null, cm.getRecipes()[1]);
	}


	@Test
	public void testGetRecipes110() {
		cm.addRecipe(r1);
		cm.addRecipe(r1);
		//System.out.println(cm.getRecipes()[1]);
		assertEquals(null, cm.getRecipes()[1]);
	}

	@Test
	public void testGetRecipes111() {
		cm.addRecipe(r1);
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r2);
		//System.out.println(cm.getRecipes()[1]);
		assertEquals(null, cm.getRecipes()[2]);
	}

	@Test
	public void testGetRecipes112() throws InventoryException {
		cm.addInventory("1","1","0","1");
		//cm.checkInventory();
		String out = "Coffee: 16\n" +
				"Milk: 16\n" +
				"Sugar: 15\n" +
				"Chocolate: 16\n";
		//System.out.println(cm.checkInventory());
		assertEquals(out, cm.checkInventory());
	}

	@Test
	public void testGetRecipes113()  {
		Recipe r8 = new Recipe();
		r8.setName(null);
		cm.addRecipe(r8);
		//System.out.println(cm.checkInventory());
		assertEquals("", r8.getName());
	}

	/* @Test(expected = NumberFormatException.class)
	public void testGetRecipes114() throws NumberFormatException, RecipeException {
		Recipe r8 = new Recipe();
		//r8.setName("123");
		r8.setAmtChocolate("-1");
		//r5.setAmtCoffee("0");
		//r5.setAmtMilk("1");
		//r5.setAmtSugar("1");
		//r5.setPrice("100");
		//cm.addRecipe(r5);
		//System.out.println(cm.checkInventory());
		//assertEquals("", r8.getAmtChocolate());
	}

	 */


}
