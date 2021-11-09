package com.example.studyapp_sql.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.KotlinMaterials
import com.example.studyapp_sql.model.MyViewModel
import com.example.studyapp_sql.R
import com.example.studyapp_sql.adapters.KotlinAdapter
import kotlinx.android.synthetic.main.edit_alert.view.*


class KotlinFragment : Fragment() {

    lateinit var etTitle: EditText
    lateinit var etDescription: EditText
    lateinit var etDetails: EditText
    lateinit var add: Button
    lateinit var rvMain: RecyclerView
    lateinit var rvKotlinAdapter: KotlinAdapter
    var kotlinList = ArrayList<KotlinMaterials>()
    lateinit var back : Button

    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kotlin, container, false)
        activity?.setTitle("Kotlin")
        etTitle = view.findViewById(R.id.etTitle)
        etDescription = view.findViewById(R.id.etDescription)
        etDetails = view.findViewById(R.id.etDetail)
        add = view.findViewById(R.id.add)
        rvMain = view.findViewById(R.id.rvMain)
        back = view.findViewById(R.id.back)



        myViewModel.getKotlinLessons().observe(viewLifecycleOwner, {
                kotlin ->
            rvKotlinAdapter.update(kotlin as ArrayList<KotlinMaterials>)
        })


        add.setOnClickListener {
            var title = etTitle.text.toString()
            var description = etDescription.text.toString()
            var details = etDetails.text.toString()
            myViewModel.addKotlinLesson(KotlinMaterials(0, title, description, details))
            etTitle.setText("")
            etDescription.setText("")
            etDetails.setText("")
        }
        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kotlinFragment_to_mainFragment)
        }

        rvKotlinAdapter = KotlinAdapter(this, kotlinList)
        rvMain.adapter = rvKotlinAdapter
        rvMain.layoutManager = LinearLayoutManager(context)

        return view

    }

    fun editAlert(lesson: KotlinMaterials) {

        val builder = AlertDialog.Builder(context)

        val dialogView = layoutInflater.inflate(R.layout.edit_alert, null)

        builder.setView(dialogView)
        val alertDialog: AlertDialog = builder.create()

        dialogView.etTitle.setText(lesson.title)
        dialogView.etDescription.setText(lesson.description)
        dialogView.etDetail.setText(lesson.details)


        dialogView.edit.setOnClickListener {
            var utitle = dialogView.etTitle.text.toString()
            var udescription = dialogView.etDescription.text.toString()
            var udetails = dialogView.etDetail.text.toString()
            myViewModel.updateKotlinLesson(KotlinMaterials(lesson.id, utitle, udescription, udetails))
            alertDialog.cancel()

        }

        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()

    }

    fun deleteAlert(lesson: KotlinMaterials) {


        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setMessage("Confirm delete ?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

              myViewModel.deleteKotlinLesson(lesson)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()

        alert.setTitle("Delete Alert")
        alert.show()


    }

}