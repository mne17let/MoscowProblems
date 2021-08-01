package com.example.moscowproblems.FragmentClasses

import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moscowproblems.Models.ProblemModel
import com.example.moscowproblems.R
import com.example.moscowproblems.ViewModels.ProblemFragmentViewModel
import java.util.*

private const val STRING_TAG_FOR_DATE_DIALOG = "Date dialog"
private const val STRING_TAG_FOR_TIME_DIALOG = "Time dialog"
private const val STRING_FOR_KEY_FOR_TARGET_FRAGMENT = "key"
private const val STRING_FOR_KEY_FOR_TARGET_TIME_FRAGMENT = "key time"
private const val STRING_FOR_KEY_FOR_BUNDLE_FROM_DATEPICKER_FRAGMENT = "key for bundle"

private const val STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_HOUR = "key for bundle with hour"
private const val STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_MINUTE = "key for bundle with minutes"

private const val TAG_FOR_TIME_DEBUG = "TimeDebug"

private const val DATE_FORMAT = "EEE, MMM, dd"

class ProblemFragment: Fragment(){

    private lateinit var problemData: ProblemModel

    private lateinit var editTextForTitleVar: EditText
    private lateinit var buttonWithDataVar: Button
    private lateinit var checkBoxSolveProblemVar: CheckBox
    private lateinit var buttonPickTimeVar: Button
    private lateinit var buttonChooseExecutorVar: Button
    private lateinit var buttonSendReportVar: Button

    private lateinit var problemId: UUID

    private val viewModelForProblemFragment: ProblemFragmentViewModel by lazy {
        ViewModelProvider(this).get(ProblemFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        problemId = arguments?.getSerializable("Problem_id") as UUID
        viewModelForProblemFragment.loadProblem(problemId)

        childFragmentManager.setFragmentResultListener(STRING_FOR_KEY_FOR_TARGET_FRAGMENT, this, object :
            FragmentResultListener {
            override fun onFragmentResult(requestKey: String, result: Bundle) {
                val newDateForProblem = result.getSerializable(STRING_FOR_KEY_FOR_BUNDLE_FROM_DATEPICKER_FRAGMENT)
                problemData.date = newDateForProblem as Date
                updateUI()
            }

        })


        childFragmentManager.setFragmentResultListener("мой ключ времени", this, object :
            FragmentResultListener {
            override fun onFragmentResult(requestKey: String, result: Bundle) {

                val newHour = result.getInt(STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_HOUR)
                val newMinute = result.getInt(STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_MINUTE)

                Log.d(TAG_FOR_TIME_DEBUG, "Get data from bundle" +
                        "current time is $newHour:$newMinute")

                updateTimeOnButton(newHour, newMinute)
            }

        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewOfFragment = inflater.inflate(R.layout.fragment_problem, container, false)

        editTextForTitleVar = viewOfFragment.findViewById(R.id.id_edittext_title)
        buttonWithDataVar = viewOfFragment.findViewById(R.id.id_button_with_date)
        checkBoxSolveProblemVar = viewOfFragment.findViewById(R.id.id_checkbox_problem_solved)
        buttonPickTimeVar = viewOfFragment.findViewById(R.id.id_button_pick_time)
        buttonChooseExecutorVar = viewOfFragment.findViewById(R.id.id_button_choose_executor)
        buttonSendReportVar = viewOfFragment.findViewById(R.id.id_button_send_report)


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


    override fun onStart() {
        super.onStart()

        setTextWatchetOnEditText()
        setListenerOnCheckBox()
        workWithDateButton()
        setListenerOnPickTimeButton()
        setListenerOnSendReportButton()

    }

    fun setListenerOnSendReportButton(){
        buttonSendReportVar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val newIntent = Intent(Intent.ACTION_SEND)
                newIntent.type = "text/plain"
                newIntent.putExtra(Intent.EXTRA_TEXT, getStringProblemReport())
                newIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.string_problem_report_app_name)

                val intentWithChooseList = Intent.createChooser(newIntent, getString(R.string.string_send_report_for))

                startActivity(intentWithChooseList)
            }

        })
    }

    fun workWithDateButton(){
        buttonWithDataVar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val argsForDatePickerFragment = Bundle()
                argsForDatePickerFragment.putSerializable("Дата текущей проблемы", problemData.date)

                val newDatePickerFragment = DatePickerFragment()
                newDatePickerFragment.arguments = argsForDatePickerFragment
                newDatePickerFragment.show(this@ProblemFragment.childFragmentManager, STRING_TAG_FOR_DATE_DIALOG)
            }
        })
    }

    fun setListenerOnPickTimeButton(){
       buttonPickTimeVar.setOnClickListener(object : View.OnClickListener{
           override fun onClick(v: View?) {
               val newTimePickerDialogFragment = TimePickerFragment()
               newTimePickerDialogFragment.show(this@ProblemFragment.childFragmentManager, STRING_TAG_FOR_TIME_DIALOG)
           }

       })
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

    fun updateTimeOnButton(hour: Int, minute: Int){
        val newTextForButton = "Текущее время $hour:$minute"
        buttonPickTimeVar.text = newTextForButton
    }

    fun getStringProblemReport(): String {
        val isSolvedProblem = if (problemData.isSolved){
            getString(R.string.string_problem_report_solved)
        } else {
            getString(R.string.string_problem_report_unsolved)
        }

        val dateString = android.text.format.DateFormat.format(DATE_FORMAT, problemData.date).toString()

        val forExecutor = if (problemData.executor.isBlank()){
            getString(R.string.string_problem_report_no_executor)
        } else {
            getString(R.string.string_problem_report_have_executor, problemData.executor)
        }

        val report = getString(R.string.string_full_report, problemData.title, dateString, isSolvedProblem, forExecutor)

        return report
    }
}