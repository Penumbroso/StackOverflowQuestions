package io.schiar.tcc.model

/**
 * Representa um usuario que fez um Post no StackOverflow.
 * @property profile_image avatar do criado do post.
 * @property display_name nome do criador do post.
 */
data class Owner(
        val profile_image: String,
        val display_name: String
)