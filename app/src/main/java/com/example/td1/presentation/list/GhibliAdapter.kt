package com.example.td1.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.td1.R

class GhibliAdapter(private var dataSet: List<Ghibli>,var listener:((Int) -> Unit)?= null) :
        RecyclerView.Adapter<GhibliAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.ghibli_name)
        }
    }

    fun updateList(list: List<Ghibli>){
        dataSet = list
        notifyDataSetChanged()

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.ghibli_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
       // val ghibli : Ghibli = dataSet[position]
        val ghibli  = dataSet[position]
       // viewHolder.textView.text = ghibli.toString()
        viewHolder.textView.text = ghibli.title

      //  viewHolder.textViewName.text = ghibli.original_title
        viewHolder.itemView.setOnClickListener{
            listener?.invoke(position)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
