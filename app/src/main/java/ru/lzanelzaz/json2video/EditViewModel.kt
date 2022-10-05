package ru.lzanelzaz.json2video

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.lzanelzaz.json2video.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    fun onCreateProject(projectId: String) {
        appRepository.createProject(projectId)
    }
}