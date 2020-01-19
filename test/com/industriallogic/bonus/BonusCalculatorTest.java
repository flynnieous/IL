// ***************************************************************************
// Copyright (c) 2014, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.bonus;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BonusCalculatorTest {
     private static final double precision = 0.001;
    private BonusCalculator bonusCalculator;

    @Before
    public void setUp() throws Exception {
        bonusCalculator = new BonusCalculator();
    }

    @Test
    public void individualNoTax() {
        BonusCalculator bonusCalculator = new BonusCalculator();
        assertEquals(10.0, bonusCalculator.individualBonus(100, 0, 10.0, 0.0), precision);
    }

    @Test
    public void individualWithTax() {
        assertEquals(8.50, bonusCalculator.individualBonus(100, 0, 10, 15), precision);
    }

    @Test
    public void individualMustBeAboveQuota() {
        assertEquals(0.0, bonusCalculator.individualBonus(100, 150, 10, 15), precision);
        assertEquals(0.0, bonusCalculator.individualBonus(100, 100, 10, 15), precision);
    }

    @Test
    public void teamNoQuota() {
        assertEquals(50.0, bonusCalculator.teamBonus(1000, 0, 5, 1), precision);
        assertEquals(20.0, bonusCalculator.teamBonus(1000, 0, 2, 1), precision);
    }

    @Test
    public void teamAboveQuota() {
        assertEquals(50.0, bonusCalculator.teamBonus(1000, 500, 10, 1), precision);
        assertEquals(25.0, bonusCalculator.teamBonus(12000, 11000, 10, 4), precision);
    }

    @Test
    public void teamBelowQuota() {
        assertEquals(0.0, bonusCalculator.teamBonus(12000, 15000, 10, 4), precision);
        assertEquals(0.0, bonusCalculator.teamBonus(12000, 12000, 10, 4), precision);
    }

    @Test
    public void teamZeroMembers() {
        assertEquals(0.0, bonusCalculator.teamBonus(12000, 11000, 10, 0), precision);
    }
}
	
