package lt.vitalijus.oneoffevents.app

import android.app.Application
import lt.vitalijus.oneoffevents.login.di.loginModule
import lt.vitalijus.oneoffevents.profile.di.userProfileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OneOffEventsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OneOffEventsApp)
            modules(
                loginModule,
                userProfileModule,
            )
        }
    }
}
