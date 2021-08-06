package com.example.moscowproblems.FragmentClasses

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.moscowproblems.R

private const val STRING_URI_FOR_SHOW_PHOTO_FRAGMENT = "URI FOR FRAGMENT"

class ShowPhotoDialogFragment: DialogFragment() {

    private lateinit var imageViewOnDialogFragment: ImageView

    private lateinit var uriForPhoto: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stringUri = arguments?.getString(STRING_URI_FOR_SHOW_PHOTO_FRAGMENT)
        uriForPhoto = Uri.parse(stringUri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogInstance = dialog
        if (dialogInstance != null){
            dialogInstance.setTitle("Мой заголовок")
        }
        val view = inflater.inflate(R.layout.fragment_show_photo, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageViewOnDialogFragment = view.findViewById<ImageView>(R.id.id_imageview_dialog_problem_photo)
        view.setOnClickListener {
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        imageViewOnDialogFragment.setImageURI(uriForPhoto)
    }
}