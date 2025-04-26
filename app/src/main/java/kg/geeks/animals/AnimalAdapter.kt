package kg.geeks.animals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.geeks.animals.databinding.ItemAnimalsBinding

class AnimalAdapter (
    private val animals: List<Animal>,
    private val onItemClick: (Animal) -> Unit
): RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    inner class AnimalViewHolder(val binding: ItemAnimalsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        with(holder.binding) {
            Glide.with(root.context).load(animal.image).circleCrop().into(imageAnimal)
            textName.text = animal.name
            textYear.text = animal.year.toString()
        }
        holder.itemView.setOnClickListener {
            onItemClick(animal)
        }
    }
    override fun getItemCount() = animals.size
}