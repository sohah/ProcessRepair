
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class OtherPropRelationResult {
    int tightCount = 0;

    List<String> tightProps = new ArrayList<>();

    String benchmark;
    String origProp; //the prop we were repairing

    String otherOrigProp; //the prop we hit.

    public OtherPropRelationResult(String benchmark, String origProp, String otherOrigProp) {
        this.benchmark = benchmark;
        this.origProp = origProp;
        this.otherOrigProp = otherOrigProp;
    }

    public OtherPropRelationResult(String benchmark, String origProp, String otherOrigProp, int tightCount, List<String> tightProps) {
        this.benchmark = benchmark;
        this.origProp = origProp;
        this.otherOrigProp = otherOrigProp;
        this.tightCount = tightCount;
        this.tightProps = tightProps;
    }

    /*public String toString() {
        System.out.println("bench,      OriginalPropName,      tautology,      equiv#,      loose#,      tight#,      incomparable#");
        System.out.println(origProp + ",      " + origProp + ",      " + tautologyCount + ",      " + equivCount + ",      " + looseCount + ",      " + tightCount + ",      " + incomparableCount);

        System.out.println("tightProps are:" + tightProps);
        System.out.println("equivProps are:" + equivProps);
        System.out.println("inComparable Props are:" + inComparableProps);
    }*/
}
