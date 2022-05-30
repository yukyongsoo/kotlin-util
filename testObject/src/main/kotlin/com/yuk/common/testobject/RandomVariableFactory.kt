package com.yuk.common.testobject

import com.github.javafaker.Faker
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.jeasy.random.randomizers.range.DoubleRangeRandomizer
import org.jeasy.random.randomizers.range.FloatRangeRandomizer
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer
import org.jeasy.random.randomizers.range.LongRangeRandomizer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.random.Random

object RandomVariableFactory {
    val random: EasyRandom
    var faker: Faker

    init {
        val param = EasyRandomParameters()
            .stringLengthRange(1, 10)
            .randomizationDepth(10)
            .collectionSizeRange(1, 10)
            .scanClasspathForConcreteTypes(true)
            .randomize(Int::class.javaPrimitiveType, IntegerRangeRandomizer(1, 100))
            .randomize(Long::class.javaPrimitiveType, LongRangeRandomizer(1, 100))
            .randomize(Float::class.javaPrimitiveType, FloatRangeRandomizer(1f, 100f))
            .randomize(Double::class.javaPrimitiveType, DoubleRangeRandomizer(1.0, 100.0))
            .randomize(Int::class.javaObjectType, IntegerRangeRandomizer(1, 100))
            .randomize(Long::class.javaObjectType, LongRangeRandomizer(1, 100))
            .randomize(Float::class.javaObjectType, FloatRangeRandomizer(1f, 100f))
            .randomize(Double::class.javaObjectType, DoubleRangeRandomizer(1.0, 100.0))
            .bypassSetters(true)

        random = EasyRandom(param)
        faker = Faker(Locale("ko"))
    }

    fun getInt() = getObject<Int>()

    fun getString() = getObject<String>()

    fun getLong() = getObject<Long>()

    fun getDouble() = getObject<Double>()

    fun getFloat() = getObject<Float>()

    fun getLocalDate() = getObject<LocalDate>()

    fun getLocalTime() = getObject<LocalTime>()

    fun getLocalDateTime() = getObject<LocalDateTime>()

    fun getName() = faker.name().fullName()

    fun getEmail() = faker.internet().emailAddress()

    fun getPhoneNumber() = faker.phoneNumber().cellPhone()

    fun getAddress() = faker.address().fullAddress()

    fun getId() = faker.idNumber().valid()

    fun getZipcode() = faker.address().zipCode()

    inline fun <reified T> getObject(): T {
        if (Collection::class.java.isAssignableFrom(T::class.java)) throw RuntimeException(
            "you can't use collection. use getCollection()"
        )

        if (Map::class.java.isAssignableFrom(T::class.java)) throw RuntimeException(
            "you can't use Map. use getMap()"
        )

        return random.nextObject(T::class.java)
    }

    inline fun <reified T : Collection<S>, reified S> getCollection(): T {
        val size = Random.nextInt(1, 10)

        val array = Array<S>(size) {
            random.nextObject(S::class.java)
        }

        return newInstance(array)
    }

    inline fun <reified T, S> newInstance(array: Array<S>): T {
        return if (MutableSet::class.java.isAssignableFrom(T::class.java)) {
            mutableSetOf(*array) as T
        } else if (Set::class.java.isAssignableFrom(T::class.java)) {
            setOf(*array) as T
        } else if (MutableList::class.java.isAssignableFrom(T::class.java)) {
            mutableListOf(*array) as T
        } else if (List::class.java.isAssignableFrom(T::class.java)) {
            listOf(*array) as T
        } else throw RuntimeException("can't determine ${T::class.java}")
    }

    inline fun <reified T : Map<K, V>, reified K, reified V> getMap(): T {
        val size = Random.nextInt(1, 10)

        val array = Array<Pair<K, V>>(size) {
            random.nextObject(K::class.java) to random.nextObject(V::class.java)
        }

        return newInstance(array)
    }

    inline fun <reified T, K, V> newInstance(array: Array<Pair<K, V>>): T {
        return when {
            ConcurrentMap::class.java.isAssignableFrom(T::class.java) -> {
                ConcurrentHashMap<K, V>().apply {
                    putAll(array)
                } as T
            }
            MutableMap::class.java.isAssignableFrom(T::class.java) -> {
                mutableMapOf(*array) as T
            }
            Map::class.java.isAssignableFrom(T::class.java) -> {
                mapOf(*array) as T
            }
            else -> throw RuntimeException("can't determine ${T::class.java}")
        }
    }
}
