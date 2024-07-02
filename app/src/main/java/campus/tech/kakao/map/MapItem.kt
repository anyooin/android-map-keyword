package campus.tech.kakao.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MapItem(
    val name: String,
    val address: String,
    val category: String
)

class MapListAdapter(
    val mapItemList: MutableList<MapItem>, val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<MapListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val address: TextView
        val category: TextView

        init {
            name = itemView.findViewById<TextView>(R.id.name)
            address = itemView.findViewById<TextView>(R.id.address)
            category = itemView.findViewById<TextView>(R.id.category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.map_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mapItemList.get(position).name
        holder.address.text = mapItemList.get(position).address
        holder.category.text = mapItemList.get(position).category
    }

    override fun getItemCount(): Int {
        return mapItemList.size
    }
}