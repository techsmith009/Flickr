package com.example.flickrapplication.model
import com.google.gson.annotations.SerializedName

data class FlickrContents(
@SerializedName("title"       ) var title       : String          = "",
@SerializedName("link"        ) var link        : String          = "",
@SerializedName("description" ) var description : String          = "",
@SerializedName("modified"    ) var modified    : String          = "",
@SerializedName("generator"   ) var generator   : String          = "",
@SerializedName("items"       ) var items       : ArrayList<Item> = arrayListOf()
)

data class Media (
    @SerializedName("m" ) var m : String = ""
)

data class Item (
    @SerializedName("title"       ) var title       : String = "",
    @SerializedName("link"        ) var link        : String = "",
    @SerializedName("media"       ) var media       : Media?  = null,
    @SerializedName("date_taken"  ) var dateTaken   : String = "",
    @SerializedName("description" ) var description : String = "",
    @SerializedName("published"   ) var published   : String = "",
    @SerializedName("author"      ) var author      : String = "",
    @SerializedName("author_id"   ) var authorId    : String = "",
    @SerializedName("tags"        ) var tags        : String = ""
)