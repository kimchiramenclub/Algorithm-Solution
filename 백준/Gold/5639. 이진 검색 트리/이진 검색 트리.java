import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void preorder(Node tree) throws IOException {
        String input;
        int num;

        while((input = br.readLine()) != null && !input.equals("")){
            num = Integer.parseInt(input);
            tree.insert(num);
        }
    }

    static void postorder(Node tree){
        if(tree == null) return;

        postorder(tree.left);
        postorder(tree.right);
        sb.append(tree.num).append('\n');
    }

    public static void main(String[] args) throws IOException {
        String start = br.readLine();
        Node tree = new Node(Integer.parseInt(start));

        preorder(tree);
        postorder(tree);
        System.out.println(sb);
    }

    static class Node {
        int num;
        Node left, right;

        Node(int num) {
            this.num = num;
        }

        void insert(int num) {
            if (num < this.num) {
                if (this.left == null) this.left = new Node(num);
                else this.left.insert(num);
            }
            else{
                if(this.right == null) this.right = new Node(num);
                else this.right.insert(num);
            }
        }
    }
}
