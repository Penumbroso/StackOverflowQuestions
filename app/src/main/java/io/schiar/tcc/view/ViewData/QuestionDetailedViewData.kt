package io.schiar.tcc.view.ViewData

import io.schiar.tcc.utilities.BitmapLoader

/**
 * Representação de uma questão detalhada do ponto de vista da View.
 * @property title titulo do Post.
 * @property display_name nome do usuario que criou o Post.
 * @property profile_image imagem do usuario que criou o Post.
 * @property link link para a questão no StackOverflow.
 * @property body corpo do texto do post.
 * @property creation_date data na qual o Post foi criado.
 */
data class QuestionDetailedViewData(
        val title: String,
        val display_name: String,
        val profile_image: BitmapLoader,
        val link: String,
        val body: String,
        val creation_date : String
)