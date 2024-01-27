public class LargeDepositor {
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
        return "AFM: \t" + afm + "First Name: \t" + firstName + "Last Name \t" + lastName +
                "Savings: \t" + savings + "Taxed Income: \t" + taxedIncome;
    }

    public void setSavings(s){
        this.savings = s;
    }

}
