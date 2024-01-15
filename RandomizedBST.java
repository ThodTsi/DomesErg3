import java.util.List;

class RandomizedBST implements TaxEvasionInterface {
    class TreeNode {
        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int N;

        TreeNode(LargeDepositor item){
            this.item = item;
        }
    }

    private TreeNode root;

    public void insert(LargeDepositor item) {
        if (root == null) {
            root.item = item;
        }
        int res = compareRoot(item);
        if (res == 0) {
            
            return;
        }

    }

    public TreeNode insertAsRoot(LargeDepositor item, TreeNode node){
        if(node == null){
            return new RandomizedBST(item);
        }
    }

    public int compareRoot(LargeDepositor elem) {
        if (root.item.key() < elem.key()) {
            return -1;
        } else if (root.item.key() > elem.key()) {
            return 1;
        }
        return 0;
    }

    public void load(String filename){

    }

    public void updateSavings(int AFM, double savings){

    }

    public LargeDepositor searchByAFM(int AFM){

    }

    public List searchByLastName(String last_name){

    }

    public void remove(int AFM){

    }

    public double getMeanSavings(){

    }

    public void printΤopLargeDepositors(int k){

    }

    public void printByAFM(){

    }

}