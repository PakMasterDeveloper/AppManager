# AppManager
> Step 1. Add the JitPack repository to your build file
 ``` Project Gradle
  allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
 ```
> Step 2. Add the dependency
``` Module Gradle
dependencies {
	        implementation 'com.github.PakMasterDeveloper:AppManager:1.0'
	}
```

> Use only Fragment or Activity for ask permission

```
val permissionRegister= registerPermission {
     when(it){
          PermissionUtils.PermissionState.Granted-> logD("Permission Granted")
          PermissionUtils.PermissionState.Denied-> logD("Permission Denied")
          PermissionUtils.PermissionState.PermanentlyDenied-> logD("Permission PermanentlyDenied")
     }
}
permissionRegister.permissionLauncher(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))

```

> Use only Fragment or Activity for save some small value in sharedPreference

```
  preferenceUtils.setStringValue("key","value")
              or 
  PreferenceUtils.getInstance(this).setStringValue("key","value")

```

> Use only for check Api level 

```
   sdk29AndUp { 
            // above Api level 29 
        }?:let { 
            // below Api level 29
        }

```

> Use only Timer Utils 

```
    TimerUtils.startTimer(5L, listener = object :TimerUtils.TimerCallback{
            override fun onTimerFinish() {
                // do somethings
            }

            override fun onTimerTick(milliSecond: Long) {
                
            }
        })

```
> Use for calling other activity with required parameter like 
```

         navigateActivity(this,MainActivity::class.java,true,Bundle())
```
> Context, Activity Name , if remove Activity from stack only true value , passing Bundle if tou wants pass some value one activity to other activity 

>Use for change Status Color 

```
   statusColorChanger(this,R.color.black,ColorState.WHITE_COLOR)

```
