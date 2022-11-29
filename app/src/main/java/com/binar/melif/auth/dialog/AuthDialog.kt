package com.binar.melif.auth.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.binar.melif.R
import com.binar.melif.databinding.DialogAuthBinding

class AuthDialog : DialogFragment() {
    private lateinit var binding: DialogAuthBinding

    private var listener: OnMenuSelectedListener? = null

    fun setOnMenuSelectedListener(listener: OnMenuSelectedListener){
        this@AuthDialog.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DialogAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.llLoginButton.setOnClickListener {
            listener?.onLoginClicked(this)
        }
    }


}

interface OnMenuSelectedListener{
    fun onLoginClicked(dialog: DialogFragment)
}