package com.binar.melif.uimodel

import androidx.annotation.StringRes
import com.binar.melif.data.network.model.MovieResultModel

const val TV_SHOW_TYPE_HEADER = 0
const val TV_SHOW_TYPE_SECTION = 1

sealed class MovieItem(val type: Int){
    class MovieHeaderItem(val data: MovieResultModel?):
        MovieItem(TV_SHOW_TYPE_HEADER)
    class MovieSectionItem(@StringRes val sectionName: Int, val data:
    List<MovieResultModel>?): MovieItem(
        TV_SHOW_TYPE_SECTION)
}