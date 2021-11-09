package com.example.studyapp_sql.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.studyapp_sql.R


class MainFragment: Fragment() {
   lateinit var android : Button
   lateinit var kotlin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        activity?.setTitle("Main")

        android = view.findViewById(R.id.android)
        kotlin = view.findViewById(R.id.kotlin)

        android.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_androidFragment)
        }

        kotlin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_kotlinFragment)
        }

        return view
    }

}