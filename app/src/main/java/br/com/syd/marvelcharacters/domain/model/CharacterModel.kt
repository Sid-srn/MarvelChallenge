package br.com.syd.marvelcharacters.domain.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    var id: Int,
    var name: String,
    var description: String,
    var picture: String,
    var comics: List<String>,
    var series: List<String>,
    var isFavority: Boolean
    //var modified: String,
    //var thumbnail: Thumbnail,
    //var resourceURI: String,
    //var comics: Comics,
    //var series: Series,
    //var stories: Stories,
    //var events: Events,
    //@SerializedName("urls")
    //var urls : List<Urls>

) : Parcelable