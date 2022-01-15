package com.trendfly.store.ui.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.trendfly.store.AlarmReceiver;
import com.trendfly.store.R;

public class NotificationsFragment extends Fragment {
    private PendingIntent pendingIntent;
    private AlarmManager manager;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final Button button1 = root.findViewById(R.id.button1);
        manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        if(pendingIntent!=null){
            manager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                if(pendingIntent!=null){
                    manager.cancel(pendingIntent);
                    pendingIntent.cancel();
                }
                final EditText editText = root.findViewById(R.id.editText1);
                final EditText editText2 = root.findViewById(R.id.editText2);
                String pincodes= (String) editText.getText().toString();
                String phoneNumbers= (String) editText2.getText().toString();
                //setup alaram
                Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
                alarmIntent.putExtra("pincodes", pincodes);
                alarmIntent.putExtra("phoneNumbers", phoneNumbers);
                pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);
                manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                int interval = 10000; // 10 seconds
                manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
                Toast.makeText(getActivity(), "Alarm Set", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}