package uz.uni_team.online_translator.presentation.main.pages.docs

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.OpenableColumns
import android.util.AttributeSet
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo
import dagger.hilt.android.AndroidEntryPoint
import uz.uni_team.online_translator.R
import uz.uni_team.online_translator.databinding.ScreenDocsBinding
import uz.uni_team.online_translator.utils.pdfviewer.PDFView
import java.io.File
import java.io.FileInputStream


@AndroidEntryPoint
class DocScreen : Fragment(R.layout.screen_docs) {

    private lateinit var launcher: ActivityResultLauncher<Array<String>>

    private val binding:ScreenDocsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launcher = registerForActivityResult(
            ActivityResultContracts.OpenDocument(),
        ) { result ->
            result?.let { openPdf(uri = result) }
        }
        checkPermissions()
    }

    private fun checkPermissions() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(
                object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        println("Denied")
                        launcher.launch(arrayOf("application/pdf"))
                    }

                    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
                        println("onPermissionRationaleShouldBeShown")
                        p1?.continuePermissionRequest()
                    }
                },
            ).check()
    }

    private fun openPdf(uri: Uri) {
        binding.pdfView.fromUri(uri)
            .nightMode(true)
            .load()
    }
}