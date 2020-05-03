import java.util.ArrayList;
import java.util.List;

public class OrigPropRelationResult {
    int equivCount = 0;
    int looseCount = 0;
    int tightCount = 0;
    int incomparableCount = 0;
    int tautologyCount = 0;
    List<String> equivProps = new ArrayList<>();
    List<String> tightProps = new ArrayList<>();
    List<String> inComparableProps = new ArrayList<>();


    String benchmark;
    String origProp;

    public OrigPropRelationResult(String benchmark, String origProp) {
        this.benchmark = benchmark;
        this.origProp = origProp;
    }

    public String toString() {
        return ("bench,      OriginalPropName,      tautology,      equiv#,      loose#,      tight#,      incomparable#\n")
                + (benchmark + ",      " + origProp + ",      " + tautologyCount + ",      " + equivCount + ",      " + looseCount + ",      " + tightCount + ",      " + incomparableCount)
                + ("\ntightProps are:" + tightProps)
                + ("\nequivProps are:" + equivProps)
                + ("\ninComparable Props are:" + inComparableProps);
    }

    public OtherPropRelationResult makeOtherPropRelationResult(){
        return new OtherPropRelationResult(benchmark, origProp, origProp, tightCount, tightProps);
    }
}
