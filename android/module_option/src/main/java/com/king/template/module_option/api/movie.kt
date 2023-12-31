package com.king.template.module_option.api

import dev.utils.common.StringUtils


/**
 * 电影详情
 */
data class MovieDetails(
    val backdrop_path: String,
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
) {

    fun title(): String? {
        return StringUtils.checkValues(title, original_title)
    }

    fun imagePath(): String? {
        return StringUtils.checkValues(backdrop_path, poster_path)
    }
}

/**
 * 电影海报图片集合
 */
data class MoviePosterImages(
    val posters: List<Poster>
)

/**
 * 电影海报图片
 */
data class Poster(
    val file_path: String
)