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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity_TAG", "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
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

        findViewById<Button>(R.id.btnAddress).setOnClickListener {
            navigateAddress(person)
        }

        findViewById<Button>(R.id.btnBackground).setOnClickListener {
            try {
                val colorHex = "#${findViewById<EditText>(R.id.etColorHex).text}"
                val colorInt = colorHex.toColorInt()
                findViewById<LinearLayout>(R.id.mainLayout).setBackgroundColor(colorInt)
            } catch (ex: Exception) {
                Toast.makeText(
                    this,
                    getString(R.string.txt_error_change_background),
                    Toast.LENGTH_LONG
                ).show()
            }
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