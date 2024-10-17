import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Deque;

public class StackImplementationExerciseTest {
    static Lab se;

    @BeforeClass
    public static void setUp(){
        se = new Lab();
    }
    /**
     * A newly instantiated Stack should not be null.
     */
    @Test
    public void createStackTest(){
        Deque<String> stack = se.createNewStack();
        Assert.assertNotNull(stack);
    }
    /**
     * A newly instantiated Stack should have a size of 0.
     */
    @Test
    public void getSizeTest1(){
        Deque<String> stack = se.createNewStack();
        if(stack == null){
            Assert.fail();
        }else{
            Assert.assertEquals(se.getSize(stack), 0);
        }
    }
    /**
     * An empty stack which has had an item added to it should have a size of 1.
     */
    @Test
    public void getSizeTest2(){
        Deque<String> stack = se.createNewStack();
        if(stack == null){
            Assert.fail();
        }else{
            se.addToTopOfStack(stack, "Crime and Punishment");
            Assert.assertEquals(se.getSize(stack), 1);
        }
    }
    /**
     * An empty stack which has had two items added to it should have a size of 2.
     */
    @Test
    public void addToTopOfStackTest1(){
        Deque<String> stack = se.createNewStack();
        if(stack == null){
            Assert.fail();
        }else{
            se.addToTopOfStack(stack, "Crime and Punishment");
            se.addToTopOfStack(stack, "Finnegan's Wake");
            Assert.assertEquals(se.getSize(stack), 2);
        }
    }

    /**
     * A stack with 3 items which then has an item removed should have a size of 2. The removed item
     * should be the most recently added item.
     */
    @Test
    public void removeFromTopOfStackTest1(){
        Deque<String> stack = se.createNewStack();
        if(stack == null){
            Assert.fail();
        }else{
            se.addToTopOfStack(stack, "Crime and Punishment");
            se.addToTopOfStack(stack, "Finnegan's Wake");
            se.addToTopOfStack(stack, "Don Quixote");
            String bookName = se.removeFromTopOfStack(stack);
            Assert.assertEquals(se.getSize(stack), 2);
            Assert.assertEquals(bookName, "Don Quixote");
        }
    }
    /**
     * A stack with 3 items which then has 2 items removed should have a size of 1. The most recently
     * added item should be removed first.
     */
    @Test
    public void removeFromTopOfStackTest2(){
        Deque<String> stack = se.createNewStack();
        if(stack == null){
            Assert.fail();
        }else{
            se.addToTopOfStack(stack, "Crime and Punishment");
            se.addToTopOfStack(stack, "Finnegan's Wake");
            se.addToTopOfStack(stack, "Don Quixote");
            String bookName = se.removeFromTopOfStack(stack);
            Assert.assertEquals(bookName, "Don Quixote");
            bookName = se.removeFromTopOfStack(stack);
            Assert.assertEquals(se.getSize(stack), 1);
            Assert.assertEquals(bookName, "Finnegan's Wake");
        }
    }

    /**
     * Peeking from a stack should return the most recently added item without decreasing the size of the stack.
     */
    @Test
    public void getTopOfStackWithoutRemovingTest1(){
        Deque<String> stack = se.createNewStack();
        if(stack == null){
            Assert.fail();
        }else{
            se.addToTopOfStack(stack, "Crime and Punishment");
            se.addToTopOfStack(stack, "Finnegan's Wake");
            se.addToTopOfStack(stack, "Don Quixote");
            String bookName = se.getTopOfStackWithoutRemoving(stack);
            Assert.assertEquals(bookName, "Don Quixote");
            Assert.assertEquals(se.getSize(stack), 3);
        }
    }
}
