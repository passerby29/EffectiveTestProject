package dev.passerby.effectivetestproject.presentation.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import dev.passerby.effectivetestproject.databinding.FragmentProfileBinding
import dev.passerby.effectivetestproject.presentation.activities.StartActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private var preferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        preferences =
            activity?.getSharedPreferences("APP_PREFERENCES", AppCompatActivity.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPrefs()
        binding.profileLogoutBtn.setOnClickListener {
            val intent = Intent(requireContext(), StartActivity::class.java)
            startActivity(intent)
        }
        binding.profileChangePhotoTv.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            activityResultLauncher.launch(intent)
        }
    }

    private var activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                imageUri = result.data?.data
                binding.profileUserImageIv.setImageURI(imageUri)
                setPrefs()
            }
        }

    private fun getPrefs() {
        imageUri = preferences?.getString(IMAGE_URI, "")?.toUri()
        if (imageUri != null) {
            binding.profileUserImageIv.setImageURI(imageUri)
        }
    }

    private fun setPrefs() {
        val editor = preferences?.edit()
        editor?.putString(IMAGE_URI, imageUri.toString())
        editor?.apply()
    }

    companion object {
        const val IMAGE_URI = "imageUri"
    }
}