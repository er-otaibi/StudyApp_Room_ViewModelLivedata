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
import com.example.studyapp.AndroidMaterials
import com.example.studyapp_sql.model.Lesson
import com.example.studyapp_sql.model.MyViewModel
import com.example.studyapp_sql.R
import com.example.studyapp_sql.adapters.AndroidAdapter
import kotlinx.android.synthetic.main.edit_alert.view.*

class AndroidFragment : Fragment() {
    lateinit var etTitle: EditText
    lateinit var etDescription: EditText
    lateinit var etDetails: EditText
    lateinit var add: Button
    lateinit var rvMain: RecyclerView
    lateinit var back: Button
    lateinit var rvAndroidAdapter: AndroidAdapter
    var androidList = ArrayList<AndroidMaterials>()
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_android, container, false)

        activity?.setTitle("Android")
        etTitle = view.findViewById(R.id.etTitle)
        etDescription = view.findViewById(R.id.etDescription)
        etDetails = view.findViewById(R.id.etDetail)
        add = view.findViewById(R.id.add)
        rvMain = view.findViewById(R.id.rvMain)
        back = view.findViewById(R.id.back)


        myViewModel.getAndroidLessons().observe(viewLifecycleOwner,
            {
                    android ->
            rvAndroidAdapter.update(android as ArrayList<AndroidMaterials>)
        })

        add.setOnClickListener {
            var title = etTitle.text.toString()
            var description = etDescription.text.toString()
            var details = etDetails.text.toString()
            myViewModel.addAndroidLesson(AndroidMaterials(0, title, description, details))
            etTitle.setText("")
            etDescription.setText("")
            etDetails.setText("")
        }

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_androidFragment_to_mainFragment)
        }

        rvAndroidAdapter = AndroidAdapter(this, androidList)
        rvMain.adapter = rvAndroidAdapter
        rvMain.layoutManager = LinearLayoutManager(context)

        return view
    }

    fun editAlert(lesson: AndroidMaterials) {

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
            myViewModel.updateAndroidLesson(AndroidMaterials(lesson.id, utitle, udescription, udetails))
            alertDialog.cancel()

        }

        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()

    }

    fun deleteAlert(lesson: AndroidMaterials) {
        val dialogBuilder = AlertDialog.Builder(context)


        dialogBuilder.setMessage("Confirm delete ?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                myViewModel.deleteAndroidLesson(lesson)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()

        alert.setTitle("Delete Alert")
        alert.show()


    }
}