package pak.developer.app.managers.extensions

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pak.developer.app.managers.enums.ColorState
import pak.developer.app.managers.ui.commons.utils.PreferenceUtils

inline fun <reified T> T.showToast(activity: Activity, msg: String) {
    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> T.showLongToast(activity: Activity, msg: String) {
    Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
}

fun View.gone() = apply {
    visibility = View.GONE
}

fun View.visible() = apply {
    visibility = View.VISIBLE
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q, lambda = 1)
inline fun <reified T> T.sdk29AndUp(onSdk29: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        onSdk29()
    } else null
}


@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R, lambda = 1)
inline fun <reified T> T.sdk30AndUp(onSdk30: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        onSdk30()
    } else null
}

inline fun <reified T> T.navigateActivity(
    activity: Activity,
    className: Class<*>,
    removeBackStack: Boolean = false,
    bundle: Bundle? = null
) {
    val activityIntent = Intent(activity, className)
    bundle?.let {
        activityIntent.putExtras(it)
    }
    activity.startActivity(activityIntent)
    if (removeBackStack) {
        activity.finish()
    }
}

inline fun <reified T> T.statusColorChanger(
    activity: Activity,
    @ColorInt colorId: Int,
    colorState: ColorState
) {
    val window = activity.window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(activity, colorId)
    val windowsController = WindowCompat.getInsetsController(window, window.decorView)
    when (colorState) {
        ColorState.WHITE_COLOR -> {
            windowsController.isAppearanceLightStatusBars =
                activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_NO
        }

        ColorState.OTHER_COLOR -> {
            windowsController.isAppearanceLightStatusBars =
                activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        }
    }

}

fun RecyclerView.setUpVertical(context: Context) = apply {
    setHasFixedSize(true)
    val manager = LinearLayoutManager(context)
    manager.orientation = LinearLayoutManager.VERTICAL
    layoutManager = manager
    val animateItem = DefaultItemAnimator()
    animateItem.supportsChangeAnimations = false
    itemAnimator = animateItem
}

fun RecyclerView.setUpHorizontal(context: Context) = apply {
    setHasFixedSize(true)
    val manager = LinearLayoutManager(context)
    manager.orientation = LinearLayoutManager.HORIZONTAL
    layoutManager = manager
    val animateItem = DefaultItemAnimator()
    animateItem.supportsChangeAnimations = false
    itemAnimator = animateItem
}

fun RecyclerView.setUpGrid(context: Context, columnCount: Int = 3) = apply {
    setHasFixedSize(true)
    val manager = GridLayoutManager(context, columnCount)
    manager.orientation = LinearLayoutManager.VERTICAL
    layoutManager = manager
    val animateItem = DefaultItemAnimator()
    animateItem.supportsChangeAnimations = false
    itemAnimator = animateItem
}

val AppCompatActivity.preferenceUtils
    get() = PreferenceUtils.getInstance(this)
val Fragment.preferenceUtils
    get() = PreferenceUtils.getInstance(requireContext())

val Context.preferenceUtils
    get() = PreferenceUtils.getInstance(this)

fun Application.orientationVertical()= apply {
    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}
fun Application.lockDarkTheme()=apply {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}
