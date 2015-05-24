package treehou.se.habit.ui.settings.subsettings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import treehou.se.habit.R;
import treehou.se.habit.core.settings.NotificationSettings;

public class NotificationsSettingsFragment extends Fragment {


    // TODO: Rename and change types and number of parameters
    public static NotificationsSettingsFragment newInstance() {
        NotificationsSettingsFragment fragment = new NotificationsSettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NotificationsSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        final NotificationSettings settings = NotificationSettings.loadGlobal(getActivity());

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.settings_notification);

        CheckBox cbxNotificationToSpeech = (CheckBox) rootView.findViewById(R.id.cbx_notification_to_speech);
        cbxNotificationToSpeech.setChecked(settings.notificationToSpeach());
        cbxNotificationToSpeech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settings.setNotificationToSpeach(isChecked);
                settings.save();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }


}
