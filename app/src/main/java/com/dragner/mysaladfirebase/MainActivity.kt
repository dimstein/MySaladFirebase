package com.dragner.mysaladfirebase

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null
    private var myFirstEdit = "first"
    private var mySecondEdit = "Second"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->

            myFirstEdit = editText2.text.toString()
            mySecondEdit = editText3.text.toString()

            Snackbar.make(view, "this is the message $myFirstEdit and $mySecondEdit", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }



}
     fun ButtonTwoClick(view: View){


        val alert = AlertDialog.Builder(this)
        var editTextAge: EditText? = null
        var editTextYear: EditText? = null

        // Builder
        with(alert) {
            var title: AlertDialog.Builder = setTitle("Title of Alert")
            var name = "kjhhg"   //R.id.editTextName.toString()
            setMessage("${name} \n\nEnter your Age Here!!")

        // Add any  input field here
            editTextAge = EditText(context)
            editTextAge!!.hint = "age"
            editTextAge!!.inputType = InputType.TYPE_CLASS_NUMBER

        setPositiveButton("OK") { dialog, whichButton ->
            //showMessage("display the game score or anything!")
            dialog.dismiss()
            var age = editTextAge!!.text.toString()
            // textViewOutput.text = " Name: ${name} and Age:  ${age}"
        }

        setNegativeButton("NO") { dialog, whichButton ->
            //showMessage("Close the game or anything!")
            dialog.dismiss()
        }


        // Dialog
        val dialog = alert.create()
        dialog.setView(editTextAge)
       dialog.show()
    }
    }
    fun BtnThreeClick (view: View){
        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference()

        data class SaladItem(
                val name: String = "",
                val description: String = "",
                var uuid: String = ""
            )

        myFirstEdit = editText2.text.toString()
        mySecondEdit = editText3.text.toString()

        val availableSalads: List<SaladItem> = mutableListOf(
            SaladItem(myFirstEdit, mySecondEdit)
        )
        availableSalads.forEach {
            val key = mDatabase!!.child("adds").push().key
            it.uuid = key
            mDatabase!!.child("adds").child(key).setValue(it)
    }
}}






