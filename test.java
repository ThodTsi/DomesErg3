public class test {
    public static void main(String[] args) {
        RandomizedBST bst = new RandomizedBST();
        bst.load("deposits.txt");
        // bst.updateSavings(111, 20);
        System.out.println(bst.root.item.toString());

        // System.out.println(bst.root.left.item.toString());
        System.out.println(bst.root.N);
        bst.printΤopLargeDepositors(3);
        // System.out.println(bst.searchByAFM(111).toString());
        // System.out.println(bst.searchByAFM(333));
        // SingleList s = bst.searchByLastName("sa");
        // System.out.println(bst.getMeanSavings());
    }
}