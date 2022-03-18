package fastcampus.aop.part2.final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fastcampus.aop.part2.final_project.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        setupEvent()
        setupValues()

    }

    override fun setupEvent() {


    }

    override fun setupValues() {

    }
}