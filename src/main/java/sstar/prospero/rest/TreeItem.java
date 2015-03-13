package sstar.prospero.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey.Tarasenko on 08.03.2015.
 */
public class TreeItem {
    private String key;
    private String title;
    private boolean folder = true;
    private boolean expanded = true;
    private List<TreeItem> children = new ArrayList<TreeItem>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItem> childeren) {
        this.children = childeren;
    }
}
