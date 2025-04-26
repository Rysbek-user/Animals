package kg.geeks.animals.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kg.geeks.animals.R
import kg.geeks.animals.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var year: Int? = null
    private var isIncrementing = false
    private val handler = Handler(Looper.getMainLooper())
    private val incrementRunnable = object : Runnable {
        override fun run() {
            if (isIncrementing) {
                year?.let {
                    year = it + 1
                    binding.textYear.text = year.toString()
                }
                handler.postDelayed(this, 200) // Увеличивать каждые 200 мс
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val imageUrl = arguments?.getString("imageUrl")
        val name = arguments?.getString("name")
        year = arguments?.getInt("year")

        Glide.with(this)
            .load(imageUrl ?: R.drawable.baseline_arrow_back_24)
            .circleCrop()
            .placeholder(R.drawable.baseline_arrow_back_24)
            .error(R.drawable.error_image)
            .into(binding.animalImage)

        binding.textView.text = name ?: "Unknown"
        binding.textYear.text = year?.toString() ?: "Unknown"

        // Обработка зажатия кнопки
        binding.buttonIncrement.setOnLongClickListener {
            isIncrementing = true
            handler.post(incrementRunnable)
            true
        }

        // Остановка увеличения при отпускании кнопки
        binding.buttonIncrement.setOnClickListener {
            // Одиночный клик (опционально)
            year?.let {
                year = it + 1
                binding.textYear.text = year.toString()
            }
        }

        binding.buttonIncrement.setOnTouchListener { _, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP ||
                event.action == android.view.MotionEvent.ACTION_CANCEL) {
                isIncrementing = false
                handler.removeCallbacks(incrementRunnable)
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(incrementRunnable) // Очистка
        _binding = null
    }
}