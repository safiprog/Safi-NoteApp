package com.example.safinotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView

class NAdaptor(val context: Context,
               val NoteClickDelete:NoteClickDelete,
               val NoteClickInterface:NoteClickInterface
): RecyclerView.Adapter<NAdaptor.ViewModel>() {
    private val allNotes= ArrayList<Note>()

    inner class ViewModel(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTV=itemView.findViewById<TextView>(R.id.NoteTile)
        val timeTv=itemView.findViewById<TextView>(R.id.timeStamp)
        val deteleTV=itemView.findViewById<ImageView>(R.id.deleteImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ViewModel(itemView)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("Last Updated : "+ allNotes.get(position).Timestamp)

        holder.deteleTV.setOnClickListener{
            NoteClickDelete.onDeleteIconClicked(allNotes.get(position))
        }
        holder.itemView.setOnClickListener{
            NoteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDelete{
    fun onDeleteIconClicked(note: Note)
}
interface NoteClickInterface{
    fun onNoteClick(note: Note)

}