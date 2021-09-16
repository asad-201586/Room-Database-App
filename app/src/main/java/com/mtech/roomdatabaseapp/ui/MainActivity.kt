package com.mtech.roomdatabaseapp.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtech.roomdatabaseapp.R
import com.mtech.roomdatabaseapp.databinding.UserEditDialogBinding
import com.mtech.roomdatabaseapp.roomDB.entity.UserEntity
import com.mtech.roomdatabaseapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_room_data.*

class MainActivity : AppCompatActivity(), UserInfoAdapter.RawClickListener {

    lateinit var userAdapter: UserInfoAdapter
    private val mainViewModel: MainViewModel by lazy { MainViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerview()
        getData()
        setUpObserver()
        clickEvent()

    }

    private fun clickEvent() {
        button_submit.setOnClickListener{
            val name = edt_name.text.toString()
            val designation = edt_designation.text.toString()
            val age = edt_age.text.toString()

            if (validateEdt(edt_name,name,getString(R.string.enter_your_name))
                && validateEdt(edt_designation,designation,getString(R.string.enter_your_designation))
                && validateEdt(edt_age,age,getString(R.string.enter_your_age))
                ){
                    val data = UserEntity(0,name,designation,age.toInt())
                    mainViewModel.addUser(data)
                    clearEdt()
                    Toast.makeText(this,getString(R.string.user_added),Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun clearEdt() {
        edt_name.setText("")
        edt_designation.setText("")
        edt_age.setText("")
    }

    private fun setUpObserver() {
        mainViewModel.getAllUsersObservers().observe(this, {
            userAdapter.setListData(ArrayList(it))
            userAdapter.notifyDataSetChanged()
        })
    }

    private fun getData() {
        mainViewModel.getAllUsers()
    }

    private fun setRecyclerview() {
        room_rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            userAdapter = UserInfoAdapter(this@MainActivity)
            adapter = userAdapter
            val divider = DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(divider)
        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        mainViewModel.deleteUser(user)
        Toast.makeText(this,getString(R.string.user_deleted),Toast.LENGTH_SHORT).show()

    }

    override fun onItemClickListener(user: UserEntity) {
        Toast.makeText(this,"Hello ${user.name}",Toast.LENGTH_SHORT).show()
    }

    override fun onEditUserClickListener(user: UserEntity) {
        showEditDialog(user)
    }

    private fun showEditDialog(user: UserEntity) {
        val dialog = Dialog(this)
        val dialogBinding = UserEditDialogBinding.inflate(LayoutInflater.from(this),null,false)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setData(dialogBinding,user)

        dialogBinding.buttonUpdate.setOnClickListener {

            val name = dialogBinding.edtName.text.toString()
            val designation = dialogBinding.edtDesignation.text.toString()
            val age = dialogBinding.edtAge.text.toString()

            if (validateEdt(edt_name,name,getString(R.string.enter_your_name))
                && validateEdt(edt_designation,designation,getString(R.string.enter_your_designation))
                && validateEdt(edt_age,age,getString(R.string.enter_your_age))
            ){
                val data = UserEntity(user.id,name,designation,age.toInt())
                mainViewModel.updateUser(data)
                Toast.makeText(this,getString(R.string.user_data_updated),Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun setData(binding: UserEditDialogBinding,user: UserEntity) {
        binding.edtName.setText(user.name)
        binding.edtDesignation.setText(user.designation)
        binding.edtAge.setText(user.age.toString())
    }

    private fun validateEdt(edt: EditText, value: String, warningMessage: String): Boolean{
        if (value.isEmpty()){
            edt.requestFocus()
            Toast.makeText(this,warningMessage, Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}