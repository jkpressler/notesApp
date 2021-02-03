package com.example.notesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.notesapp.R
import com.example.notesapp.models.Note
import java.lang.Exception

class NoteAdapter(context: Context, resource: Int, list: ArrayList<Note>): ArrayAdapter<Note>(context, resource, list) {

    private var mResource: Int = 0
    private var mList: ArrayList<Note>
    private var mLayoutInflater: LayoutInflater
    private var mContext: Context = context

    init{
        this.mResource = resource
        this.mList = list
        this.mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val returnView: View?
        if(convertView == null) {
            returnView = try {
                mLayoutInflater.inflate(mResource, null)
            } catch (e: Exception) {
                e.printStackTrace()
                View(context)
            }

            if (returnView != null) {
                setUI(returnView, position)
            }
            return returnView!!
        }
        setUI(convertView, position)
        return convertView
    }

    private fun setUI(view: View, position: Int){
        val note: Note? = if (count > position) getItem(position) else null
        val noteTitle: TextView? = view.findViewById(R.id.tvTitle)
        noteTitle?.text = note?.title ?: ""
        val noteBody: TextView? = view.findViewById(R.id.tvBody)
        noteBody?.text = note?.body ?: ""
        val noteDT: TextView? = view.findViewById(R.id.tvDateTime)
        noteDT?.text = note?.dateTime ?: ""
    }
}