package com.brunomusskopf.listaeventos.presentation

import androidx.databinding.BindingAdapter
import com.brunomusskopf.listaeventos.domain.base.ValidacaoStringModel
import com.google.android.material.textfield.TextInputLayout

object DataBindingAdapters {

    /**
     * Utilitário para exibição do '!' de erro em [TextInputLayout] com base no [CodigoValidacao], definindo os textos padrão e limpando a notificação
     */
    @BindingAdapter("custom_binding_hint_error")
    @JvmStatic
    fun textInputLayoutErrorBindingAdapter(et: TextInputLayout, codigo: ValidacaoStringModel?) {
        when (codigo) {
            ValidacaoStringModel.VAZIO -> {
                et.error = "Campo Obrigatório"
            }
            ValidacaoStringModel.INVALIDO -> {
                et.error ="Campo inválido"
            }
            else -> {
                et.error = null
                et.isErrorEnabled = false
            }
        }
    }
}