package com.motorph;

public class Deductions {

    public static double calculateSSS(double gross) {
        if (gross < 3250) return 135.00;
        if (gross < 24750) return 135 + (Math.floor((gross - 3250) / 500) * 22.50);
        return 1125.00;
    }

    public static double calculatePhilHealth(double gross) {
        return gross * 0.025;
    }

    public static double calculatePagibig(double gross) {
        return gross * 0.02;
    }

    public static double calculateWithholdingTax(double gross) {
        if (gross <= 20833) {
            return 0;
        } else if (gross <= 33333) {
            return (gross - 20833) * 0.20;
        } else if (gross <= 66667) {
            return 2500 + (gross - 33333) * 0.25;
        } else if (gross <= 166667) {
            return 10833 + (gross - 66667) * 0.30;
        } else if (gross <= 666667) {
            return 40833 + (gross - 166667) * 0.32;
        } else {
            return 20833 + (gross - 66667) * 0.35;
        }
    }

        public static double calculateNet(double gross, double sss, double philHealth, double pagibig, double withholdingTax) {
            return gross - (sss + philHealth + pagibig + withholdingTax);
        }

        public static void main(String[] args) {
            double gross = 10000;

            double sss = calculateSSS(gross);
            double philHealth = calculatePhilHealth(gross);
            double pagibig = calculatePagibig(gross);
            double withholdingTax = calculateWithholdingTax(gross);

            double net = calculateNet(gross, sss, philHealth, pagibig, withholdingTax);
            System.out.println("Net Income: " + net);
        }
    }



