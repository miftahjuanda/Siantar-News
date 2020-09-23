package com.udacoding.siantarnews.model

class News(var source: Source?) {
    var author : String? = null
    var title : String? = null
    var description : String? = null
    var urlToImage : String? = null
    var content : String? = null
    var publishedAt : String? = null
    var url : String? = null

    class Source() {
        var name : String? = null
    }
}