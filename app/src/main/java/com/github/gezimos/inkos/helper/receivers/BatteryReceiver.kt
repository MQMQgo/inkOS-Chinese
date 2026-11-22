package com.github.gezimos.inkos.helper.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.github.gezimos.inkos.data.Prefs
import com.github.gezimos.inkos.helper.formatHomeDate

class BatteryReceiver(private val onBatteryUpdate: (String, String, Boolean) -> Unit = { _, _, _ -> }) : BroadcastReceiver() {

    private lateinit var prefs: Prefs

    override fun onReceive(context: Context, intent: Intent) {
        prefs = Prefs(context)
        val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL

        val batteryLevel = level * 100 / scale.toFloat()
        val batteryLevelInt = batteryLevel.toInt()

        val currentDate = context.formatHomeDate(prefs.dateFormatStyle)

        val newBatteryText = "$batteryLevelInt%"

        onBatteryUpdate(currentDate, newBatteryText, isCharging)
    }
}

