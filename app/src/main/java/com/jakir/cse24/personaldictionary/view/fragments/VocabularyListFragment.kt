package com.jakir.cse24.personaldictionary.view.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.jakir.cse24.personaldictionary.R
import com.jakir.cse24.personaldictionary.adapter.VocabularyListAdapter
import com.jakir.cse24.personaldictionary.base.BaseFragment
import com.jakir.cse24.personaldictionary.interfaces.ItemClickListener
import com.jakir.cse24.personaldictionary.model.Vocabulary
import com.jakir.cse24.personaldictionary.view.activities.LoginActivity
import com.jakir.cse24.personaldictionary.view_model.VocabularyListViewModel
import kotlinx.android.synthetic.main.activity_vocabulary_list.*

/**
 * A simple [BaseFragment] subclass.
 * Created by Md. Jakir Hossain on 02/05/2019.
 */
class VocabularyListFragment : BaseFragment(), ItemClickListener {

    private lateinit var viewModel: VocabularyListViewModel

    override fun onItemClick(vocabulary: Vocabulary) {
        showToast(vocabulary.translation.meaning)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[VocabularyListViewModel::class.java]


        val layoutManager = LinearLayoutManager(requireContext())

        fabAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_addVocabulary)
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.hasFixedSize()
        recyclerView.adapter = VocabularyListAdapter(viewModel.vocabularies.value!!, this)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))
        recyclerView
    }


}
