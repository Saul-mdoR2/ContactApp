package com.r2devpros.contactapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.r2devpros.contactapp.R
import com.r2devpros.contactapp.databinding.ActivityMainBinding
import com.r2devpros.contactapp.model.Person
import com.r2devpros.contactapp.repository.room.PersonDatabase
import com.r2devpros.contactapp.utils.NAME_TAG
import com.r2devpros.contactapp.utils.getSavedColor
import com.r2devpros.contactapp.utils.removeColor
import com.r2devpros.contactapp.utils.saveColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var db: PersonDatabase? = null
    private val totalItems = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity_TAG", "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        db = Room.databaseBuilder(
            this,
            PersonDatabase::class.java,
            PersonDatabase.DATABASE_NAME
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            totalItems.postValue(db?.personDao()?.getAll()?.count())
        }

        totalItems.observe(this) {
            Toast.makeText(
                applicationContext,
                "Total contacts: $it",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onResume() {
        Log.d("MainActivity_TAG", "onResume: ")
        super.onResume()
        val colorInt = getSavedColor(this)
        if (colorInt != 0)
            binding.mainLayout.setBackgroundColor(colorInt)
    }

    private fun initViews() {
        Log.d("MainActivity_TAG", "initViews: ")

        binding.btnAddress.setOnClickListener {
            navigateAddress()
        }

        binding.btnBackground.setOnClickListener {
            setColorBackground()
        }

        binding.btnResetColor.setOnClickListener {
            removeColor(this@MainActivity)
            binding.mainLayout.setBackgroundColor(getColor(R.color.background))
        }

        binding.btnShowLayoutData.setOnClickListener {
            binding.layoutData.visibility = View.VISIBLE
        }

        binding.btnAddContact.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db?.personDao()?.insert(
                    Person(
                        0,
                        "",
                        binding.etNameValue.text.toString(),
                        binding.etAge.text.toString().toInt(),
                        binding.etGender.text.toString(),
                        binding.etPhone.text.toString(),
                        binding.etMobile.text.toString(),
                        binding.etEmail.text.toString()
                    )
                )
            }
            binding.layoutData.visibility = View.GONE
        }
    }

    private fun setColorBackground() {
        Log.d("MainActivity_TAG", "setColorBackground: ")
        try {
            val colorHex = "#${binding.etColorHex.text}"
            val colorInt = colorHex.toColorInt()
            saveColor(this, colorInt)
            binding.mainLayout.setBackgroundColor(colorInt)
        } catch (ex: Exception) {
            Toast.makeText(
                this,
                getString(R.string.txt_error_change_background),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun navigateAddress() {
        Log.d("MainActivity_TAG", "navigateAddress: ")
        val intent = Intent(this, AddressActivity::class.java).apply {
            putExtras(Bundle().apply {
                putString(NAME_TAG, binding.tvNameSub.text.toString())
            })
        }
        startActivity(intent)
    }
}