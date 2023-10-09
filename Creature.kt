abstract class Creature(attack: Int, defence: Int, healsPower: Int) {
    protected abstract val attack: Int
    protected abstract val defence: Int
    protected abstract val minDamage: Int
    protected abstract val maxDamage: Int
    protected abstract var currentHP: Int

    protected abstract val maxHP: Int

    abstract fun getAtk(): Int
    abstract fun getDef(): Int
    abstract fun getHP(): Int
    abstract fun getMinDmg(): Int
    abstract fun getMaxDmg(): Int


    abstract fun takeDamage(damage: Int)
}