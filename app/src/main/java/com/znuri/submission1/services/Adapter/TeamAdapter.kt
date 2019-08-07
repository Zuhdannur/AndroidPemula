package com.znuri.submission1.services.Adapter

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.znuri.submission1.R
import com.znuri.submission1.services.model.ItemTeams
import kotlinx.android.synthetic.main.item_football.view.*

class TeamAdapter(private val context: Context?,private var data:List<ItemTeams>?) : RecyclerView.Adapter<TeamAdapter.ViewHoder>(){

    constructor() : this(null,null)
    init {
        context
        data
    }

    lateinit var listener:onSelectedRecylerView

    fun init(listener: onSelectedRecylerView){
        this.listener = listener
    }

    class ViewHoder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHoder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_football,parent,false)
        return ViewHoder(view)
    }

    override fun getItemCount(): Int  = data!!.size

    override fun onBindViewHolder(holder: TeamAdapter.ViewHoder, position: Int) {
        var result = data?.get(position)
        Picasso.get().load(result?.strTeamBadge).into(holder.itemView.imageView)
        holder.itemView.setOnClickListener {
            listener.onItemClick(result)
        }
        holder.itemView.teamsName.text = result?.strTeam
        holder.itemView.stadiumNames.text = result?.strStadium
        holder.itemView.textView2.text = result?.intFormedYear
    }

    public interface onSelectedRecylerView{
        fun onItemClick(itemTeams: ItemTeams?)
    }

}
