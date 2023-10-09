import kotlin.random.Random

/**
 * Класс описиывает свойства Монстра. Наследуется от класса [Creature]
 *
 * @param attack атака Монстра от 1 до 30
 * @param defence защита Монстра от 1 до 30
 * @param healsPower здоровье Монстра натуральное от 1 до N
 *
 * @property minDamage минимальный урон Монстра
 * @property maxDamage максимальный урон Монстра
 * @property currentHP текущее здоровье
 * @property maxHP максимальное здоровье
 */
class Monster(attack: Int, defence: Int, healsPower: Int) : Creature(attack, defence, healsPower){
    override val attack = attack
    override val defence = defence
    override val minDamage = Random.nextInt(1,5)
    override val maxDamage = Random.nextInt(5,10)
    override var currentHP = healsPower

    override val maxHP = healsPower

    /**
     * Геттер атаки
     *
     * @return целое число - [attack] монстра
     */
    override fun getAtk(): Int {
        return attack
    }

    /**
     * Геттер защиты
     *
     * @return целое число - [defence] монстра
     */
    override fun getDef(): Int {
        return defence
    }

    /**
     * Геттер здоровья
     *
     * @return целое число - текущее здоровье
     */
    override fun getHP(): Int {
        return currentHP
    }

    /**
     * Геттер минимального урона
     *
     * @return целое число - минимальный урон
     */
    override fun getMinDmg(): Int {
        return minDamage
    }


    /**
     * Геттер максимального урона
     *
     * @return целое число - максимальный урон
     */
    override fun getMaxDmg(): Int {
        return maxDamage
    }

    fun hit(enemy: Player) {
        if (currentHP < 0) {
            println("Монстр мёртв\n")
            return
        } else if (enemy.getHP() < 0) {
            println("Игрок уже мёртв\n")
            return
        }

        val atkModifier = if ((attack - enemy.getDef()) <= 0) {
            1
        } else {
            attack - enemy.getDef()
        }
        val diceRolls = List(atkModifier) { Random.nextInt(1,7) }

        val isHitSuccess = (diceRolls.contains(5) || diceRolls.contains(6))

        val damage = if (isHitSuccess) {
            Random.nextInt(minDamage, maxDamage+1)
        } else {
            0
        }

        enemy.takeDamage(damage)
    }

    override fun takeDamage(damage: Int) {
        currentHP -= damage
        val output = if (currentHP <= 0) {
            "Монстр получил летальный урон\n"
        } else {
            "Монстр получил $damage урона. \nТекущее здоровье монстра: $currentHP \n"
        }

        println(output)
    }

}