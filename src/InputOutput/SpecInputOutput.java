package InputOutput;


import jkind.lustre.Equation;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.VarDecl;

import java.util.ArrayList;
import java.util.List;

public class SpecInputOutput {
    public ArrayList<Pair<String, NamedType>> varList = new ArrayList<>();


    int size = 0;

    public void add(String start_btn, NamedType type) {
        varList.add(new Pair<>(start_btn, type));
        size++;

    }

    public int indexOf(String varName) {
        return getInputNames().indexOf(varName);

    }

    public String varNameForIndex(int index) {
        return getInputNames().get(index);
    }

    public boolean contains(String varName, NamedType type) {
        for (int i = 0; i < varList.size(); i++) {
            if (varList.get(i).getFirst().equals(varName) && varList.get(i).getSecond().equals(type))
                return true;
        }
        return false;
    }

    // checks if the name of the variable exists
    public boolean hasName(String varName) {
        for (int i = 0; i < varList.size(); i++) {
            if (varList.get(i).getFirst().equals(varName))
                return true;
        }
        return false;
    }

    // checks if the name of the variable exists
    public NamedType getTypeForName(String varName) {
        for (int i = 0; i < varList.size(); i++) {
            if (varList.get(i).getFirst().equals(varName))
                return varList.get(i).getSecond();
        }
        return null;
    }

    public List<String> getInputNames() {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < varList.size(); i++) {
            names.add(varList.get(i).getFirst());
        }

        return names;
    }

    public int getSize() {
        return size;
    }


    public boolean containsBool() {
        for (int i = 0; i < varList.size(); i++)
            if (varList.get(i).getSecond() == NamedType.BOOL)
                return true;

        return false;
    }

    public ArrayList<VarDecl> generateVarDecl() {
        ArrayList<VarDecl> varDeclList = new ArrayList<>();

        for (int i = 0; i < varList.size(); i++) {
            Pair<String, NamedType> var = varList.get(i);
            varDeclList.add(new VarDecl(var.getFirst(), var.getSecond()));
        }
        return varDeclList;
    }




}
