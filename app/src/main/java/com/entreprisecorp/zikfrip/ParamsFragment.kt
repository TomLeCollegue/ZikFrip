package com.entreprisecorp.zikfrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.entreprisecorp.zikfrip.storage.SessionStorage
import com.entreprisecorp.zikfrip.storage.SessionStorage.Singleton.firstnameSession
import kotlinx.android.synthetic.main.fragment_params.*
import com.entreprisecorp.zikfrip.storage.SessionStorage.Singleton.nameSession as nameSession

class ParamsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.findViewById<TextView>(R.id.textSubtitle)?.text = "Mes informations"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_params, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SessionStorage.getInfoSession(activity as MainActivity)

        textNameInput.setText(nameSession)
        textFirstNameInput.setText(firstnameSession)

        buttonConfirm.setOnClickListener {
            SessionStorage.putInfoSession(activity as MainActivity, textNameInput.text.toString(), textFirstNameInput.text.toString())
            Navigation.findNavController(it)
                    .navigate(R.id.action_global_homeFragment);
        }
    }




}