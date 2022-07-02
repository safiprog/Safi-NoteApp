package com.example.safinotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDelete {
    lateinit var myRecycler:RecyclerView
    lateinit var floatingBtn:FloatingActionButton
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecycler=findViewById(R.id.myRecycler)
        floatingBtn=findViewById(R.id.floatBtn)

//    recyclerView Impementation
        myRecycler.layoutManager=LinearLayoutManager(this)
        val NoteAdaptor=NAdaptor(this,this,this)
        myRecycler.adapter=NoteAdaptor
//        viewModel all implementation
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                NoteAdaptor.updateList(it)
            }
        })
        floatingBtn.setOnClickListener{

            val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()

        }
    }
//    interface implemantation for adaptor


    override fun onDeleteIconClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("id",note.id)
        startActivity(intent)
        this.finish()
    }


}