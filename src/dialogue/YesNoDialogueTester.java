/*package dialogue;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class YesNoDialogueTester {
	@Before
    public void setUp() throws Exception {
    }

    @Test
    public void test1() {
    	YesNoDialogue test1 = new YesNoDialogue("test.txt");
    	assertEquals("Testing start", test1.getCurrentLine());
    	test1.goToNextLine(test1.new YesNoDialogueObject(false));
    	assertEquals("Testing no", test1.getCurrentLine());
    	test1.goToNextLine(test1.new YesNoDialogueObject(false));
    	assertEquals("Testing no no", test1.getCurrentLine());
    	test1.goToNextLine(test1.new YesNoDialogueObject(true));
    	assertEquals("Testing no no yes", test1.getCurrentLine());
    }
    
    @Test
    public void test2() {
    	YesNoDialogue test2 = new YesNoDialogue("test.txt");
    	assertEquals("Testing start", test2.getCurrentLine());
    	test2.goToNextLine(test2.new YesNoDialogueObject(true));
    	assertEquals("Testing yes", test2.getCurrentLine());
    	test2.goToNextLine(test2.new YesNoDialogueObject(false));
    	assertEquals("Testing yes no", test2.getCurrentLine());
    	test2.goToNextLine(test2.new YesNoDialogueObject(true));
    	assertEquals("Testing yes no yes", test2.getCurrentLine());
    }
    
    @Test
    public void test3() {
    	YesNoDialogue test3 = new YesNoDialogue("test.txt");
    	assertEquals("Testing start", test3.getCurrentLine());
    	test3.goToNextLine(test3.new YesNoDialogueObject(false));
    	assertEquals("Testing no", test3.getCurrentLine());
    	test3.goToNextLine(test3.new YesNoDialogueObject(false));
    	assertEquals("Testing no no", test3.getCurrentLine());
    	test3.goToNextLine(test3.new YesNoDialogueObject(false));
    	assertEquals("Testing no no no", test3.getCurrentLine());
    }
}
*/