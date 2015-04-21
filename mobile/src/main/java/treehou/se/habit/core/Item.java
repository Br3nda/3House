package treehou.se.habit.core;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ibaton on 2014-10-06.
 */
@Table(name = "Items")
public class Item extends Model {

    public static final String TYPE_SWITCH  = "SwitchItem";
    public static final String TYPE_STRING  = "StringItem";
    public static final String TYPE_COLOR   = "ColorItem";
    public static final String TYPE_NUMBER  = "NumberItem";
    public static final String TYPE_CONTACT = "ContactItem";
    public static final String TYPE_ROLLERSHUTTER = "RollershutterItem";
    public static final String TYPE_GROUP   = "GroupItem";
    public static final String TYPE_DIMMER  = "DimmerItem";

    public static final String STATE_UNINITIALIZED = "Uninitialized";


    @Column(name = "Server", onDelete = Column.ForeignKeyAction.CASCADE)
    private Server server;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "state")
    private String state;

    public Item() {}

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBaseUrl(){
        try {
            URL url = new URL(link);
            return new URL(url.getProtocol(), url.getHost(), url.getPort(), "").toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public class ItemHolder{
        public List<Item> item;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", server.getName(), name, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Item))return false;

        Item item = (Item) obj;
        return type.equals(item.getType()) && name.equals(item.getName());
    }
}
