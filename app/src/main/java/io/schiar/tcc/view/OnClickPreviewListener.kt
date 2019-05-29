package io.schiar.tcc.view

import android.view.View

/**
 * Define listener de quando um item de lista de questões é selecionado.
 */
interface OnClickPreviewListener {
    /**
     * Evento de clique de um item da lista de questões.
     * @param index índice do item.
     * @param view componente [View] do item.
     */
    fun onPreviewClick(index: Int, view: View)
}