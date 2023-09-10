import java.io.*;

class Main {
    /*  트리
        - 이진 트리지만, 최악의 경우 index가 2^26임. array index로 이진트리 표현 X
     */

    static Node[] nodes;
    static StringBuilder sb = new StringBuilder();

    static void preorder(Node node) {
        if (node == null) return;
        sb.append(node.data); // 루트부터 뽑고, 왼쪽으로 이동하며 순서대로 뽑음
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node){
        if(node == null) return;
        inorder(node.left);                 // 왼쪽 모두 먼저 이동 후
        sb.append(node.data);   // 왼쪽 끝부터 뽑고 루트까지 이동하며 뽑고
        inorder(node.right);                // 오른쪽 노드 뽑음
    }

    static void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.data);
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        nodes = new Node[N];
        for (int i = 0; i < N; i++) nodes[i] = new Node((char)(i+65));

        for (int i = 0; i < N; i++) {
            int parentIndex = readCharByInt();
            int left = readCharByInt();
            int right = readCharByInt();
            if (left >= 0) nodes[parentIndex].left = nodes[left];
            if (right >= 0) nodes[parentIndex].right = nodes[right];
        }

        preorder(nodes[0]);
        sb.append("\n");
        inorder(nodes[0]);
        sb.append("\n");
        postorder(nodes[0]);

        System.out.println(sb);
    }

    // 입력에서 숫자를 읽는 메서드
    static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    // Char를 읽어서 A -> 0, B -> 1, ... 이렇게 변환해 return
    static int readCharByInt() throws IOException{
        int c;
        while((c = System.in.read()) <= 32);
        return c-65;
    }

    // Tree의 각 node
    static class Node {  // Char는 array의 index를 통해 표시
        char data;
        Node left;  // 왼쪽 자식 Node 정보 저장
        Node right; // 오른쪽 자식 Node 정보 저장

        // Node를 초기화 할때, 해당 Node에 알파벳 부여
        Node(char data) {
            this.data = data;
        }
    }
}