package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.data.models.LoginBody
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentLoginBinding
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.contract.LoginContract
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.states.LoginState
import rs.raf.jul.nikola_trajkovic_4519rn.presentation.viewmodels.LoginViewModel

class LoginFragment: Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginBtn: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    private val loginViewModel: LoginContract.ViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
        initObservers()
    }

    private fun init(){

        loginBtn = binding.button
        username = binding.emailEt
        password = binding.passwordEt

    }

    private fun initObservers(){

        loginViewModel.loginState.observe(viewLifecycleOwner){
            renderState(it)
        }

    }

    private fun renderState(state: LoginState){
        when (state) {
            is LoginState.Success -> {

                val shared: String = state.login.username.toString() + "," + state.login.email.toString() + "," + state.login.firstName.toString() +"," +
                        state.login.lastName.toString() + "," + state.login.gender.toString() + "," + state.login.image.toString()

                val pref: SharedPreferences by inject()
                pref.edit().putString("userKey", shared).apply()

                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.replace(R.id.mainFragmentFcv, MainFragment())
                transaction.commit()

            }
            is LoginState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initListeners(){

        loginBtn.setOnClickListener{

            println("USAO U BUTTON")

            val u: String = username.text.toString()
            val p: String = password.text.toString()

            if(!u.equals("") && !p.equals("")){
                if(u.equals("kminchelle") && p.equals("0lelplR") ){

                    println("OVDE PRINT")
                    println(username!!.text)
                    println(password!!.text)

                    val body = LoginBody(
                        username = u,
                        password = p
                    )

                    loginViewModel.login(body)



                }else{
                    println(username.text)
                    println(password.text)
                    Toast.makeText(this.context, "Pogresno ste uneli username ili password", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this.context, "Morate uneti username i password", Toast.LENGTH_SHORT).show()
            }




        }

    }

}