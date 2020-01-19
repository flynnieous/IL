package com.industriallogic.bonus;

public class BonusCalculator {

    private int quotaAdjustment(int sales, int quota) {
        return sales > quota ? 1 : 0;
    }

    public double individualBonus(int sales, int quota, double commission, double taxRate) {
        double baseCommission = sales * commission / 100;
        double taxAdjustment = 1 - taxRate / 100;
        return baseCommission * taxAdjustment * (quotaAdjustment(sales, quota));
    }

    public double teamBonus(int sales, int quota, double commisson, int numberOfTeamMembers) {
        double baseCommission = (sales - quota) * commisson / 100;
        double teamSizeAdjustment = numberOfTeamMembers > 0 ? 1.0 / numberOfTeamMembers : 0;
        return baseCommission * teamSizeAdjustment * quotaAdjustment(sales, quota);
    }
}
