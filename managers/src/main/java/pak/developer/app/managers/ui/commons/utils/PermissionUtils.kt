package pak.developer.app.managers.ui.commons.utils

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.fragment.app.Fragment

object PermissionUtils {
    @JvmInline
    value class Permissions(val result: ActivityResultLauncher<Array<String>>)
    sealed class PermissionState {
        object Granted : PermissionState()
        object Denied : PermissionState()
        object PermanentlyDenied : PermissionState()
    }

    private fun getPermission(
        activity: Activity,
        permissionResult: Map<String, Boolean>
    ): PermissionState {
        val deniedList: List<String> = permissionResult.filter {
            it.value.not()
        }.map {
            it.key
        }
        var state = when (deniedList.isEmpty()) {
            true -> PermissionState.Granted
            false -> PermissionState.Denied
        }
        if (state == PermissionState.Denied) {
            val permanentlyMappedList = deniedList.map {
                shouldShowRequestPermissionRationale(activity, it)
            }
            if (permanentlyMappedList.contains(false)) {
                state = PermissionState.PermanentlyDenied
            }
        }
        return state
    }

    fun AppCompatActivity.registerPermission(permissionResult: (PermissionState) -> Unit): Permissions {
        return Permissions(registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            permissionResult(getPermission(this, it))
        })
    }

    fun Fragment.registerPermission(permissionResult: (PermissionState) -> Unit): Permissions {
        return Permissions(registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            permissionResult(getPermission(requireActivity(), it))
        })
    }

    fun Permissions.permissionLauncher(permissionList: Array<String>) {
        this.result.launch(permissionList)
    }
}
