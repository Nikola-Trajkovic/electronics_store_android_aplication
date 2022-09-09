package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.data.datasources.remote.LoginService
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.modules.create
import rs.raf.jul.nikola_trajkovic_4519rn.modules.createMoshi
import rs.raf.jul.nikola_trajkovic_4519rn.modules.createOkHttpClient
import rs.raf.jul.nikola_trajkovic_4519rn.modules.createRetrofit


class LoginActivity() : AppCompatActivity() {

    private var loginBtn: Button? = null
    private var username: EditText? = null
    private var password: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        initListeners()
    }

    private fun init(){

        loginBtn = findViewById(R.id.button)
        username = findViewById(R.id.emailEt)
        password = findViewById(R.id.passwordEt)

    }

    private fun initListeners(){

        loginBtn?.setOnClickListener{

            println("USAO U BUTTON")

            val u: String = username!!.text.toString()
            val p: String = password!!.text.toString()

            if(u != null && p != null){

                if(u.equals("kminchelle") && p.equals("0lelplR") ){

                    println("OVDE PRINT")
                    println(username!!.text)
                    println(password!!.text)

                    val body = LoginBody(
                        username = u,
                        password = p
                    )





                }else{
                    println(username!!.text)
                    println(password!!.text)
                    Toast.makeText(this, "Pogresno ste uneli username ili password", Toast.LENGTH_SHORT).show()
                }


            }else{
                Toast.makeText(this, "Morate popuniti username i password", Toast.LENGTH_SHORT).show()
            }

        }

    }

}