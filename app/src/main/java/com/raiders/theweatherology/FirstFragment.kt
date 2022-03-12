package com.raiders.theweatherology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.button).setOnClickListener {

                    Navigation.findNavController(it).navigate(
                        R.id.action_FirstFragment_to_SecondFragment,
                        bundleOf("message" to editCity.text.toString())
                    )
                }

        }

    }
