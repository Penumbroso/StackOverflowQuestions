package io.schiar.tcc.model.repository

import android.util.Log
import com.beust.klaxon.Klaxon
import io.schiar.tcc.model.Question
import io.schiar.tcc.model.QuestionDetailed
import okhttp3.*
import java.io.IOException

/**
 *
 * @property Questions lista de posts.
 * @property QuestionDetailed lista contendo um post detalhado
 * Numa aplicação real, esses objetos viriam de uma camada de dados da aplicação.
 */
class QuestionsRepository: QuestionsRepositoryInterface {

    data class Questions(val items: List<Question>)
    data class QuestionsDetailed(val items : List<QuestionDetailed>)

    /**
     * Busca a lista de posts do StackOverflow utilizando a API 2.2.
     * @param callback usado para receber a lista de Questions buscada.
     */
    override fun fetchList(callback: (List<Question>) -> Unit) {
        val url = "https://api.stackexchange.com/2.2/questions?pagesize=15&order=desc&sort=activity&site=stackoverflow&filter=!2.dt38COXaTC8PKF4dT6a"

        var request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()!!.string()
                val parsedQuestions = Klaxon().parse<Questions>(body)

                return callback(parsedQuestions!!.items)
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.d("ERROR", "Failure to execute")
            }
        })
    }

    /**
     * Busca um posto detalhado utilizando o id do post selecionado.
     * @param id usado para buscar um post especifico.
     * @param callback usado para receber o post detalhado.
     */
    override fun fetchDetails(id: Int, callback: (QuestionDetailed) -> Unit) {
        val url = "https://api.stackexchange.com/2.2/posts/$id?order=desc&sort=activity&site=stackoverflow&filter=!)4k*p.-WsDa)4Mn1qHrGh.AmWPh9"

        var request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()!!.string()
                val parsedQuestions = Klaxon().parse<QuestionsDetailed>(body)

                return callback(parsedQuestions!!.items[0])
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.d("ERROR", "Failure to execute")
            }
        })
    }

    companion object {
        val instance: QuestionsRepositoryInterface = QuestionsRepository()
    }

}

