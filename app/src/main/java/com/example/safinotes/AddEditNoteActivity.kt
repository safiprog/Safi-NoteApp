package com.example.safinotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var NoteTileAdd:EditText
    lateinit var NoteDesAdd:EditText
    lateinit var NoteUpadd:Button
    lateinit var viewModel: NoteViewModel
    var noteID=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel
        ::class.java)

        NoteTileAdd=findViewById(R.id.noteTileEdit)
        NoteDesAdd=findViewById(R.id.noteDesEdit)
        NoteUpadd=findViewById(R.id.noteAddBtn)


        val noteType=intent.getStringExtra("noteType")
        Log.d("hamzasa", "sala kyun faild hora hai ")
        if (noteType.equals("Edit")){
            val noteTile=intent.getStringExtra("noteTitle")
            val noteDescription=intent.getStringExtra("noteDescription")
            noteID=intent.getIntExtra("id",-1)
//            value Set on another Activity

            NoteUpadd.setText("Update Note")
            NoteTileAdd.setText(noteTile)
            NoteDesAdd.setText(noteDescription)

        }else{
            NoteUpadd.setText("Save Note")
        }
        NoteUpadd.setOnClickListener{
            val Notetile=NoteTileAdd.text.toString()
            val NoteDescrip=NoteDesAdd.text.toString()

            if (noteType.equals("Edit")){
                if (Notetile.isNotEmpty() && NoteDescrip.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=Note(Notetile,NoteDescrip,currentDate)
                    updateNote.id=noteID
                    viewModel.update(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                }
            }else{
                if (Notetile.isNotEmpty() && NoteDescrip.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=Note(Notetile,NoteDescrip,currentDate)
                    viewModel.insert(updateNote)
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()

        }

    }
}