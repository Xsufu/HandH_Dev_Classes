import kotlin.random.Random

/**
 * Класс описиывает свойства Игрока. Наследуется от класса [Creature]
 *
 * @param attack атака Игрока от 1 до 30
 * @param defence защита Игрока от 1 до 30
 * @param healsPower здоровье Игрока натуральное от 1 до N
 *
 * @property minDamage минимальный урон Игрока
 * @property maxDamage максимальный урон Игрока
 * @property currentHP текущее здоровье
 * @property maxHP максимальное здоровье
 * @property healsCount доступное количество исцелений
 */
class Player(attack: Int, defence: Int, healsPower: Int) : Creature(attack, defence, healsPower) {
    override val attack = attack
    override val defence = defence
    override val minDamage = Random.nextInt(1,5)
    override val maxDamage = Random.nextInt(5,10)
    override var currentHP = healsPower

    override val maxHP = healsPower
    private var healsCount = 4

    /**
     * Геттер атаки
     *
     * @return целое число - [attack] игрока
     */
    override fun getAtk(): Int {
        return attack
    }

    /**
     * Геттер защиты
     *
     * @return целое число - [defence] игрока
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

    /**
     * Функция исцеления
     *
     * @return строка с уведомлением об успехе/неудаче исцеления
     */
    fun heal(): String {
        // Если здоровье персонажа отрицательно - выводим сообщение
        return if (currentHP < 0) {
            "Игрок мёртв\n"
        }

        // Иначе если есть доступные исцеления
        else if (healsCount > 0) {
            // Запоминаем размер исцеления
            val incomingHeal = (maxHP * 0.3).toInt()
            // Если после исцеления здоровья станет больше, чем максимум
            currentHP = if ((incomingHeal + currentHP) > maxHP) {
                // Текущему здоровью приравниваем значение максимального здоровья
                maxHP
            } else {
                // Иначе увеличиваем на размер входящего исцеления
                incomingHeal + currentHP
            }
            // Уменьшить количество исцелений на 1
            healsCount--

            // Вернуть строку с сообщением об удаче
            "30% от максимального здоровья восстановлено\nТекущее здоровье игрока: $currentHP\n"
        } else { // Иначе сообщение о неудаче
            "Превышено количество возможных исцелений\n"
        }
    }

    /**
     * Метод нанесения удара по противнику
     *
     * @param enemy экземпляр класса монстра
     */
    fun hit(enemy: Monster) {
        // Если персонаж умер - выходим из метода с сообщением
        if (currentHP < 0) {
            println("Игрок мёртв\n")
            return
        } else if (enemy.getHP() < 0) {
            println("Монстр уже мёртв\n")
            return
        }

        // Вычисляем модификатор атаки
        val atkModifier = if ((attack - enemy.getDef()) <=  0) {
            1 // Если разница отрицательная - бросаем один кубик
        } else { // Иначе считаем количество кубиков
            attack - enemy.getDef()
        }
        // В список сохраняем atkModifier бросков кубика
        val diceRolls = List(atkModifier) { Random.nextInt(1,7) }
        // Проверяем есть ли в списке 5 или 6
        val isHitSuccess = (diceRolls.contains(5) || diceRolls.contains(6))

        // В случае удачи
        val damage = if (isHitSuccess) {
            // Вычисляем размер урона
            Random.nextInt(minDamage, maxDamage+1)
        } else { // Иначе 0
            0
        }

        // Отправляем урон противнику
        enemy.takeDamage(damage)
    }

    /**
     * Функция получения урона
     *
     * @param damage размер входящего урона
     */
    override fun takeDamage(damage: Int){
        // Отнимаем от здоровья входящий урон
        currentHP -= damage
        // Если текущее здоровье отрицательно - сообщение о летальном уроне
        val output = if (currentHP <= 0) {
            "Игрок получил летальный урон \n"
        } else { // Иначе полученный урон и оставшийся запас здоровья
            "Игрок получил $damage урона. \nТекущее здоровье игрока: $currentHP \n"
        }

        // Выводим исход удара на экран
        println(output)
    }
}