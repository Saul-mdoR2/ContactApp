package com.r2devpros.contactapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.r2devpros.contactapp.R
import com.r2devpros.contactapp.model.Person
import com.r2devpros.contactapp.utils.PERSON_TAG

@Suppress("DEPRECATION")
class AddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("AddressActivity_TAG", "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_activity_layout)
        initViews()
        getData()
    }

    private fun initViews() {
        Log.d("AddressActivity_TAG", "initViews: ")
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
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
            if (person != null)
                findViewById<TextView>(R.id.tvNameSub).text = person.name
        }
    }
}