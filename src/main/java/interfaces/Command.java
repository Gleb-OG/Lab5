package interfaces;

import data.Organization;

import java.util.TreeMap;

public interface Command {
    void execute(TreeMap<Integer, Organization> collection, String[] args) throws Exception;
}
