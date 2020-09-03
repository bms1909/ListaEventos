package com.brunomusskopf.listaeventos.presentation.base

import androidx.databinding.BindingAdapter
import com.brunomusskopf.listaeventos.domain.base.StringValidationStatus
import com.google.android.material.textfield.TextInputLayout

object DataBindingAdapters {

    /**
     * Utilitário para exibição do '!' de erro em [TextInputLayout] com base no [CodigoValidacao], definindo os textos padrão e limpando a notificação
     */
    @BindingAdapter("custom_binding_hint_error")
    @JvmStatic
    fun textInputLayoutErrorBindingAdapter(et: TextInputLayout, code: StringValidationStatus?) {
        when (code) {
            StringValidationStatus.EMPTY -> {
                et.error = "Campo Obrigatório"
            }
            StringValidationStatus.INVALID -> {
                et.error ="Campo inválido"
            }
            else -> {
                et.error = null
                et.isErrorEnabled = false
            }
        }
    }
}