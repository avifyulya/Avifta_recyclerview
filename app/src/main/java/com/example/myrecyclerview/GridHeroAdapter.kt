package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myrecyclerview.Hero
import com.example.myrecyclerview.ListHeroAdapter
import com.example.myrecyclerview.R

class GridHeroAdapter(val listHeroes: ArrayList<Hero>) : RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: Any){
        this.onItemClickCallback= onItemClickCallback as OnItemClickCallback
    }
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var photo: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i : Int) : GridViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid_hero, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listHeroes[position].photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.photo)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listHeroes[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listHeroes.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}
