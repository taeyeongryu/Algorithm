import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static class BTree{
        class Node{
            int num;
            Node left;
            Node right;
            public Node(int num, Node left, Node right) {
                this.num = num;
                this.left = left;
                this.right = right;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "num=" + num +
                        ", left=" + left +
                        ", right=" + right +
                        '}';
            }
        }
        Node root;
        public BTree(){}
        public BTree(Node root) {
            this.root = root;
        }
        //BTree에 숫자 삽입하는 행위
        public void insert(int num){
            //root가 null이면 아직 하나도 없다는 의미이다.
            if(root==null){
                root = new Node(num, null, null);
            }else{
                addNum(root, num);
            }
        }
        //아래 노드의 하위노드로 num을 추가한다는 의미이다.
        private void addNum(Node node, int num){
            //왼쪽에 넣는다.
            if(node.num>num){
                //이미 node가 있으면
                //null check
                if(node.left!=null){
                    //그 노드를 기준으로 다시 넣는다.
                    addNum(node.left, num);
                }
                //node가 없으면 바로 여기다.
                else{
                    node.left = new Node(num, null, null);
                }
            }
            //오른쪽에 넣는다.
            else{
                if (node.right != null) {
                    addNum(node.right, num);
                } else {
                    node.right = new Node(num, null, null);
                }
            }
        }

        @Override
        public String toString() {
            return "BTree{" +
                    "root=" + root +
                    '}';
        }
        public void afterPrint(Node node){
            if(node.left!=null){
                afterPrint(node.left);
            }
            if(node.right!=null){
                afterPrint(node.right);
            }
            sb.append(node.num + "\n");
        }

    }

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        BTree tree = new BTree();
        String inputStr = null;
        while (true) {
            inputStr = br.readLine();
            if (inputStr==null||inputStr.equals("")) break;
            int inputInt = Integer.parseInt(inputStr);
            tree.insert(inputInt);
        }

        tree.afterPrint(tree.root);
        System.out.println(sb.toString());
    }
}