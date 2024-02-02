public class LargeDepositor implements Comparable<LargeDepositor>{
    private int afm;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;

    int key() {
        return afm;
    }

    LargeDepositor(int afm, String firstName, String lastName, double savings, double taxedIncome) {
        this.afm = afm;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    public String toString() {
        return "AFM: " + afm + " \tFirst Name: " + firstName + " \tLast Name: " + lastName +
                " \tSavings: " + savings + " \tTaxed Income: " + taxedIncome;
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

    public double getTaxedIncome(){
        return taxedIncome;
    }

    public int compareTo(LargeDepositor d){
        double score1 = this.savings - this.taxedIncome;
        double score2 = d.savings - d.taxedIncome;
        int comp = Double.compare(score1, score2);
        return comp;
    }
}
