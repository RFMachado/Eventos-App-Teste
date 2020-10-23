package br.com.teste.sicredi.util.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import br.com.teste.sicredi.R
import br.com.teste.sicredi.util.extension.toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_checkin_view.*

class CheckinBottomSheetDialog : BottomSheetDialogFragment() {

    var listener: CheckinListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_checkin_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindListener()
    }

    private fun bindListener() {
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnCheckIn.setOnClickListener {
            when {
                isNotEmailValid() -> {
                    edtEmail.error = "Email Invalido"
                }
                edtName.text.isNullOrEmpty() ||  edtEmail.text.isNullOrEmpty() -> {
                    context?.toast(getString(R.string.error_check_in_fields))
                }
                else -> {
                    listener?.onClickCheckin(
                        name = edtName.text.toString(),
                        email = edtEmail.text.toString()
                    )

                    dismiss()
                }
            }
        }
    }

    private fun isNotEmailValid(): Boolean {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()
    }

    fun show(manager: FragmentManager) {
        super.show(manager, CheckinBottomSheetDialog::class.java.name)
    }

    interface CheckinListener {
        fun onClickCheckin(name: String, email: String)
    }

}