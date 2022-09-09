package rs.raf.jul.nikola_trajkovic_4519rn.presentation.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import okhttp3.internal.wait
import org.koin.android.ext.android.inject
import rs.raf.jul.nikola_trajkovic_4519rn.R
import rs.raf.jul.nikola_trajkovic_4519rn.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    val userKey = "userKey"

    private lateinit var username: TextView
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var image: ImageView
    private lateinit var email: TextView
    private lateinit var gender: TextView
    private lateinit var logOut: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
    }

    private fun init(){

        username = binding.usernameTv
        firstName = binding.firstNameTv
        lastName = binding.lastNameTv
        image = binding.imageView
        email = binding.emailTv
        gender = binding.genderTv
        logOut = binding.logOutBtn




    }

    private fun initListeners(){

        val pref: SharedPreferences by inject()
        val user: String? = pref.getString(userKey, null)

        val splited: List<String> = user!!.split(",")

        username.text = splited[0]
        email.text = splited[1]
        firstName.text = splited[2]
        lastName.text = splited[3]
        gender.text = splited[4]
        val imgUrl: String = splited[5]

        Glide.with(this)
            .load(imgUrl)
            .override(500, 500)
            .into(image)

        logOut.setOnClickListener{

            val pref: SharedPreferences by inject()
            pref.edit().clear().apply()

            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.mainFragmentFcv, LoginFragment())
            transaction.commit()


        }

    }

}