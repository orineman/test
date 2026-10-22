package com.infowave.pda.kernel.objects;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 17.03.2008
 * Time: 13:13:14
 * Paccage : com.infowave.pda.kernel.objects
 */
public class Bookmark {
    private int id;
    private String name;
    private String description;
    private String path;
    private boolean isPathIDL;

    public Bookmark() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPathIDL() {
        return isPathIDL;
    }

    public void setPathIDL(boolean pathIDL) {
        isPathIDL = pathIDL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
