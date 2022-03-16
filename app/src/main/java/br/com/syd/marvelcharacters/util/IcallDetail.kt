package br.com.syd.marvelcharacters.util

import br.com.syd.marvelcharacters.domain.model.CharacterModel

interface IcallDetail {
    fun callDetail(characterModel: CharacterModel)
}
