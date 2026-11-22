package com.limelight.preferences

import android.content.Context
import android.util.AttributeSet

/**
 * 触控板灵敏度设置项：直接读写 TouchpadInputPreferences（touchpad_input/touch_sense），
 * 与串流中游戏菜单的灵敏度滑条共用同一存储，两处修改实时同步。
 */
class TrackpadSensitivityPreference(context: Context, attrs: AttributeSet) : SeekBarPreference(context, attrs) {
    override fun onSetInitialValue(defaultValue: Any?) {
        currentValue = TouchpadInputPreferences.read(context).sensitivity
    }

    override fun refreshCurrentValue() {
        currentValue = TouchpadInputPreferences.read(context).sensitivity
    }

    override fun persistInt(value: Int): Boolean {
        val values = TouchpadInputPreferences.read(context).copy(sensitivity = value.coerceIn(1, 1000))
        TouchpadInputPreferences.save(context, values)
        return true
    }
}
