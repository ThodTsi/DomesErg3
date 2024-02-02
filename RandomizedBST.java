//import org.w3c.dom.Node;

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
    boolean hasC = false;

    public void insert(LargeDepositor item) {
        root = insert(item, root);
        root.N = countChildren(root);
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
            return insertAsRoot(item, node);
        }
        if (less(item.key(), node.item.key())) {
            node.left = insert(item, node.left);
        } else {
            node.right = insert(item, node.right);
        }

        return node;
    }

    // eisagwgh sthn riza
    public TreeNode insertAsRoot(LargeDepositor item, TreeNode node) {
        if (node == null) {
            return new TreeNode(item);
        }
        if (less(item.key(), node.item.key())) {
            node.left = insertAsRoot(item, node.left);
            node = rotateRight(node);
        } else {
            node.right = insertAsRoot(item, node.right);
            node = rotateLeft(node);
        }

        return node;
    }

    // gia dejia peristrofh
    public TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;

        return x;
    }

    // gia aristeri peristrofh
    public TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    private int countChildren(TreeNode n) {
        if (n == null) {
            return 0;
        }

        int leftCount = countChildren(n.left);
        int rightCount = countChildren(n.right);

        n.N = leftCount + rightCount + 1; // Update N for the current node

        return n.N; // Return the total count for the subtree rooted at this node
    }

    // vohthtikh
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
            // System.out.println("Number of lines: " + numberOfLines);
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

    // anadromikh anazhthsh onomatepwnymoy sto dentro
    public void Traversal(TreeNode root, String last_name, SingleList list) {
        if (root != null) {
            Traversal(root.left, last_name, list);

            if (root.item.getLastName().equals(last_name)) {
                list.insert(root.item);
            }

            Traversal(root.right, last_name, list);
        }
    }

    public SingleList searchByLastName(String last_name) {
        SingleList list = new SingleList();
        if (root == null) {
            System.out.println("The name does not exist");
            return null;
        }

        Traversal(root, last_name, list);

        if (list.isEmpty()) {
            System.out.println("The name does not exist");
            return null;
        } else if (list.N > 5) {
            list.printList();
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
        n.N--;
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

    // anadromikh anazhthsh katathesewn sto dentro
    public double Traversal2(TreeNode root, double s) {
        if (root != null) {
            s = Traversal2(root.left, s);

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
        if (n > 0) {
            return sum / n;
        } else {
            return 0.0;
        }

    }

    public void printΤopLargeDepositors(int k) {
        PQ pq = new PQ();
        Traversal3(root, pq, k);
        System.out.println("The " + k + " most suspected of tax evasion large depositors are: ");
        for (int i = 1; i <= pq.size(); i++) {
            System.out.println(pq.heap[i].toString());
        }
    }

    // anadromikh anazhthsh upoptoy
    public void Traversal3(TreeNode root, PQ pq, int k) {
        final double MAX_VALUE = 8000;
        if (root != null) {
            Traversal3(root.left, pq, k);

            LargeDepositor max = root.item;
            if (pq.size() == k) {
                for (int i = 1; i < pq.size(); i++) {
                    if (pq.heap[i].compareTo(pq.heap[i + 1]) == 1) {
                        max = pq.heap[i];
                    }
                }
                if (root.item.getTaxedIncome() < MAX_VALUE) {
                    pq.remove(max.key());
                    pq.insert(root.item);
                }
                if (max.compareTo(root.item) == 1) {
                    pq.remove(max.key());
                    pq.insert(root.item);

                }
            } else {
                pq.insert(root.item);
            }

            Traversal3(root.right, pq, k);
        }
    }

    public void printByAFM() {
        return;
    }

}