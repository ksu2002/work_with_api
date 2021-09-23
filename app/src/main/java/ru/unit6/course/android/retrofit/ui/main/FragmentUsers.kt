package ru.unit6.course.android.retrofit

import android.R.attr
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.ui.main.MainAdapter.Companion.KEY_ID
import ru.unit6.course.android.retrofit.ui.main.MainViewModel
import ru.unit6.course.android.retrofit.ui.main.UserViewModel
import ru.unit6.course.android.retrofit.utils.Status

class FragmentUsers : Fragment(R.layout.fragment_user) {
    private lateinit var viewModel: UserViewModel
  //  val textemail = view?.findViewById<TextView>(R.id.text_email)
   // val textname by lazy{ view?.findViewById<TextView>(R.id.text_name) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val textview = view.findViewById<TextView>(R.id.text_view)

        val bundle = arguments
        val id = bundle?.getString(KEY_ID) ?: "fignya"

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.loadDataOfUser(id)
        setupObservers()
    }

    private fun setupObservers() {

        viewModel.liveData.observe(viewLifecycleOwner) { user ->
            if (user != null) {
               // val im =ImageView
                requireView().findViewById<TextView>(R.id.text_email)?.text = "email: "+user.email //==view?
                view?.findViewById<TextView>(R.id.text_name)?.text = "name: "+user.name
              //  view?.findViewById<ImageView>(R.id.image_avatar)?. = user.avatar
                val imageViewAvatar = view?.findViewById<ImageView>(R.id.image_avatar)
                if (imageViewAvatar != null) {
                    Glide.with(imageViewAvatar.context)
                        .load(user.avatar)
                        .into(imageViewAvatar)
                }
            }
        }
    }


}
