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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R
import com.example.moscowproblems.ViewModels.ProblemFragmentViewModel
import java.util.*

class ProblemFragment: Fragment(){

    private lateinit var problemData: ProblemModel

    private lateinit var editTextForTitleVar: EditText
    private lateinit var buttonWithDataVar: Button
    private lateinit var checkBoxSolveProblemVar: CheckBox

    private lateinit var problemId: UUID

    private val viewModelForProblemFragment: ProblemFragmentViewModel by lazy {
        ViewModelProvider(this).get(ProblemFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        problemId = arguments?.getSerializable("Problem_id") as UUID
        viewModelForProblemFragment.loadProblem(problemId)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewOfFragment = inflater.inflate(R.layout.fragment_problem, container, false)

        editTextForTitleVar = viewOfFragment.findViewById(R.id.id_edittext_title)
        buttonWithDataVar = viewOfFragment.findViewById(R.id.id_button_with_date)
        checkBoxSolveProblemVar = viewOfFragment.findViewById(R.id.id_checkbox_problem_solved)


        return viewOfFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myObserver: MyObserver = MyObserver()
        viewModelForProblemFragment.problemModelLiveData.observe(viewLifecycleOwner, myObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModelForProblemFragment.saveProblem(problemData)
    }

    inner class MyObserver: Observer<ProblemModel> {
        override fun onChanged(t: ProblemModel?) {
            if (t != null) {
                problemData = t
                updateUI()
            }
        }
    }

    fun updateUI(){
        editTextForTitleVar.setText(problemData.title)
        buttonWithDataVar.text = problemData.date.toString()
        checkBoxSolveProblemVar.isChecked = problemData.isSolved
        checkBoxSolveProblemVar.jumpDrawablesToCurrentState()

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

            }

            override fun onTextChanged(chars: CharSequence?, start: Int, before: Int, count: Int) {
                problemData.title = chars.toString()
            }

            override fun afterTextChanged(s: Editable?) {

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