package treehou.se.habit.core.db.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import treehou.se.habit.Constants;

@Table(name = "NotificaitonSettings")
public class NotificationSettingsDB extends Model {

    private static final String TAG = "NotificationSettings";
    public static final String PREF_GLOBAL = "NotificationSettings";
    public static final long ID_GLOBAL = 0;

    @Column(name = "notificationToSpeach")
    private boolean notificationToSpeach;

    public NotificationSettingsDB() {
        super();
        notificationToSpeach = Constants.DEFAULT_NOTIFICATION_TO_SPEACH;
    }

    public boolean notificationToSpeach() {
        return notificationToSpeach;
    }

    public void setNotificationToSpeach(boolean notificationToSpeach) {
        this.notificationToSpeach = notificationToSpeach;
    }

    public static NotificationSettingsDB loadGlobal(Context context){

        SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCE_SERVER, Context.MODE_PRIVATE);
        long id = preferences.getLong(PREF_GLOBAL,-1);

        NotificationSettingsDB notificationSettings = null;

        if(id != -1) {
            notificationSettings = NotificationSettingsDB.load(NotificationSettingsDB.class, id);
            Log.d(TAG, "Global notification settings is " + notificationSettings);
        }

        if(notificationSettings == null) {
            Log.d(TAG, "Global notification settings is created");
            notificationSettings = new NotificationSettingsDB();
            notificationSettings.save();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(PREF_GLOBAL, notificationSettings.getId());
            editor.apply();
        }

        return notificationSettings;
    }
}
