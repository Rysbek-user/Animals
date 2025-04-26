package kg.geeks.animals.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geeks.animals.Animal
import kg.geeks.animals.AnimalAdapter
import kg.geeks.animals.R
import kg.geeks.animals.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animals = listOf(
        Animal(
            image = "https://main-cdn.sbermegamarket.ru/big1/hlr-system/1724237217/600001115887b0.jpeg",
            name = "Toni",
            year = 2
        ),
        Animal(
            image = "https://avatars.mds.yandex.net/i?id=4d36b858e6c5677b5346cd5a03470197_l-10248205-images-thumbs&n=13",
            name = "Rock",
            year = 3
        ),
        Animal(
            image = "https://sun9-51.userapi.com/impg/ImnPWin-uPZUGhUnTzDGPwJ9n5eAHmmb-txG0Q/Z0G1QFFmM6Y.jpg?size=807x538&quality=96&sign=d6e284b8ba38d08402924a466b573eaa&c_uniq_tag=WCO0byECqDjodj9ZbgTQvAZxb2fqnLylkHcQvxh4KpQ&type=album",
            name = "Bobik",
            year = 1
        ),
        Animal(
            image = "https://avatars.mds.yandex.net/i?id=e21f66d84d038c1c037039c6bb2200c0_l-4079997-images-thumbs&n=13",
            name = "Bob",
            year = 4
        ),
        Animal(
            image = "https://avatars.mds.yandex.net/i?id=afb1d08084b556cbb3458ca4ac7d0cfb_l-7571629-images-thumbs&n=13",
            name = "Job",
            year = 3
        ),
        Animal(
            image = "https://storage-api.petstory.ru/resize/0x0x100/f2/72/bb/f272bbb28693491db9107c249985ea68.jpeg",
            name = "Sosiska",
            year = 2
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AnimalAdapter(animals) { animal ->
            val bundle = Bundle().apply {
                putString("imageUrl", animal.image)
                putString("name", animal.name)
                putInt("year", animal.year)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        }

        binding.rvAnimals.adapter = adapter
        binding.rvAnimals.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

