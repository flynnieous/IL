// ***************************************************************************
// Copyright (c) 2013, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TagNodeTest {

	private TagNode orders;

	@Before
	public void setUp() throws Exception {
		orders = new TagNode("orders");
	}

	private void addAttributesToOrdersNode() {
		orders.addAttribute("Date", "20130204");
		orders.addAttribute("OrderCount", "453");
	}

	private TagNode createOrderWithTwoAttributes() {
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		order.addValue("carDoor");
		return order;
	}

	private void addFourOrderNodes() {
		orders.add(new TagNode("order0"));
		orders.add(new TagNode("order1"));
		orders.add(new TagNode("order2"));
		orders.add(new TagNode("order3"));
	}

	private void checkFifthOrderChildName() {
		assertEquals("fifthOrder-child", orders.children.get(4).children.get(0).getName());
	}

	private void checkNameOfFifthOrder() {
		assertEquals("fifthOrder", orders.children.get(4).getName());
	}

	private void thereAreFiveTopLevelNodes() {
		assertEquals(5, orders.children.size());
	}

	@Test
	public void fiveOrdersOneWithChild()
	{
		addFourOrderNodes();

		TagNode fifthOrder = new TagNode("fifthOrder");
		fifthOrder.add(new TagNode("fifthOrder-child"));
		orders.add(fifthOrder);

		thereAreFiveTopLevelNodes();
		checkNameOfFifthOrder();
		checkFifthOrderChildName();
	}

	@Test
	public void ordersNodeWithValue() {
		String EXPECTED_VALUE = "a value";
		orders.addValue(EXPECTED_VALUE);
		assertEquals(EXPECTED_VALUE, orders.getValue());
	}

	@Test
	public void ordersNodeWithOneOrder() {
		String expected =
		"<orders Date=\"20130204\" OrderCount=\"453\">" +
			"<order number=\"123\">" +
			"carDoor" +
			"</order>" +
		"</orders>";

		addAttributesToOrdersNode();
		orders.add(createOrderWithTwoAttributes());
		assertEquals("orders xml", expected, orders.toString());
	}

	@Test
	public void OrdersNodeWithAttributes() {
		addAttributesToOrdersNode();
		assertEquals("<orders Date=\"20130204\" OrderCount=\"453\"/>", orders.toString());

		assertEquals("20130204", orders.getAttributeNamed("Date"));
		assertEquals("453", orders.getAttributeNamed("OrderCount"));
		assertEquals(2, orders.attributes.size());
	}

	@Test
	public void emptyOrdersNode() {
		String expected = "<orders/>";
		assertEquals("orders xml", expected, orders.toString());
	}
}
