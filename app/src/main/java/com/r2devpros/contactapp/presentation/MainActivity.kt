package com.r2devpros.contactapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.r2devpros.contactapp.R
import com.r2devpros.contactapp.model.Person
import com.r2devpros.contactapp.utils.PERSON_TAG
import com.r2devpros.contactapp.utils.getSavedColor
import com.r2devpros.contactapp.utils.removeColor
import com.r2devpros.contactapp.utils.saveColor


class MainActivity : AppCompatActivity() {

    private lateinit var btnAddress: Button
    private lateinit var btnChangeBackground: Button
    private lateinit var btnResetBackgroung: Button
    private lateinit var etColorHex: EditText
    private lateinit var mainLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity_TAG", "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onResume() {
        Log.d("MainActivity_TAG", "onResume: ")
        super.onResume()
        val colorInt = getSavedColor(this)
        if (colorInt != 0)
            mainLayout.setBackgroundColor(colorInt)
    }

    private fun initViews() {
        Log.d("MainActivity_TAG", "initViews: ")
        val person = Person(
            "",
            "Héctor Saúl Maldonado Gómez",
            22,
            "M",
            "8616125404",
            "8611167660",
            "saul_99_27@hotmail.com"
        )

        btnAddress = findViewById(R.id.btnAddress)
        btnChangeBackground = findViewById(R.id.btnBackground)
        btnResetBackgroung = findViewById(R.id.btnResetColor)
        etColorHex = findViewById(R.id.etColorHex)
        mainLayout = findViewById(R.id.mainLayout)

        btnAddress.setOnClickListener {
            navigateAddress(person)
        }

        btnChangeBackground.setOnClickListener {
            setColorBackground()
        }

        btnResetBackgroung.setOnClickListener {
            removeColor(this@MainActivity)
            mainLayout.setBackgroundColor(getColor(R.color.background))
        }
    }

    private fun setColorBackground() {
        Log.d("MainActivity_TAG", "setColorBackground: ")
        try {
            val colorHex = "#${etColorHex.text}"
            val colorInt = colorHex.toColorInt()
            saveColor(this, colorInt)
            mainLayout.setBackgroundColor(colorInt)
        } catch (ex: Exception) {
            Toast.makeText(
                this,
                getString(R.string.txt_error_change_background),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun navigateAddress(person: Person) {
        Log.d("MainActivity_TAG", "navigateAddress: person ${person.name} ")
        val intent = Intent(this, AddressActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(PERSON_TAG, person)
            })
        }
        startActivity(intent)
    }
}