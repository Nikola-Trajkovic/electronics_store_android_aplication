package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import org.koin.android.ext.android.inject

import rs.raf.jul.nikola_trajkovic_4519rn.databinding.ActivityMainBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments.LoginFragment
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments.MainFragment
import android.widget.Toast
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments.KorpaFragment
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments.ProductFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val userKey = "userKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        val pref: SharedPreferences by inject()
        val username: String? = pref.getString(userKey,null)

        if(username.equals(null)) {
            transaction.replace(R.id.mainFragmentFcv, LoginFragment())
            transaction.commit()
        }else{
            transaction.replace(R.id.mainFragmentFcv, MainFragment())
            transaction.commit()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.item1 -> {
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.mainFragmentFcv, KorpaFragment())
                transaction.commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}