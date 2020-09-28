package InputOutput;

import jkind.lustre.NamedType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class takes Daikon's invariants and translates it to a lustre form string.
 */


public class TranslateDaikonInv {

    public static String benchmark = null;

    public static FileWriter fw;
    public static BufferedWriter bw;
    public static PrintWriter out;

    public static void main(String[] args) throws IOException {

        assert (args.length == 2) : "a file that contains Daikon's invariants must be passed as well as the benchmark name";
        Path path = Paths.get(args[0]);
        benchmark = args[1];
        fw = new FileWriter("LustreFromDaikonProp_" + benchmark, false);
        bw = new BufferedWriter(fw);
        out = new PrintWriter(bw);


        if (!Files.exists(path)) { // prop was not executed in the run.
            System.out.println("prop file not found, unexpected. aborting");
            assert false;
        }

        String content = readFile(path, StandardCharsets.US_ASCII);

        ArrayList<String> invariants = new ArrayList<String>(Arrays.asList(content.split("\n")));
        SpecInOutManager specInOutMgr = new SpecInOutManager();
        specInOutMgr.discoverVars();

        ArrayList<String> lustreInv = new ArrayList<>();
        for (String inv : invariants) {
            inv = inv.replaceAll("this.", "");
            if (!inv.contains("old("))
                lustreInv.add(inv);
            else
                lustreInv.add(toLustre(inv, specInOutMgr));
        }

        for (String inv : lustreInv) {
            out.println(inv);
        }

        out.close();
    }

    /**
     * returns a pre expression only for the ids that are in the input and the output otherwise it returns the same id.
     *
     * @param id
     * @param specInOutMgr
     * @return
     */
    private static String toLustre(String id, SpecInOutManager specInOutMgr) {
        String idInOld = id.substring(id.indexOf("old") + 4, id.length() - 1);
        NamedType type = specInOutMgr.getTypeForName(idInOld);

        if (type != null)
            if (type == NamedType.INT)
                return "(0 -> pre " + idInOld + ")";
            else if (type == NamedType.BOOL)
                return "(false -> pre " + idInOld + ")";
            else assert false : "unexpected type";
        return id.replaceAll("\\\\old","");
    }


    static String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }
}
