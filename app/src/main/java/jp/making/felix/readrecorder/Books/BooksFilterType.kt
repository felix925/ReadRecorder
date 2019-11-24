package jp.making.felix.readrecorder.Books

enum class BooksFilterType {
    /**
     * 並べ替えを行わないデフォルトのフィルター
     */
    ALL_BOOKS,

    /**
     * 読んでいる途中の本を表示する
     */
    ACTIVE_BOOKS,

    /**
     * 読み終わった本を表示する
     */

    COMPLETED_BOOKS
}