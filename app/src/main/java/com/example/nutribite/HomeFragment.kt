package com.example.nutribite
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.example.nutribite.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoButton = view.findViewById<ImageButton>(R.id.info)
        infoButton.setOnClickListener {
            val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.popup_layout, null)
            val popupWindow = PopupWindow(
                popupView,
                (requireActivity().resources.displayMetrics.widthPixels * 0.85).toInt(),
                (requireActivity().resources.displayMetrics.heightPixels * 0.80).toInt()
            )
            popupWindow.showAtLocation(it, Gravity.CENTER, 0, 0)

            val closeButton = popupView.findViewById<Button>(R.id.closeButton)
            closeButton.setOnClickListener {
                // Handle close button click
                popupWindow.dismiss()
            }
        }
    }
}
