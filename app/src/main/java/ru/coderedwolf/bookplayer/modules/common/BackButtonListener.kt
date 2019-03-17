package ru.coderedwolf.bookplayer.modules.common


interface BackButtonListener {

    /**
     * Return false, if not call activity onBackPressed()
     */
    fun onBackPressed(): Boolean
}