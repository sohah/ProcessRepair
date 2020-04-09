import jkind.api.JKindApi;
import jkind.api.results.JKindResult;
import org.eclipse.core.runtime.NullProgressMonitor;

import java.io.File;

public class RepairQuality {

    public static JKindResult callJkind(String fileName) {
        File file1;

        file1 = new File("../props/" + fileName);
        JKindApi api = new JKindApi();
        JKindResult result = new JKindResult("");

        api.execute(file1, result, new NullProgressMonitor());

        return result;
    }

}
