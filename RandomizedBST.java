class RandomizedBST implements TaxEvasionInterface {
    class TreeNode {
        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int N;

        public int key() {
            return item.key();
        }
    }

    private TreeNode root;

    public void insert(LargeDepositor elem) {
        if (root == null) {
            root.item = elem;
        }
        int res = compareRoot(elem);
        if (res == 0) {
            
            return;
        }

    }

    public int compareRoot(LargeDepositor elem) {
        if (root.key() < elem.key()) {
            return -1;
        } else if (root.key() > elem.key()) {
            return 1;
        }
        return 0;
    }

}