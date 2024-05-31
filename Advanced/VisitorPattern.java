package Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VisitorPattern {

    public static void main(String[] args) throws FileNotFoundException {
        // solve returns the Root Advanced.Node solved with all its Children
        // who then store their own children allowing a recursive search.
        TreeNode root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();
        // accept() method of each Advanced.TreeVis subclass provides its own customised evaluation.
        // and holds its own result.
        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);
        // get result and print
        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

    public static TreeNode solve() throws FileNotFoundException {
        // read in the tree data. Return its root.
        String file = "src/main/resources/visitorTreeTest";
        Scanner scan = new Scanner(new File(file));

        int n = scan.nextInt(); //number of nodes

        // read in node values and temp store
        int[] values = new int[n+1];
        for (int i = 1; i <= n; i++) {
            values[i] = scan.nextInt();
        }
        // read in node colours and temp store
        int[] colours = new int[n+1];
        for (int i = 1; i <= n; i++) {
            colours[i] = scan.nextInt();
        }
        // read in tree edges and create a list of Nodes with their neighbours
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            if (!nodes.containsKey(u)) {
                nodes.put(u, new HashSet<>());
            }
            if (!nodes.containsKey(v)) {
                nodes.put(v, new HashSet<>());
            }
            nodes.get(u).add(v);
            nodes.get(v).add(u);
        }
        // Work through a Breadth First Search from selected Root
        // calculate Depth and so create Advanced.Node,
        Queue<TreeNode> notVisited = new LinkedList<>(); //Queue will only contain TreeNodes not Leaf.
        HashSet<Integer> visited = new HashSet<>(); //to eliminate from further evaluation.
        int depth = 0;
        // select Root at 1.
        TreeNode root = new TreeNode(1, values[1], depth, colours[1]);
        notVisited.offer(root);
        while (!notVisited.isEmpty()) {
            depth++;
            // run through all Nodes in the queue as depth + 1 below previous run
            int size = notVisited.size();
            for (int i = 0; i < size; i++) {
                TreeNode visiting = notVisited.poll();
                int id = visiting.getId();
                visited.add(id);
                Set<Integer> neighbours = nodes.get(id);
                for (Integer neighbour: neighbours) {
                    // only evaluate if not visited already. This will eliminate parents.
                    if (!visited.contains(neighbour)) {
                        Node object;
                        // if child neighbour size == 1, it is a leaf node. Note that root node has already been evaluated.
                        // otherwise it's a tree node and add it to Queue
                        if(nodes.get(neighbour).size()>1) {
                            object = new TreeNode(neighbour, values[neighbour], depth, colours[neighbour]);
                            notVisited.offer((TreeNode)object);
                        } else { //lead nodes are not added to Queue.
                            object = new TreeLeaf(neighbour, values[neighbour], depth, colours[neighbour]);
                        }
                        // add Child Advanced.Node to parent regardless of Advanced.Node or Leaf.
                        visiting.addChild(object);
                    }
                }
            }
        }
        scan.close();
        return root;
    }

}

abstract class Node {
    private int value;
    private int depth;
    private int color;

    public Node(int value, int depth, int color) {
        this.value = value;
        this.depth = depth;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }

    public int getColor() {
        return color;
    }

    // abstract method specified so that it can be specifically implemented by subclasses.
    public abstract void accept(TreeVis visitor);

}


class TreeNode extends Node {

    private int id;
    private Set<Node> children; //note that we use Advanced.Node Superclass so we can store Advanced.TreeNode & Advanced.TreeLeaf.

    public TreeNode(int id, int value, int depth, int color) {
        super(value, depth, color);
        this.id = id;
        this.children = new HashSet<>(); // new property to hold children.
    }

    // must implement accept method from Advanced.Node. Advanced.TreeNode ensures all children are visited.
     @Override
    public void accept(TreeVis visitor) {
        visitor.visitNode(this);
        for (Node child: children) {
            child.accept(visitor); //recurring throughout the entire tree
        }
    }

    // new method to add children if node has any.
    public void addChild(Node child){
        this.children.add(child); //no requirement for nodes to hold parents.
    }

    public int getId() {
        return id;
    }
}

class TreeLeaf extends Node {

    private int id;
    public TreeLeaf(int id, int value, int depth, int color) {
        super(value, depth, color);
        this.id = id;
        // leaf node does not require a children property.
    }

    @Override // leaf node does not require us visit and evaluate children.
    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }

    public int getId() {
        return id;
    }
}

abstract class TreeVis {
    // no properties are specified in this abstract class since the result TYPE differs
    // between visitors.
    public abstract int getResult(); // each subclass will implement getResult.
    public abstract void visitNode(TreeNode node); //each subclass will implement visitNode.
    public abstract void visitLeaf(TreeLeaf leaf); // each subclass will implement visitLeaf.

}

class SumInLeavesVisitor extends TreeVis {
    private int result; // new property to hold result

    public SumInLeavesVisitor() {
        this.result = 0; //initialise to 0.
    }

    @Override // get result of summing up leaf values.
    public int getResult() {
        return result;
    }

    @Override // no requirement to do anything. only visiting leaves.
    public void visitNode(TreeNode node) {
    }

    @Override // sum leaf values.
    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    long result; // new property to store result.

    public ProductOfRedNodesVisitor() {
        this.result = 1L; // initialise to 1. The product of 0 values will be 1 apparently!
    }

    @Override // return result as int but needs to be stored as long due to size.
    public int getResult() {
        return (int) result; // required to cast long to int to meet abstract blueprint
    }

    @Override // if at regular node - multiply product of values. Stored as long
    public void visitNode(TreeNode node) {
        if(node.getColor()==0){
            result = result*node.getValue() % (1000000007);
        }
    }

    @Override // if at leaf node - same - multiply product of values. Stored as long.
    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor()==0){
            result = result*leaf.getValue() % (1000000007);
        }
    }
}

class FancyVisitor extends TreeVis {
    int sumOfNode; //new property to hold sumOfNode Values
    int sumOfLeaf; //new property to store sumOfLeaf Values.

    public FancyVisitor() {
        this.sumOfNode = 0; // initialised to 0.
        this.sumOfLeaf = 0; // initialised to 0.
    }

    @Override // Advanced.FancyVisitor result is the difference of visit node and visit leaf sums
    public int getResult() {
        return Math.abs(sumOfNode-sumOfLeaf);
    }

    @Override // if at non-leaf node of even integer depth - sum value.
    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            sumOfNode += node.getValue();
        }
    }

    @Override // if at a green leaf node - sum value
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == 1) {
            sumOfLeaf += leaf.getValue();
        }
    }
}


