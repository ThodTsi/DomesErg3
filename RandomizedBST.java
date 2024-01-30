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
        int n = 0;

        TreeNode(LargeDepositor item) {
            this.item = item;
        }
    }

    private TreeNode root;

    public void insert(LargeDepositor item) {
        TreeNode node = new TreeNode(item);
        if (root == null) {
            root = node;
        }
        if (Math.random() * (node.n + 1) < 1.0) {
            insertAsRoot(item, node);
        }
        if (less(item.key(), node.item.key())) {
            insertAsRoot(item, node);
        } else {
            insertAsRoot(item, node);
        }
        node.n++;
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

            // Count the number of lines
            int numberOfLines = 0;
            while (r.readLine() != null) {
                numberOfLines++;
            }

            // Reset the BufferedReader
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
                if ((char) ch == '\n' || ch == -1) {
                    if (ch == -1) {
                        endIndex++; // mono gia thn teleytaia grammh
                    }
                    LargeDepositor l = new LargeDepositor(afm, firstname, lastname, savings, taxedIncome);
                    insert(l);
                    System.out.println(l.toString());
                    n++;
                    whitespace = 0;
                    endIndex = startIndex = 0; // allazei line, jana arxikopoihsh
                    currentLine.setLength(0); // "katharizoyme to line"
                    whitespace = 0; // -1 giati diavazei ena parapanw whiteSpace otan allazei line
                    if (ch == -1) { // diavaze -1 kai meta jana -1, opote to valame gia na
                        break; // mas petaei apo to loop otan teleiwnei to text, dhladh sto prwto -1
                    }
                } else {
                    endIndex++;
                    currentLine.append((char) ch);
                }

                if (Character.isWhitespace((char) ch)) {
                    System.out.println("eii");
                    if (whitespace == 0) {
                        afm = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                    } else if (whitespace == 1) {
                        firstname = currentLine.toString().trim().substring(startIndex, endIndex - 1);
                        System.out.println(
                                "Firstname raw: '" + currentLine.toString().substring(startIndex, endIndex - 1) + "'");
                    } else if (whitespace == 2) {
                        lastname = currentLine.toString().trim().substring(startIndex, endIndex - 1);
                    } else if (whitespace == 3) {
                        savings = Double
                                .parseDouble(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                    } else if (whitespace == 4) {
                        taxedIncome = Double
                                .parseDouble(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                    }
                    startIndex = endIndex;
                    whitespace++;
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
        return n.item;
    }

    public void Traversal(TreeNode root, String last_name, SingleList list) {
        if (root != null) {
            Traversal(root.left, last_name, list);

            // Perform the search logic here
            if (root.item.getLastName().equals(last_name)) {
                list.insert(root.item);
            }

            Traversal(root.right, last_name, list);
        }
    }

    public SingleList searchByLastName(String last_name) {
        SingleList list = new SingleList();
        if (root == null) {
            System.out.println("There is no such last name");
            return list;
        }

        Traversal(root, last_name, list);

        return list;
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

    public void Traversal2(TreeNode root, double s, int n) {
        if (root != null) {
            Traversal2(root.left, s, n);

            // Perform the search logic here
            s += root.item.getSavings();
            n++;

            Traversal2(root.right, s, n);
        }
    }

    public double getMeanSavings() {
        double sum = 0.0f;
        int n = 0;
        Traversal2(root, sum, n);
        return sum / n;

    }

    public void printΤopLargeDepositors(int k) {
        return;
    }

    public void printByAFM() {
        return;
    }

}