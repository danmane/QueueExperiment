import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ ProcessorTest.class, QueueManagerTest.class, TaskTest.class })
public class AllTests {

}
