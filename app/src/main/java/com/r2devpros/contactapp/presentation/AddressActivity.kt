package com.r2devpros.contactapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.r2devpros.contactapp.R
import com.r2devpros.contactapp.model.Person
import com.r2devpros.contactapp.utils.PERSON_TAG
import com.r2devpros.contactapp.utils.getSavedColor

@Suppress("DEPRECATION")
class AddressActivity : AppCompatActivity() {

    private lateinit var mainLayout: LinearLayout
    private lateinit var toolbar: Toolbar
    private lateinit var tvName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AddressActivity_TAG", "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_activity_layout)
        initViews()
        getData()
    }

    override fun onResume() {
        Log.d("AddressActivity_TAG", "onResume: ")
        super.onResume()
        val colorInt = getSavedColor(this)
        if (colorInt != 0)
            mainLayout.setBackgroundColor(colorInt)
    }

    private fun initViews() {
        Log.d("AddressActivity_TAG", "initViews: ")

        mainLayout = findViewById(R.id.mainLayout)
        tvName = findViewById(R.id.tvNameSub)

        setUpToolbar()
    }

    private fun setUpToolbar() {
        Log.d("AddressActivity_TAG", "setUpToolbar: ")
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getData() {
        Log.d("AddressActivity_TAG", "getData: ")
        val extras = intent.extras
        if (extras != null) {
            val person = extras.getParcelable<Person>(PERSON_TAG)
            person?.let {
                tvName.text = it.name
            }
        }
    }
}