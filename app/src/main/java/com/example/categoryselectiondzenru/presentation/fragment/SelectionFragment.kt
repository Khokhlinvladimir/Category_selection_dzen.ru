package com.example.categoryselectiondzenru.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categoryselectiondzenru.App
import com.example.categoryselectiondzenru.data.repository.Repository
import com.example.categoryselectiondzenru.databinding.FragmentSelectionBinding
import com.example.categoryselectiondzenru.presentation.CatViewModel
import com.example.categoryselectiondzenru.presentation.CatViewModelFactory
import com.example.categoryselectiondzenru.presentation.activity.MainActivity
import com.example.categoryselectiondzenru.presentation.adapter.Selection
import com.example.categoryselectiondzenru.presentation.adapter.TagAdapter
import com.example.categoryselectiondzenru.presentation.adapter.mapper.MyItem
import com.example.categoryselectiondzenru.presentation.adapter.mapper.MyItemMapper

class SelectionFragment : Fragment() {

    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!
    private val myItems = ArrayList<MyItem>()
    private val mapper = MyItemMapper()

    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory((requireActivity().application as App).repository)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.hide()

        val recyclerView = binding.recyclerView

        viewModel.getListCategories.observe(viewLifecycleOwner){

            for (i in it.indices){
                myItems.add(MyItem((i+1), it[i]))
            }




            val adapter = TagAdapter(mapper.mapFromEntityList(myItems), Selection.NON_SELECTABLE)

            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
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