package com.delta22.rentateamtest.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.delta22.rentateamtest.R
import com.delta22.rentateamtest.data.json.User
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class UserInfoFragment : Fragment() {

    companion object {
        private const val DIRECTORY = "/save/"
    }

    private lateinit var imageView: ImageView
    private lateinit var textFirstName: TextView
    private lateinit var textLastName: TextView
    private lateinit var textEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_info, container, false)
        findViews(view)
        val user: User = requireArguments().getSerializable(User.USER_EXTRA) as User
        showUserInfo(user)
        return view
    }

    private fun findViews(view: View) {
        imageView = view.findViewById(R.id.image_info_user)
        textFirstName = view.findViewById(R.id.text_info_user_first_name)
        textLastName = view.findViewById(R.id.text_info_user_last_name)
        textEmail = view.findViewById(R.id.text_info_user_email)
    }

    private fun showUserInfo(user: User) {
        loadImage(user.id.toString(), user.avatar)
        textFirstName.text = user.firstName
        textLastName.text = user.lastName
        textEmail.text = user.email
    }

    private fun saveImage(imageKey: String, url: String) {
        val target: Target = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if (bitmap == null) {
                    return
                }
                val directory = File(requireContext().filesDir.toString() + DIRECTORY)

                if (!directory.exists()) {
                    directory.mkdirs()
                }
                val file = File(directory, imageKey)

                try {
                    val outputStream: OutputStream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) = Unit
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) = Unit
        }

        Picasso.with(requireContext())
            .load(url)
            .into(target)
    }

    private fun readImageFromMemory(id: String) {
        val bitmap =
            BitmapFactory.decodeFile(requireContext().filesDir.toString() + DIRECTORY + id)
        imageView.setImageBitmap(bitmap)
    }

    private fun loadImage(id: String, url: String) {
        readImageFromMemory(id)

        if (!hasImage(imageView)) {
            Picasso.with(requireContext())
                .load(url)
                .placeholder(R.drawable.ic_baseline_perm_identity_24)
                .error(R.drawable.ic_baseline_perm_identity_24)
                .into(imageView)
            saveImage(id, url)
        }
    }

    private fun hasImage(view: ImageView): Boolean {
        val drawable = view.drawable
        var hasImage = drawable != null
        if (hasImage && drawable is BitmapDrawable) {
            hasImage = drawable.bitmap != null
        }
        return hasImage
    }
}
