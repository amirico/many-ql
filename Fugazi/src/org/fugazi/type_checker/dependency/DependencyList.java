package org.fugazi.type_checker.dependency;

import org.fugazi.ast.expression.literal.ID;
import org.fugazi.type_checker.dependency.Dependency;

import java.util.ArrayList;
import java.util.List;

/*
    This is used instead of HashMap mapping IDs to List of dependencies.
    Necessary because using ID's as keys for a HashMap is not possible out of the box.
 */

public class DependencyList {

    private final List<Dependency> dependecies;

    public DependencyList() {
        this.dependecies = new ArrayList<Dependency>();
    }

    public List<ID> getIds() {
        List<ID> ids = new ArrayList<ID>();
        for (Dependency dependency : this.dependecies) {
            ids.add(dependency.getDependee());
        }
        return ids;
    }

    public List<ID> getIdDependencies(ID id) {
        int idx = this.indexOf(id);
        if (idx == -1) {
            return null;
        }
        return this.dependecies.get(idx).getDependants();
    }

    public List<String> getIdDependencyNames(ID id) {
        int idx = this.indexOf(id);
        if (idx == -1) {
            return null;
        }
        List<String> names = new ArrayList<String>();
        for (ID dependency : this.dependecies.get(idx).getDependants()) {
            names.add(dependency.getName());
        }
        return names;
    }

    public void addIdDependenant(ID id, ID dependant) {
        int idx = this.indexOf(id);
        if (idx == -1) {
            this.dependecies.add(new Dependency(id));
            idx = this.indexOf(id);
        }
        this.dependecies.get(idx).addDependant(dependant);
    }

    // this needed to be overridden since AST variables are different object
    // comparison can only be done on names
    private int indexOf(ID id) {
        int idx = 0;
        for (Dependency dependency : this.dependecies) {
            if (id.getName().equals(dependency.getDependee().getName())){
                return idx;
            }
            idx++;
        }
        return -1;
    }

    public String toString() {
        String returnString = "";
        for (Dependency dependency : this.dependecies) {
            returnString += "\n" + dependency.getDependee().toString()
                    + dependency.getDependants().toString();
        }
        return returnString;
    }
}
