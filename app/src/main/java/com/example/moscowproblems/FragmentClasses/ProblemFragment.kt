package com.example.moscowproblems.FragmentClasses

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R

class ProblemFragment: Fragment(){

    private lateinit var problemData: ProblemModel

    private lateinit var editTextForTitleVar: EditText
    private lateinit var buttonWithDataVar: Button
    private lateinit var checkBoxSolveProblemVar: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        problemData = ProblemModel()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewOfFragment = inflater.inflate(R.layout.fragment_problem, container, false)

        editTextForTitleVar = viewOfFragment.findViewById(R.id.id_edittext_title)
        buttonWithDataVar = viewOfFragment.findViewWithTag(R.id.id_button_with_date)
        checkBoxSolveProblemVar = viewOfFragment.findViewById(R.id.id_checkbox_problem_solved)


        return viewOfFragment
    }

    fun workWithButton(){
        buttonWithDataVar.apply {
            text = problemData.date.toString()
            isEnabled = false
        }
    }



    override fun onStart() {
        super.onStart()

        setTextWatchetOnEditText()
        setListenerOnCheckBox()

    }

    fun setTextWatchetOnEditText(){
        val editTextTitleWatcher: TextWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(chars: CharSequence?, start: Int, before: Int, count: Int) {
                problemData.title = chars.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        }

        editTextForTitleVar.addTextChangedListener(editTextTitleWatcher)
    }

    fun setListenerOnCheckBox(){

        val listenerForCheckBox = object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                problemData.isSolved = isChecked
            }

        }

        checkBoxSolveProblemVar.setOnCheckedChangeListener(listenerForCheckBox)
    }


}