package testing.beanbags;

import testing.Unittest;

public class BeanBagsTestRunner {
    public void run() {
        Unittest testCheckId = new TestCheckId();
        testCheckId.run();
        Unittest testConstructor = new TestConstructor();
        testConstructor.run();
        Unittest testGetId = new TestGetId();
        testGetId.run();
        Unittest testSetId = new TestSetId();
        testSetId.run();
    }
}
