public class LargeDepositor implements Comparable<LargeDepositor> {
    private int afm;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;
    private double score;

    int key() {
        return afm;
    }

    LargeDepositor(int afm, String firstName, String lastName, double savings, double taxedIncome) {
        this.afm = afm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
        this.score = savings - taxedIncome;
    }

    public String toString() {
        return "VAT " + afm + " \tFirst Name: " + firstName + " \tLast Name: " + lastName +
                " \tSavings: " + savings + " \tTaxed Income: " + taxedIncome + " \tScore: " + score;
    }

    public void setSavings(double s) {
        this.savings = s;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSavings() {
        return savings;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }
    

    public int compareTo(LargeDepositor d) {
        int comp = Double.compare(this.score, d.score);
        return comp;
    }
}
