package com.example.categoryselectiondzenru.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categoryselectiondzenru.databinding.FragmentViewCategoriesBinding
import com.example.categoryselectiondzenru.model.Category
import com.example.categoryselectiondzenru.presentation.adapter.SelectionCatAdapter
import com.example.categoryselectiondzenru.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewCategoriesFragment : Fragment() {

    private var _binding: FragmentViewCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatViewModel by viewModels ()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentViewCategoriesBinding.inflate(inflater, container, false)


        val recyclerView = binding.recyclerViewSelectionCategories
        val buttonDelete = binding.buttonDelete

        viewModel.dataBaseCategories.observe(viewLifecycleOwner){

            if (it.isEmpty()){
                buttonDelete.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), "Вы не выбрали категории", Toast.LENGTH_LONG).show()
            } else {
                buttonDelete.visibility = View.VISIBLE
                buttonDelete.setOnClickListener {
                viewModel.deleteAll()
                }
            }

            val dbList = mutableListOf<Category>()
            for (i in it){
                dbList.add(Category(i.category))
            }
            recyclerView.adapter = SelectionCatAdapter(dbList)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}