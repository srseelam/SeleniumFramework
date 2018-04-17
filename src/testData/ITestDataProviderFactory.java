package testData;
 
import java.io.IOException;
 
public interface ITestDataProviderFactory {
       ITestDataProvider create() throws IOException;
}