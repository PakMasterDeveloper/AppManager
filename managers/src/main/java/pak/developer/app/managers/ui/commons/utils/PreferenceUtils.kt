package pak.developer.app.managers.ui.commons.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import pak.developer.app.managers.extensions.logD
import pak.developer.app.managers.extensions.logException

class PreferenceUtils private constructor() {
    companion object {
        private var preference: SharedPreferences? = null
        private var instance: PreferenceUtils? = null
        fun getInstance(context: Context): PreferenceUtils {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = PreferenceUtils()
                        preference = context.getSharedPreferences(
                            MutableValue.PREFERENCE_NAME,
                            Context.MODE_PRIVATE
                        )
                    }
                }
            }
            return instance!!
        }
    }

    fun setStringValue(key: String, value: String): Boolean {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.putString(key, value)
                editor.apply()
                true
            } else {
                logD("String value save by Key is null or empty")
                false
            }
        } catch (error: Exception) {
            logException("Save String Value Error : ${error.localizedMessage}")
            false
        }
    }

    fun getStringValue(key: String): String {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                preference!!.getString(key, "")!!
            } else {
                logD("String value read by Key is null or empty")
                ""
            }
        } catch (error: Exception) {
            logException("Read String Value Error : ${error.localizedMessage}")
            ""
        }
    }

    fun setBooleanValue(key: String, value: Boolean): Boolean {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.putBoolean(key, value)
                editor.apply()
                true
            } else {
                logD("Boolean value save by Key is null or empty")
                false
            }
        } catch (error: Exception) {
            logException("Save Boolean Value Error : ${error.localizedMessage}")
            false
        }
    }

    fun getBooleanValue(key: String): Boolean {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                preference!!.getBoolean(key, false)
            } else {
                logD("Boolean value read by Key is null or empty")
                false
            }
        } catch (error: Exception) {
            logException("Read Boolean Value Error : ${error.localizedMessage}")
            false
        }
    }


    fun setIntegerValue(key: String, value: Int): Boolean {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.putInt(key, value)
                editor.apply()
                true
            } else {
                logD("Integer value save by Key is null or empty")
                false
            }
        } catch (error: Exception) {
            logException("Save Integer Value Error : ${error.localizedMessage}")
            false
        }
    }

    fun getIntegerValue(key: String): Int {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                preference!!.getInt(key, 0)
            } else {
                logD("Integer value read by Key is null or empty")
                0
            }
        } catch (error: Exception) {
            logException("Read Integer Value Error : ${error.localizedMessage}")
            0
        }
    }

    fun setFloatValue(key: String, value: Float): Boolean {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.putFloat(key, value)
                editor.apply()
                true
            } else {
                logD("Float value save by Key is null or empty")
                false
            }
        } catch (error: Exception) {
            logException("Save Float Value Error : ${error.localizedMessage}")
            false
        }
    }

    fun getFloatValue(key: String): Float {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                preference!!.getFloat(key, 0F)
            } else {
                logD("Integer value read by Key is null or empty")
                0F
            }
        } catch (error: Exception) {
            logException("Read Integer Value Error : ${error.localizedMessage}")
            0F
        }
    }

    fun deleteSingleValue(key: String): Boolean {
        return try {
            return if (TextUtils.isEmpty(key).not()) {
                val editor: SharedPreferences.Editor = preference!!.edit()
                editor.remove(key)
                editor.apply()
                true
            } else {
                logD("delete value  by Key is null or empty")
                false
            }
        } catch (error: Exception) {
            logException(" Delete Value Error : ${error.localizedMessage}")
            false
        }
    }

    fun clearPreference(): Boolean {
        return try {
            val editor: SharedPreferences.Editor = preference!!.edit()
            editor.clear()
            editor.apply()
            true
        } catch (error: Exception) {
            logException("Save Float Value Error : ${error.localizedMessage}")
            false
        }
    }
}