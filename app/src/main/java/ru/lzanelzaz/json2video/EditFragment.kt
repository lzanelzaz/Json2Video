package ru.lzanelzaz.json2video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.Locale

@AndroidEntryPoint
class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    private val viewModel: EditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timestamp =
            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.DEFAULT, Locale("ru_RU"))
                .format(System.currentTimeMillis())
                .toString()
                .replace(" ", "_")

        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
            val projectId = timestamp + "_" + uris.size
            uris.forEachIndexed { index, uri ->
                Firebase.storage.reference.child("$projectId/$index")
                    .putFile(uri)
            }
            //viewModel.onCreateProject(projectId)
        }.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

    }
}