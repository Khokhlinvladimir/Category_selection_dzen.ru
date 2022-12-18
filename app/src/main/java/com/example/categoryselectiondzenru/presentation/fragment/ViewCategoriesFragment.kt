package com.example.categoryselectiondzenru.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categoryselectiondzenru.App
import com.example.categoryselectiondzenru.R
import com.example.categoryselectiondzenru.data.entity.Categories
import com.example.categoryselectiondzenru.databinding.FragmentSelectionBinding
import com.example.categoryselectiondzenru.databinding.FragmentViewCategoriesBinding
import com.example.categoryselectiondzenru.model.Category
import com.example.categoryselectiondzenru.presentation.adapter.TagAdapter
import com.example.categoryselectiondzenru.presentation.viewmodel.CatViewModel
import com.example.categoryselectiondzenru.presentation.viewmodel.CatViewModelFactory


class ViewCategoriesFragment : Fragment() {

    private var _binding: FragmentViewCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory((requireActivity().application as App).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentViewCategoriesBinding.inflate(inflater, container, false)


        val recyclerView = binding.recyclerViewSelectionCategories

        viewModel.dataBaseCategories.observe(viewLifecycleOwner){
            val dbList = mutableListOf<Category>()
            for (i in it){
                dbList.add(Category(i.category))
            }
            recyclerView.adapter = TagAdapter(dbList)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}