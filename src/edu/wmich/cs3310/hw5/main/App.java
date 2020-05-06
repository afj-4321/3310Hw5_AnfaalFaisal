package edu.wmich.cs3310.hw5.main;

/**
 * PART 1 THAT WERE QUESTIONS HAVE BEEN ANSWERED IN THE SLC REPORT,
 * I also give permission for my code to be shared
 */

import java.io.*;
import java.util.ArrayList;

/**
 * Start program for whole project
 */
public class App {
    /**
     * Entry app point
     *
     * @param args contain path to input file
     */
    public static void main(String[] args) throws FileNotFoundException {
        String file = "hw5cs3310Sp2020data.txt";
        if (args.length > 0 && args[0] != null) {
            file = args[0];
        }
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader b = new BufferedReader(new FileReader(new File(file)))) {
            String readLine;
            while ((readLine = b.readLine()) != null) {
                list.add(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file2 = new File("hw5output_AnfaalFaisal.txt");
        PrintStream stream = new PrintStream(file2);
        System.setOut(stream);
        BinaryStreeImplicit bst = new BinaryStreeImplicit();
        String[] sArray;
        for (String s : list) {
            sArray = s.split(":", 2);
            switch (sArray[0].trim().toLowerCase()) {
                case "insert":
                    String[] data = sArray[1].trim().split(",");
                    bst.insert(new Mydata(data[0].trim(), Integer.parseInt(data[1].trim()), data[2].trim().charAt(0)));
                    break;
                case "delete":
                    bst.delete(new Mydata(sArray[1].trim(), 0, ' '));
                    break;
                case "postorder":
                    bst.postorderTraversal();
                    break;
                case "inorder":
                    bst.inorderTraversal();
                    break;
                case "preorder":
                    bst.preorderTraversal();
                    break;
                case "search":
                    System.out.println(bst.getElement(bst.search(new Mydata(sArray[1].trim(), 0, ' '))));
                    break;
                default:
                    break;
            }
        }
    }
}
