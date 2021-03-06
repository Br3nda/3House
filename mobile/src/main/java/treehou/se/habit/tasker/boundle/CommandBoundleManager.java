package treehou.se.habit.tasker.boundle;

import android.os.Bundle;
import android.util.Log;

import treehou.se.habit.tasker.reciever.CommandReciever;
import treehou.se.habit.tasker.reciever.IFireReciever;

public class CommandBoundleManager {

    public static final String TAG = "CommandBoundleManager";

    public static final int TYPE_COMMAND = 1;

    private static final String BUNDLE_KEY_VARIABLE_REPLACE_STRINGS = "net.dinglisch.android.tasker.extras.VARIABLE_REPLACE_KEYS";

    /**
     * @param command The toast message to be displayed by the plug-in. Cannot be null.
     * @return A plug-in bundle.
     */
    public static Bundle generateCommandBundle(final long itemId, final String command) {

        Log.d(TAG, "Item " + itemId + " Command " + command);

        final Bundle result = new Bundle();
        result.putInt(IFireReciever.BUNDLE_EXTRA_TYPE, TYPE_COMMAND);
        result.putString(CommandReciever.BUNDLE_EXTRA_COMMAND, command);
        // Instruct tasker to perform variable substitutions in the command
        result.putString(BUNDLE_KEY_VARIABLE_REPLACE_STRINGS, CommandReciever.BUNDLE_EXTRA_COMMAND);
        result.putLong(CommandReciever.BUNDLE_EXTRA_ITEM, itemId);

        return result;
    }

    /**
     * Private constructor prevents instantiation
     *
     * @throws UnsupportedOperationException because this class cannot be instantiated.
     */
    private CommandBoundleManager() {
        throw new UnsupportedOperationException("This class is non-instantiable"); //$NON-NLS-1$
    }
}
