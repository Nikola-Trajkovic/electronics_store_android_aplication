package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){

        val splash: androidx.core.splashscreen.SplashScreen = installSplashScreen()
        splash.setKeepOnScreenCondition {

            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()


            false
        }
    }

}