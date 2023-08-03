package pak.developer.app.managers.extensions

import android.util.Log
import pak.developer.app.managers.ui.commons.utils.MutableValue

inline fun <reified T> T.TAG(): String = MutableValue.TAG
inline fun <reified T> T.logV(msg: String) = Log.v(TAG(), msg)
inline fun <reified T> T.logD(msg: String) = Log.d(TAG(), msg)
inline fun <reified T> T.logI(msg: String) = Log.i(TAG(), msg)
inline fun <reified T> T.logW(msg: String) = Log.w(TAG(), msg)
inline fun <reified T> T.logE(msg: String) = Log.e(TAG(), msg)
inline fun <reified T> T.logException(msg: String) = Log.d(TAG(), msg)