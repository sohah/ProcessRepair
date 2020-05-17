import com.sun.tools.corba.se.idl.constExpr.Or;

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
    List<String> tautProps = new ArrayList<>();
    List<String> looseProps = new ArrayList<>();


    String benchmark;
    String origProp;

    public OrigPropRelationResult(String benchmark, String origProp) {
        this.benchmark = benchmark;
        this.origProp = origProp;
    }

    public String toString() {
        if (OriginalPropTest.debug)
            return (benchmark + ",      " + origProp + ",      " + tautologyCount + ",      " + equivCount + ",      " + looseCount + ",      " + tightCount + ",      " + incomparableCount)
                    + ("\ntightProps are:" + tightProps)
                    + ("\nequivProps are:" + equivProps)
                    + ("\ninComparable Props are:" + inComparableProps);
        else
            return (benchmark + ",      " + origProp + ",      " + tautologyCount + ",      " + equivCount + ",      " + looseCount + ",      " + tightCount + ",      " + incomparableCount);
    }

    public OtherPropRelationResult makeOtherPropRelationResult() {
        return new OtherPropRelationResult(benchmark, origProp, origProp, tightCount + equivCount, tightProps);
    }

    public String tautologyToString() {
        return "tautology props for " + benchmark + ": " + origProp + " are: " + tautProps + "\nloose props are:" + looseProps;
    }
}
