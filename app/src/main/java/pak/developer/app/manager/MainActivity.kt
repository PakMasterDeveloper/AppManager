package pak.developer.app.manager

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pak.developer.app.managers.extensions.logD
import pak.developer.app.managers.ui.commons.utils.PermissionUtils
import pak.developer.app.managers.ui.commons.utils.PermissionUtils.permissionLauncher
import pak.developer.app.managers.ui.commons.utils.PermissionUtils.registerPermission

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerPermission {
            when (it) {
                PermissionUtils.PermissionState.Granted -> logD("Granted")
                PermissionUtils.PermissionState.Denied -> logD("Denied")
                PermissionUtils.PermissionState.PermanentlyDenied -> logD("PermanentlyDenied")
            }
        }.permissionLauncher(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }
}