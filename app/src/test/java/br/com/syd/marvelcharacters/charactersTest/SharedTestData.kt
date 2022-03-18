package br.com.syd.marvelcharacters.charactersTest

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel

val mockCharacterModel = CharacterModel(
    1017100,
    "A-Bomb (HAS)",
    "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! ",
    "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg",
    listOf(),
    listOf(),
    true
)

val mockFavoriteCharacterModel = FavoriteCharacterModel(
    1017100,
    "A-Bomb (HAS)",
    "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/portrait_medium.jpg"
)

val mockTestCharacter = CharacterModel(
    0,
    "Character test name",
    "Description test",
    "picture test",
    listOf(),
    listOf(),
    false
)

val mockTestFavorite = FavoriteCharacterModel(
    0,
    "Character test name",
    "picture test"
)

val mockCharacterDataResultComicsItem = CharacterResponse.Data.Result.Comics.Item(
    "http://gateway.marvel.com/v1/public/comics/21366",
    "Avengers: The Initiative (2007) #14"
)

val mockCharacterDataResultComics = CharacterResponse.Data.Result.Comics(
    12,
    "http://gateway.marvel.com/v1/public/characters/1011334/comics",
    listOf(),
    12
)
val mockCharacterDataResultSeries = CharacterResponse.Data.Result.Series(
    3,
    "http://gateway.marvel.com/v1/public/characters/1011334/series",
    listOf(),
    3
)
val mockCharacterDataResultEvents = CharacterResponse.Data.Result.Events(
    1,
    "http://gateway.marvel.com/v1/public/characters/1011334/events",
    listOf(),
    1
)

val mockCharacterDataResultStories = CharacterResponse.Data.Result.Stories(
    54,
    "http://gateway.marvel.com/v1/public/characters/1009144/stories",
    listOf(),
    54
)
val mockCharacterDataResultThumbnail = CharacterResponse.Data.Result.Thumbnail(
    "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
    "jpg"
)

val mockCharacterData = CharacterResponse.Data(
    20,
    20,
    0,
    listOf(),
    1560
)

val mockCharacterResponse = CharacterResponse(
    "<a href=\"http://marvel.com\">Data provided by Marvel. © 2022 MARVEL</a>",
    "a320478634ce87feaf8b2a1c25037fcb992a4bc1",
    200,
    "© 2022 MARVEL",
    mockCharacterData,
    "a320478634ce87feaf8b2a1c25037fcb992a4bc1",
    "Ok"
)

val mockCharacterDataResult = CharacterResponse.Data.Result(
    mockCharacterDataResultComics,
    "",
    mockCharacterDataResultEvents,
    1011334,
    "2013-10-17T14:41:30-0400",
    "3-D Man",
    "http://gateway.marvel.com/v1/public/characters/1009144",
    mockCharacterDataResultSeries,
    mockCharacterDataResultStories,
    mockCharacterDataResultThumbnail,
    listOf()
)

val mockCharacterDataResultUrl = CharacterResponse.Data.Result.Url(
    "wiki",
    "http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=0f7cc1fac733b5803681cb23124041d9"
)