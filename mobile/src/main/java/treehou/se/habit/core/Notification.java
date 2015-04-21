package treehou.se.habit.core;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by ibaton on 2015-01-27.
 */
@Table(name = "Notification")
public class Notification extends Model {

    @Column(name = "message")
    private String message = "";

    @Column(name = "date")
    Date date;

    @Column(name = "viewed")
    boolean viewed;

    public Notification() {
    }

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getDate() {
        return new DateTime(date);
    }

    public void setDate(DateTime date) {
        this.date = date.toDate();
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
