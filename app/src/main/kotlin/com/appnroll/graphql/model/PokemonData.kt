package com.appnroll.graphql.model


data class PokemonData(
    val pokemon: Pokemon
)

data class Pokemon(
    val id: String,
    val number: String,
    val name: String,
    val attacks: Attacks,
    val evolutions: List<Evolution>?
)

data class Attacks(
    val special: List<Attack>,
    val fast: List<Attack>
)

data class Attack(
    val name: String,
    val type: String,
    val damage: Int
)

data class Evolution(
    val id: String,
    val number: String,
    val name: String,
    val weight: Weight,
    val attacks: Attacks
)

data class Weight(
    val minimum: String,
    val maximum: String
)