package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.databinding.ActivitySignUpBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {


        binding.btnSignUP.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()
            val inputName = binding.edtName.text.toString()
            val inputPhoneNum = binding.edtPhoneNum.text.toString()

            apiList.putRequestSignUp(inputEmail, inputPassword, inputName, inputPhoneNum).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful){

                        val br = response.body()!!


                        Toast.makeText(mContext, "가입을 축하합니다!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })


        }
    }

    override fun setupValues() {

    }
}