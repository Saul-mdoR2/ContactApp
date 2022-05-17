package com.r2devpros.contactapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.r2devpros.contactapp.databinding.AddressActivityLayoutBinding
import com.r2devpros.contactapp.utils.NAME_TAG
import com.r2devpros.contactapp.utils.getSavedColor

class AddressActivity : AppCompatActivity() {

    private lateinit var binding: AddressActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AddressActivity_TAG", "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = AddressActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        getData()
    }

    override fun onResume() {
        Log.d("AddressActivity_TAG", "onResume: ")
        super.onResume()
        val colorInt = getSavedColor(this)
        if (colorInt != 0)
            binding.mainLayout.setBackgroundColor(colorInt)
    }

    private fun initViews() {
        Log.d("AddressActivity_TAG", "initViews: ")
        setUpToolbar()
    }

    private fun setUpToolbar() {
        Log.d("AddressActivity_TAG", "setUpToolbar: ")
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getData() {
        Log.d("AddressActivity_TAG", "getData: ")
        val extras = intent.extras
        if (extras != null) {
            val name = extras.getString(NAME_TAG)
            name?.let {
                binding.tvNameSub.text = it
            }
        }
    }
}