/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elias
 */
public class Deductions {
    public static double calculateSSS(double grossPay) {
        return grossPay * 0.045; // Example percentage
    }

    public static double calculatePhilHealth(double grossPay) {
        return grossPay * 0.035; // Example percentage
    }

    public static double calculatePagIbig(double grossPay) {
        return grossPay * 0.02; // Example percentage
    }

    public static double calculateWithholdingTax(double grossPay) {
        return grossPay * 0.1; // Example percentage
    }
}

