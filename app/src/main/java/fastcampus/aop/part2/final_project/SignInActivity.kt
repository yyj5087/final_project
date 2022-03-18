package fastcampus.aop.part2.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.databinding.ActivitySignInBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity() {

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {

        binding.btnSignUP.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }



        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    if(response.isSuccessful){

                        val br = response.body()!!
                        Toast.makeText(mContext, "${br.data.user.name}님, 환영합니다!", Toast.LENGTH_SHORT).show()

                    }
                    else{

                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            } )
        }
    }

    override fun setupValues() {

    }
}