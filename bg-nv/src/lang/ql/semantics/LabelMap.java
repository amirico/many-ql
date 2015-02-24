package lang.ql.semantics;

import lang.ql.ast.statement.Question;
import lang.ql.gui.label.Label;

import java.util.*;

/**
 * Created by bore on 23/02/15.
 */
public class LabelMap
{
    private Map<String, Set<String>> labelToId;

    public LabelMap()
    {
        this.labelToId = new HashMap<String, Set<String>>();
    }

    public void registerLabel(Question q)
    {
        String label = q.getLabel();
        String id = q.getId();
        if (this.labelToId.containsKey(label))
        {
            this.labelToId.get(label).add(id);
        }
        else
        {
            Set<String> ids = new HashSet<String>();
            ids.add(id);
            this.labelToId.put(label, ids);
        }
    }

    public Set<List<String>> getDuplicateLabels()
    {
        Set<List<String>> result = new HashSet<List<String>>();
        for (Set<String> s : this.labelToId.values())
        {
            if (s.size() > 1)
            {
                List l = new ArrayList();
                l.addAll(s);
                result.add(l);
            }
        }
        return result;
    }
}