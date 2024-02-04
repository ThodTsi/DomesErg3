import java.lang.Math;
import java.util.InputMismatchException;
import java.util.Scanner;
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
    final double MAX_VALUE = 8000;

    public void insert(LargeDepositor item) {
        root = insert(item, root);
        root.N = countChildren(root) - 1;
    }

    private TreeNode insert(LargeDepositor item, TreeNode node) {
        if (node == null) {
            return new TreeNode(item);
        }
        if (item.key() == node.item.key()) {
            System.out.println("Diplo afm");
            return node;
        }
        if (Math.random() * (node.N + 1) < 1.0) { // tyxaia eisagwgh sthn riza
            return insertAsRoot(item, node);
        }
        if (less(item.key(), node.item.key())) {
            node.left = insert(item, node.left);
        } else {
            node.right = insert(item, node.right);
        }
        node.N = countChildren(node);

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

        n.N = leftCount + rightCount + 1;

        return n.N;
    }

    // vohthtikh
    public boolean less(int key1, int key2) {
        if (key1 < key2) {
            return true;
        }
        return false;
    }

    // idia logikh me thn prohgoymenh mas ergasia
    public void load(String filename) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            int numberOfLines = 0;
            while (r.readLine() != null) {
                numberOfLines++;
            }
            r.close();

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            // arxikopoihseis
            int afm = 0;
            String firstname = null, lastname = null;
            double savings = 0.0f, taxedIncome = 0.0f;
            int ch = 0, whitespace = 0, startIndex = 0, endIndex = 0, n = 0;
            StringBuilder currentLine = new StringBuilder();

            while (n < numberOfLines) {
                ch = reader.read();
                if (Character.isWhitespace((char) ch)) {
                    whitespace++;
                    if (whitespace == 1) {
                        afm = Integer.parseInt(currentLine.toString().substring(startIndex, endIndex).trim());
                        startIndex = endIndex;
                    } else if (whitespace == 2) {
                        firstname = currentLine.toString().substring(startIndex, endIndex).trim();
                        startIndex = endIndex;
                    } else if (whitespace == 3) {
                        lastname = currentLine.toString().substring(startIndex, endIndex).trim();
                        startIndex = endIndex;
                    } else if (whitespace == 4) {
                        savings = Double.parseDouble(currentLine.toString().substring(startIndex, endIndex).trim());
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
                    } else {
                        taxedIncome = Double.parseDouble(currentLine.toString().substring(startIndex, endIndex).trim());

                    }
                    LargeDepositor l = new LargeDepositor(afm, firstname, lastname, savings, taxedIncome);
                    insert(l);
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
            System.out.println("This VAT number doesn't exist");
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
        } else if (list.size() <= 5) {
            list.printList(); // to poly 5 ypoptoys, toys typwnei
        }
        return list;
    }

    public void remove(int afm) {
        root = remove(root, afm);
        root.N = countChildren(root) - 1;
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
        n.N = countChildren(n);
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

    // metraei toys komvoys toy dentroy
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
        DoubleQueue dq = new DoubleQueue();
        Traversal3(root, dq, k);
        NodeD h;
        for (int i = 0; i <= dq.size(); i++) {
            h = dq.head;
            while (h != null && h.next != null) {
                if (h.getData().getTaxedIncome() > MAX_VALUE && h.getData().compareTo(h.next.getData()) == -1) {
                    NodeD temp = h.next;
                    h.next = temp.next;
                    if (temp.next != null) {
                        temp.next.setPrev(h);
                    }
                    temp.prev = h.prev;
                    if (h.prev != null) {
                        h.prev.setNext(temp);
                    }
                    h.prev = temp;
                    temp.next = h;

                    if (dq.head == h) {
                        dq.head = temp;
                    }
                }

                h = h.next;
            }
        }

        int i = 0;
        NodeD he = dq.head;
        System.out.println("The " + k + " most suspected of tax evasion large depositors are: ");
        while (he != null && i < k) {
            System.out.println(he.getData().toString());
            he = he.next;
            i++;
        }

    }

    // anadromikh anazhthsh upoptoy
    public void Traversal3(TreeNode root, DoubleQueue dq, int k) {
        if (root != null) {
            Traversal3(root.left, dq, k);
            if (root.item.getTaxedIncome() < MAX_VALUE) {
                dq.addFirst(root.item);
            } else {
                dq.addLast(root.item);
            }
            Traversal3(root.right, dq, k);
        }
    }

    void printInorder(TreeNode node) {
        if (node == null){
            return;
        }

        printInorder(node.left);

        System.out.println(node.item.toString());

        printInorder(node.right);
    }

    public void printByAFM() {
        printInorder(root);
    }

    public static void main(String args[]) {
        try {
            Scanner sc = new Scanner(System.in);
            int an;
            String an1;
            String an12;
            double an2;
            double an21;
            RandomizedBST bst = new RandomizedBST();
            bst.load("deposits.txt");

            do {
                System.out.println("-------------------MENU-------------------");
                System.out.println("1. Inserst a large depositor");
                System.out.println("2. Remove a large depositor");
                System.out.println("3. Change large depositors savings");
                System.out.println("4. Show large depositors information");
                System.out.println("5. Show mean savings of all large depositors");
                System.out.println("6. Show the most suspected of tax evasion large depositors");
                System.out.println("7. Show large depositors sorted in ascending order by their VAT number");
                System.out.println("0. Exit");
                System.out.println("------------------------------------------");
                an = sc.nextInt();
                while (an < 0 || an > 7) {
                    System.out.print("Please give a correct number");
                    an = sc.nextInt();
                }

                switch ((an)) {
                    case 1:
                        System.out.print("VAT Number: ");
                        an = sc.nextInt();
                        System.out.print("First Name: ");
                        an1 = sc.next();
                        System.out.print("Last Name: ");
                        an12 = sc.next();
                        System.out.print("Savings: ");
                        an2 = sc.nextDouble();
                        System.out.print("Taxed Income: ");
                        an21 = sc.nextDouble();
                        LargeDepositor ld = new LargeDepositor(an, an1, an12, an2, an21);
                        bst.insert(ld);
                        break;

                    case 2:
                        System.out.print("Please give the VAT number of the large depositor you choose to remove: ");
                        an = sc.nextInt();
                        bst.remove(an);
                        break;

                    case 3:
                        System.out.print(
                                "Please give the Vat number of the large depositor you choose to update their savings: ");
                        an = sc.nextInt();
                        System.out.print("New savings amount: ");
                        an2 = sc.nextDouble();
                        bst.updateSavings(an, an2);
                        break;

                    case 4:
                        System.out.println("1. Search by last name");
                        System.out.println("2. Search by VAT number");
                        an = sc.nextInt();
                        while (an < 1 || an > 2) {
                            System.out.print("Please give a correct number");
                            an = sc.nextInt();
                        }

                        if (an == 1) {
                            System.out.print("Last Name: ");
                            an1 = sc.next();
                            bst.searchByLastName(an1);
                        } else {
                            System.out.print("VAT number: ");
                            an = sc.nextInt();
                            bst.searchByAFM(an);
                        }
                        break;

                    case 5:
                        System.out.println(bst.getMeanSavings());
                        break;

                    case 6:
                        System.out.print("Please give the number of how many depositors do you want to be displayed: ");
                        an = sc.nextInt();
                        bst.printΤopLargeDepositors(an);
                        break;

                    case 7:
                        bst.printByAFM();
                        break;
                }
            } while (an != 0);

            sc.close();
        } catch (InputMismatchException e) {
            System.out.println("Please give an int number ");
        } catch (NullPointerException ex) {
            System.out.println("error");
            System.exit(0);
        }
    }

}