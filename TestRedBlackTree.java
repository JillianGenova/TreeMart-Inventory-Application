// --== CS400 File Header Information ==--
// Name: Jillian Genova
// Email: jegenova@wisc.edu
// Team: Blue
// Group: KE
// TA: Keren
// Lecturer: Gary Dahl
// Notes to Grader: Just a warning, I am slightly right/ left dyslexic so I may have 
// 					mislabel R / L in my comments but the code functions correctly

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class from which my four distinct test cases will be implemented. Tests the
 * functionality of the insert method to ensure the tree's level order traversal 
 * does not violate any RB Tree properties.
 * 
 * @author genova.jill
 *
 */
public class TestRedBlackTree {
	
	/**
	 * Case where the node to insert has a red parent with a black sibling 
	 * and the node being inserted is on the opposite side of the 
	 * sibling ("case 1"). Tests with both null, right, and left siblings
	 */
	@Test
	public void testCase1() {
		// insertion value on the right of the tree
		RedBlackTree tree = new RedBlackTree<Integer>();
		tree.insert(19);
	    tree.insert(67);  
	    tree.insert(70); // case 1 value
	    // test case when parent's sibling is null (right side insert)
	    assertEquals("[67, 19, 70]", tree.root.toString());
	    
	    // insertion value on the left side of the tree
	    RedBlackTree tree2 = new RedBlackTree<Integer>();
		tree2.insert(19);
	    tree2.insert(12);
	    tree2.insert(10); // case 1 value
	    // test case when parent's sibling is null (left side insert)
	    assertEquals("[12, 10, 19]", tree2.root.toString());
	    
	    // ensures in order traversals are correct
	    assertEquals("[ 19, 67, 70 ]", tree.toString(), tree.toString());
	    assertEquals("[ 10, 12, 19 ]", tree2.toString(), tree2.toString());
	    
	}
	
	/**
	 * Case where the node to insert has a red parent with a red 
	 * sibling (Case 3). Tests both left, right, and middle cases.
	 */
	@Test
	public void testRedSibling() {
		RedBlackTree tree = new RedBlackTree<Integer>();
		tree.insert(19);
	    tree.insert(67);
	    tree.insert(70);
	    
	    // ensuring functionality of built tree
	    assertEquals("[67, 19, 70]", tree.root.toString()); 
	    
	    tree.insert(3); // insert on right
	    assertEquals("[67, 19, 70, 3]", tree.root.toString());
	    
	    tree.insert(89); // insert on left
	    assertEquals("[67, 19, 70, 3, 89]", tree.root.toString());
	    
	    tree.insert(21); // insert in middle
	    assertEquals("[67, 19, 70, 3, 21, 89]", tree.root.toString());
	}
	
	/**
	 * Case where the node to insert has a red parent with a black sibling 
	 * and the node being inserted is on the same side of the 
	 * sibling ("case 2"). Tests with both null, right, and left siblings
	 */
	@Test
	public void test3() {
		// insertion value on the right of the tree
		RedBlackTree tree = new RedBlackTree<Integer>();
		tree.insert(19);
	    tree.insert(67);
	    tree.insert(59); // case 2 value
	    // test case when parent's sibling is null (right side insert)
	    assertEquals("[59, 19, 67]", tree.root.toString());
	    
	    // insertion value on the left side of the tree
	    RedBlackTree tree2 = new RedBlackTree<Integer>();
		tree2.insert(19);
	    tree2.insert(12);
	    tree2.insert(15); // case 1 value
	    // test case when parent's sibling is null (left side insert)
	    assertEquals("[15, 12, 19]", tree2.root.toString());
	    
	    // ensures in order traversals are correct
	    assertEquals("[ 19, 59, 67 ]", tree.toString(), tree.toString());
	    assertEquals("[ 12, 15, 19 ]", tree2.toString(), tree2.toString());
	}
	
	/**
	 * Testing final case where inserting a red node under a black node
	 * on both sides. Ensure no rotations occur and the implement properties
	 * method does not execute any changes to the method.
	 */
	@Test
	public void test4() {
		RedBlackTree tree = new RedBlackTree<Integer>();
		tree.insert(19);
	    tree.insert(67);
	    tree.insert(7);
	    assertEquals("[19, 7, 67]", tree.root.toString());
	}

}
