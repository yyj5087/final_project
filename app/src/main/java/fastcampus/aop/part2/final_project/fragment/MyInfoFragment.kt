package fastcampus.aop.part2.final_project.fragment

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import fastcampus.aop.part2.final_project.OrderHistoryActivity
import fastcampus.aop.part2.final_project.R
import fastcampus.aop.part2.final_project.SplashActivity
import fastcampus.aop.part2.final_project.databinding.MyInfoListBinding
import fastcampus.aop.part2.final_project.datas.BasicResponse
import fastcampus.aop.part2.final_project.utils.ContextUtil
import fastcampus.aop.part2.final_project.utils.URIPathHelper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class MyInfoFragment: BaseFragment() {

    lateinit var binding: MyInfoListBinding

    val REQ_CODE_GALLERY = 2000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_info_list,container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvent()
        setupValue()

    }



    override fun setupEvent() {

        apiList.getRequestMyInfo().enqueue(object : retrofit2.Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){
                    val br = response.body()!!

                    binding.txtNickname.text = br.data.user.name

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

        binding.imgProfile.setOnClickListener {

            val pl = object :PermissionListener{
                override fun onPermissionGranted() {
                    val myIntent = Intent()
                    myIntent.action = Intent.ACTION_PICK
                    myIntent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
                    startActivityForResult(myIntent, REQ_CODE_GALLERY)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "갤러리 조회 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                }

            }
            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()

        }

        binding.orderHistory.setOnClickListener {
            val myIntent = Intent(mContext, OrderHistoryActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogout.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
                .setTitle("로그아웃")
                .setMessage("정말 로그아웃 하시겠습니까?")
                .setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                    ContextUtil.setLoginUserToken(mContext, "")
                    val myIntent = Intent(mContext, SplashActivity::class.java)
                    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(myIntent)
                })
                .setNegativeButton("취소", null)
                .show()

        }

    }

    override fun setupValue() {




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQ_CODE_GALLERY){
            if(resultCode == Activity.RESULT_OK){

                val selectedImageUri = data?.data!!
                val file = File(URIPathHelper().getPath(mContext, selectedImageUri))
                val fileReqBody = RequestBody.create(MediaType.get("image/*"), file)
                val multiPartBody = MultipartBody.Part.createFormData("profile_image", "myProfile.jpg", fileReqBody)

                apiList.putRequestProfileImg(
                    multiPartBody
                ).enqueue(object : retrofit2.Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if(response.isSuccessful){
                            Toast.makeText(mContext, "프로필 사진이 변경되었습니다.", Toast.LENGTH_SHORT).show()
                            Glide.with(mContext).load(selectedImageUri).into(binding.imgProfile)
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })

            }
        }
    }


}