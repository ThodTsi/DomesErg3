import java.util.List;

import org.w3c.dom.Node;

import java.lang.Math;

import java.io.*;

class RandomizedBST implements TaxEvasionInterface {
    class TreeNode {
        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int n = 0;

        TreeNode(LargeDepositor item) {
            this.item = item;
        }
    }

    private TreeNode root;

    public TreeNode insert(TreeNode node, LargeDepositor item) {
        if (node == null)
            return new TreeNode(item);
        if (Math.random() * (node.n + 1) < 1.0)
            return insertAsRoot(item, node);
        if (less(item.key(), node.item.key())) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }
        node.n++;
        return node;
    }

    public TreeNode insertAsRoot(LargeDepositor item, TreeNode node) {
        if (node == null) {
            return new TreeNode(item);
        }
        if (less(item.key(), node.item.key())) {
            node.left = insertAsRoot(item, node.left);
            node = rotateRight(node);
        } // δεξιά περιστροφή για να έρθει στη ρίζα
        else {
            node.right = insertAsRoot(item, node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    }

    public TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    public TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    public boolean less(int key1, int key2) {
        if (key1 < key2) {
            return true;
        }
        return false;
    }

    public void load(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int afm = 0;
            String firstname, lastname = null;
            double savings, taxedIncome = 0.0f;
            int ch, whitespace, startIndex, endIndex = 0;
            String line = reader.readLine();
            StringBuilder currentLine = new StringBuilder();
            while (line != null) {
                line = reader.readLine();
                endIndex++;
                currentLine.append((char) ch);
                if (Character.isWhitespace((char) ch)) {
                    if (whitespace == 0) {
                        afm = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                        startIndex = endIndex;
                        whitespace++;
                    } else if (whitespace == 1) {
                        firstname = currentLine.toString().trim().substring(startIndex, endIndex - 1);
                        startIndex = endIndex;
                        whitespace++;
                    } else if (whitespace == 2) {
                        lastname = currentLine.toString().trim().substring(startIndex, endIndex - 1);
                        startIndex = endIndex;
                        whitespace++;
                    } else if (whitespace == 3) {
                        savings = Double.parseDouble(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                        startIndex = endIndex;
                        whitespace++;
                    } else if (whitespace == 4) {
                        taxedIncome = Double
                                .parseDouble(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                        startIndex = endIndex;
                        whitespace++;
                    } else if (whitespace == 5) {
                        LargeDepositor l = new LargeDepositor(afm, firstname, lastname, savings, taxedIncome);
                        insert(root, l);
                        whitespace = 0;
                        endIndex = startIndex = 0; // allazei line, jana arxikopoihsh
                        currentLine.setLength(0); // "katharizoyme to line"
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        } catch (Exception exc) {
            System.out.println("Wrong input");
            System.exit(0);
        }
    }

    public void updateSavings(int afm, double savings) {
        boolean found = false;
        TreeNode n = root;
        while (found == false || n == null) {
            if (afm < n.item.key()) {
                n = n.left;
            } else if (afm > n.item.key()) {
                n = n.right;
            } else if (n.item.key() == afm) {
                n.item.setSavings(savings);
                found = true;
            }
        }
        if (found == false) {
            System.out.println("This afm doesn't exist");
        }
    }

    public LargeDepositor searchByAFM(int afm) {
        boolean found = false;
        TreeNode n = root;
        while (found == false || n == null) {
            if (afm < root.item.key()) {
                n = n.left;
            } else if (afm > n.item.key()) {
                n = n.right;
            } else if (afm == n.item.key()) {
                found = true;
                System.out.println(n.item.toString());
            }
        }
        if (found == false) {
            System.out.println("This afm doesn't exist");
        }
    }

    public DepList searchByLastName(String last_name) {
        DepList list = new DepList();
        if (root == null) {
            System.out.println("There is no such last name");
            return list;
        }
        TreeNode p = root;
        if (p.item.getLastName() == last_name) {
            Node n = new Node(p.item);
            list.insert();

        }

    }

    TreeNode partR(TreeNode n, int k) {
        // int t = (n.left == null) ? 0 : n.left.n;
        int t = 0;
        if (n.left == null) {
            t = 0;
        } else {
            t = n.left.n;
        }
        if (t > k) {
            n.left = partR(n.left, k);
            n = rotateRight(n);
        }
        if (t < k) {
            n.right = partR(n.right, k - t - 1);
            n = rotateLeft(n);
        }
        return n;
    }

    public void remove(int afm) {
        TreeNode p = root;
        boolean found = false;
        while (found == false || p == null) {
            if (afm < p.item.key()) {
                p = p.left;
            } else if (afm > p.item.key()) {
                p = p.right;
            } else if (afm == p.item.key()) {
                found = true;
                if (p.right == null) {
                    return;
                }
                p.right = partR(p.right, 0);
                p.right.left = p.left;
                return;
            }
        }
    }

    public double getMeanSavings() {

    }

    public void printΤopLargeDepositors(int k) {

    }

    public void printByAFM() {

    }

}