package com.example.categoryselectiondzenru.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categoryselectiondzenru.App
import com.example.categoryselectiondzenru.R
import com.example.categoryselectiondzenru.data.entity.Categories
import com.example.categoryselectiondzenru.databinding.FragmentSelectionBinding
import com.example.categoryselectiondzenru.presentation.viewmodel.CatViewModel
import com.example.categoryselectiondzenru.presentation.viewmodel.CatViewModelFactory
import com.example.categoryselectiondzenru.presentation.activity.MainActivity
import com.example.categoryselectiondzenru.model.Category
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
                override fun onItemClick(category: Category, isCheck: Boolean) {

                    if (isCheck){
                        viewModel.insert(Categories(category = category.name, timestamp = System.currentTimeMillis()))
                    } else {
                        viewModel.deleteByCategory(category = category.name)
                    }

                    Toast.makeText(requireContext(), " ${category.name}  $isCheck", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            onClick()
        }

    }



    private fun onClick(){
        (activity as MainActivity).navController.navigate(R.id.action_selectionFragment_to_viewCategoriesFragment)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}