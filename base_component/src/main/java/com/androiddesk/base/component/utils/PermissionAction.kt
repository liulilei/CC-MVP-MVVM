package io.newdex.exchange.utils

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import io.newdex.exchange.constants.Constants
import io.newdex.exchange.constants.IntentConstants
import io.reactivex.functions.Consumer
import me.yokeyword.fragmentation.SupportFragment
import java.io.File

/**
 * Created by lll on 2017/2/6.
 */

class PermissionAction @JvmOverloads constructor(private val fragment: SupportFragment, private val requestCode: Int, private val fillName: String = "") : Consumer<Boolean> {

    override fun accept(granted: Boolean?) {
        if (granted!!) {
            if (requestCode == Constants.PERMISSION_PHOTO) {
                fragment.startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IntentConstants.RESULT_LOAD_IMAGE)
            } else if (requestCode == Constants.PERMISSION_CAMERA) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(fillName)))
                fragment.startActivityForResult(intent, IntentConstants.TAKE_PICTURE_REQUEST_CODE)
            }
        } else {

        }
    }
}
