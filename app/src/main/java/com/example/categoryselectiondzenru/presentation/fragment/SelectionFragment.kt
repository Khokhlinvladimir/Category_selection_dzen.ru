package com.example.categoryselectiondzenru.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categoryselectiondzenru.App
import com.example.categoryselectiondzenru.databinding.FragmentSelectionBinding
import com.example.categoryselectiondzenru.presentation.CatViewModel
import com.example.categoryselectiondzenru.presentation.CatViewModelFactory
import com.example.categoryselectiondzenru.presentation.activity.MainActivity
import com.example.categoryselectiondzenru.presentation.adapter.data.Category
import com.example.categoryselectiondzenru.presentation.adapter.TagAdapter
import com.example.categoryselectiondzenru.presentation.adapter.listeners.OnItemClickListener

class SelectionFragment : Fragment() {

    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory((requireActivity().application as App).repository)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.hide()

        val recyclerView = binding.recyclerView

      viewModel.getListCategories.observe(viewLifecycleOwner){

        val adapter = TagAdapter(it)

            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager

            adapter.onItemClickListener = object : OnItemClickListener {
                override fun onItemClick(category: Category) {
                    Toast.makeText(requireContext(), " ${category.title}", Toast.LENGTH_SHORT).show()
                    Log.d("LOG", "selection click}")
                }
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}