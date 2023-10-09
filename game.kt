import kotlin.random.Random

fun main() {
    val player1 = Player(Random.nextInt(1, 31), Random.nextInt(1, 31), Random.nextInt(1, 61))
    val monster1 = Monster(Random.nextInt(1, 31), Random.nextInt(1, 31), Random.nextInt(1, 2))

    println("""
        ######  Player Stats  ######
        HP:  ${player1.getHP()}
        Atk: ${player1.getAtk()}
        Def: ${player1.getDef()}
        Dmg: ${player1.getMinDmg()} - ${player1.getMaxDmg()}
    """.trimIndent())

    println()

    println("""
        ######  Monster Stats  ######
        HP: ${monster1.getHP()}
        Atk: ${monster1.getAtk()}
        Def: ${monster1.getDef()}
        Dmg: ${monster1.getMinDmg()} - ${monster1.getMaxDmg()}
    """.trimIndent())

    // Игрок атакует монстра
    player1.hit(monster1)

    // Монстр атакует в ответ
    monster1.hit(player1)

    // Игрок использует исцеление и атакует в ответ
    println(player1.heal())
    player1.hit(monster1)

    // Монстр атакует игрока
    monster1.hit(player1)
}