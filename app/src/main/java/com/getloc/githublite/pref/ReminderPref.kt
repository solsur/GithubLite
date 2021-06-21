package com.getloc.githublite.pref

import android.content.Context
import com.getloc.githublite.data.remote.response.Reminder

class ReminderPref (context: Context) {

    private val preference= context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getReminder():Reminder{
        val remind = Reminder()
        remind.isReminded = preference.getBoolean(REMINDER, false)
        return remind
    }

    fun setReminder(value: Reminder){
        val edit = preference.edit()
        edit.putBoolean(REMINDER, value.isReminded)
        edit.apply()
    }

    companion object{
        const val PREFERENCE_NAME= "preference_reminder"
        private const val REMINDER = "isReminder"
    }

}