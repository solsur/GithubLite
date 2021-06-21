package com.getloc.githublite.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.getloc.githublite.R
import com.getloc.githublite.alarm.AlarmReceiver
import com.getloc.githublite.data.remote.response.Reminder
import com.getloc.githublite.pref.ReminderPref
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : AppCompatActivity() {

    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var reminder: Reminder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        val reminderPreference = ReminderPref(this)
        switch_one.isChecked = reminderPreference.getReminder().isReminded

        alarmReceiver = AlarmReceiver()
        switch_one.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "RepeatingAlarm", "11:14", "GithubLite Reminder")

            } else{
                saveReminder(false)
                alarmReceiver.setOffAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = ReminderPref(this)
        reminder= Reminder()
        reminder.isReminded = state
        reminderPreference.setReminder(reminder)
    }
}