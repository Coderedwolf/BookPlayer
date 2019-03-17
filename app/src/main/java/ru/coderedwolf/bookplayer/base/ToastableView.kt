package ru.iway.iwayapp.base

interface ToastableView {

    /**
     * Show toast on view, default time of show is short
     */
    fun showToast(res: Int)

    /**
     * Show toast on view use message string, default time of show is short
     */
    fun showToast(message: String)
}