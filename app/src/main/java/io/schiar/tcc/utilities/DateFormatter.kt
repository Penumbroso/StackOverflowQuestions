package io.schiar.tcc.utilities

import java.text.DateFormat
import java.util.*

/**
 * Fornece funções para formatação de datas.
 */
class DateFormatter {
    /**
     * Formata uma data em milissegundos desde o unixtime para uma representação em string seguindo o locale do sistema.
     * @param millisecondsSinceEpoch data em milisegundos desde o unixtime.
     * @return a data formatada em string seguindo o locale do sistema.
     */
    fun dateString(millisecondsSinceEpoch: Long): String {
        val date = Date(millisecondsSinceEpoch * 1000)
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        return formatter.format(date)
    }
}