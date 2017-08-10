package propertycloner;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestRepository.class,
  TestConverters.class
})
public class LibTestSuite {

}
