import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import java.lang.Math;

import java.io.*;

class RandomizedBST implements TaxEvasionInterface {
    class TreeNode {
        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int N = 0;

        TreeNode(LargeDepositor item) {
            this.item = item;
        }
    }

    protected TreeNode root;

    public void insert(LargeDepositor item) {
        root = insert(item, root);
    }

    private TreeNode insert(LargeDepositor item, TreeNode node) {
        if (node == null) {
            return new TreeNode(item);
        }
        if (item.key() == node.item.key()) {
            System.out.println("Diplo afm");
            return node;
        }
        if (Math.random() * (node.N + 1) < 1.0) {
            return insertAsRoot(item, root);
        }
        if (less(item.key(), node.item.key())) {
            node.left = insert(item, node.left);
        } else {
            node.right = insert(item, node.right);
        }

        return node;
    }

    public TreeNode insertAsRoot(LargeDepositor item, TreeNode node) {
        if (node == null) {
            return new TreeNode(item);
        }
        if (less(item.key(), node.item.key())) {
            node.left = insertAsRoot(item, node.left);
            node = rotateRight(node);
        }else {
            node.right = insertAsRoot(item, node.right);
            node = rotateLeft(node);
        }
        node.N++;

        return node;
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
            BufferedReader r = new BufferedReader(new FileReader(filename));

            int numberOfLines = 0;
            while (r.readLine() != null) {
                numberOfLines++;
            }

            r.close();
            System.out.println("Number of lines: " + numberOfLines);
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int afm = 0;
            String firstname = null, lastname = null;
            double savings = 0.0f, taxedIncome = 0.0f;
            int ch = 0, whitespace = 0, startIndex = 0, endIndex = 0, n = 0;
            StringBuilder currentLine = new StringBuilder();

            while (n < numberOfLines) {
                ch = reader.read();
                if (Character.isWhitespace((char) ch)) {
                    System.out.println("eii");
                    whitespace++;
                    if (whitespace == 1) {
                        afm = Integer.parseInt(currentLine.toString().substring(startIndex, endIndex).trim());
                        System.out.println("AFM: " + afm);
                        startIndex = endIndex;
                    } else if (whitespace == 2) {
                        firstname = currentLine.toString().substring(startIndex, endIndex).trim();
                        System.out.println(
                                "First: " + currentLine.toString().substring(startIndex, endIndex));
                        startIndex = endIndex;
                    } else if (whitespace == 3) {
                        lastname = currentLine.toString().substring(startIndex, endIndex).trim();
                        System.out.println("Last: " + lastname);
                        startIndex = endIndex;
                    } else if (whitespace == 4) {
                        savings = Double
                                .parseDouble(currentLine.toString().substring(startIndex, endIndex).trim());
                        System.out.println("Save: " + savings);
                        startIndex = endIndex;

                    }
                } else {
                    endIndex++;
                    currentLine.append((char) ch);
                }
                if ((char) ch == '\n' || ch == -1) {

                    if (ch == -1) {
                        taxedIncome = Double
                                .parseDouble(currentLine.toString().substring(startIndex, endIndex - 1).trim());
                        System.out.println("Tax: " + taxedIncome);
                    } else {
                        taxedIncome = Double
                                .parseDouble(currentLine.toString().substring(startIndex, endIndex).trim());
                        System.out.println("Tax: " + taxedIncome);

                    }
                    LargeDepositor l = new LargeDepositor(afm, firstname, lastname, savings, taxedIncome);
                    insert(l);
                    System.out.println(l.toString());
                    n++;
                    endIndex = startIndex = 0; // allazei line, jana arxikopoihsh
                    currentLine.setLength(0); // "katharizoyme to line"
                    whitespace = 0;

                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        } catch (Exception exc) {
            System.out.println("Wrong input");
            exc.printStackTrace();
            System.exit(0);
        }
    }

    public void updateSavings(int afm, double savings) {
        boolean found = false;
        TreeNode n = root;
        while (found == false && n != null) {
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
        while (found == false && n != null) {
            if (afm < n.item.key()) {
                n = n.left;
            } else if (afm > n.item.key()) {
                n = n.right;
            } else if (afm == n.item.key()) {
                System.out.println(n.item.toString());
                found = true;
                return n.item;
            }
        }
        if (found == false) {
            System.out.println("This afm doesn't exist");
        }
        return n.item;
    }

    public void Traversal(TreeNode root, String last_name, SingleList list) {
        if (root != null) {
            Traversal(root.left, last_name, list);

            // Perform the search logic here
            if (root.item.getLastName().equals(last_name)) {
                System.out.println(root.item.toString());
                list.insert(root.item);
            }

            Traversal(root.right, last_name, list);
        }
    }

    public SingleList searchByLastName(String last_name) {
        SingleList list = new SingleList();
        if (root == null) {
            System.out.println("The name does not exist");
            return list;
        }

        Traversal(root, last_name, list);
        if (list.isEmpty()) {
            System.out.println("The name does not exist");
        }
        return list;
    }

    TreeNode partR(TreeNode n, int k) {

        int t = 0;
        if (n.left == null) {
            t = 0;
        } else {
            t = n.left.N;
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
        root = remove(root, afm);
    }

    private TreeNode remove(TreeNode n, int afm) {
        if (n == null) {
            return null;
        }
        int k = n.item.key();
        if (less(afm, k)) {
            n.left = remove(n.left, afm);
        } else if (less(k, afm)) {
            n.right = remove(n.right, afm);
        } else {
            n = joinLeftRight(n.left, n.right);
        }
        return n;
    }

    public TreeNode joinLeftRight(TreeNode a, TreeNode b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }
        int c = a.N + b.N;
        if (Math.random() * c < 1.0 * a.N) {
            a.right = joinLeftRight(a.right, b);
            return a;
        } else {
            b.left = joinLeftRight(a, b.left);
            return b;
        }
    }

    public double Traversal2(TreeNode root, double s) {
        if (root != null) {
            s = Traversal2(root.left, s);
            // Perform the search logic here
            s += root.item.getSavings();

            s = Traversal2(root.right, s);
            System.out.println("s: " + s);
            return s;
        }
        return s;
    }

    private int countNodes(TreeNode n) {
        if (n == null)
            return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    public int count() {
        return countNodes(root);
    }

    public double getMeanSavings() {

        double sum = Traversal2(root, 0.0);
        int n = countNodes(root);
        // double avg = Traversal2(root, sum, n);
        if (n > 0) {
            return sum / n;
        } else {
            return 0.0;
        }

    }

    public void printΤopLargeDepositors(int k) {
        return;
    }

    public void printByAFM() {
        return;
    }

}