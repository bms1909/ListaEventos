package com.brunomusskopf.listaeventos.presentation.base

import androidx.databinding.BindingAdapter
import com.brunomusskopf.listaeventos.domain.base.StatusValidacaoString
import com.google.android.material.textfield.TextInputLayout

object DataBindingAdapters {

    /**
     * Utilitário para exibição do '!' de erro em [TextInputLayout] com base no [CodigoValidacao], definindo os textos padrão e limpando a notificação
     */
    @BindingAdapter("custom_binding_hint_error")
    @JvmStatic
    fun textInputLayoutErrorBindingAdapter(et: TextInputLayout, codigo: StatusValidacaoString?) {
        when (codigo) {
            StatusValidacaoString.VAZIO -> {
                et.error = "Campo Obrigatório"
            }
            StatusValidacaoString.INVALIDO -> {
                et.error ="Campo inválido"
            }
            else -> {
                et.error = null
                et.isErrorEnabled = false
            }
        }
    }
}