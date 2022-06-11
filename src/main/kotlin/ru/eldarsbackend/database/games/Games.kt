package ru.eldarsbackend.database.games

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.eldarsbackend.database.users.UserDTO
import ru.eldarsbackend.database.users.Users

object Games: Table("games") {
    private var gameId = Games.varchar("gameid", 100)
    private var name = Games.varchar("name", 100)
    private var backdrop = Games.varchar("backdrop", 50).nullable()
    private var logo = Games.varchar("backdrop", 50)
    private var description = Games.varchar("backdrop", 500)
    private var downloadCount = Games.integer("download_count")
    private var version = Games.varchar("version", 15)
    private var weight = Games.varchar("weight", 10)

    fun insert(gameDTO: GameDTO) {
        transaction {
            Games.insert {
                it[gameId] = gameDTO.gameId
                it[name] = gameDTO.name
                it[description] = gameDTO.description
                it[version] = gameDTO.version
                it[weight] = gameDTO.weight
            }
        }
    }

    fun fetchGames(): List<GameDTO> {
        return try {
            transaction {
                Games.selectAll().toList()
                    .map {
                        GameDTO(
                            gameId = it[gameId],
                            name = it[name],
                            description = it[description],
                            version = it[version],
                            weight = it[weight]
                        )
                    }
            }
        } catch (e: Exception){
            emptyList()
        }
    }
}