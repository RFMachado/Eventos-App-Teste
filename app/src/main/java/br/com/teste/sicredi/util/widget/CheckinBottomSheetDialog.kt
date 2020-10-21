package br.com.teste.sicredi.util.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import br.com.teste.sicredi.R
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
            listener?.onClickCheckin(
                name = edtName.text.toString(),
                email = edtName.text.toString()
            )
        }
    }

    fun show(manager: FragmentManager) {
        super.show(manager, CheckinBottomSheetDialog::class.java.name)
    }

    interface CheckinListener {
        fun onClickCheckin(name: String, email: String)
    }

}