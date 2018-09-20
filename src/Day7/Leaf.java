package com.company;

import java.util.List;
import java.util.stream.Collectors;

public class Leaf {

    public String name;
    public int weight;
    public Leaf parent;
    public List<Leaf> children;

    public long kidsWeight() {
        return children == null ? 0 :children.stream().mapToLong(k -> k.kidsWeight()+k.weight).sum();
    }

    public boolean ChildrenSameWeight(){

        return children.stream().map(child -> child.kidsWeight()).distinct().collect(Collectors.toList()).size() == 1;

    }
}
