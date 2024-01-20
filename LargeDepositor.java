public class LargeDepositor {
    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;

    int key() {
        return AFM;
    }

    public String toString(){
        return "AFM: \t" + AFM + "First Name: \t" + firstName + "Last Name \t" + lastName + 
                "Savings: \t" + savings + "Taxed Income: \t" + taxedIncome;
    }

}
