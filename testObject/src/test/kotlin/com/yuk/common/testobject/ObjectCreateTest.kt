package com.yuk.common.testobject

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class ObjectCreateTest {
    @Test
    fun objectCreateTest() {
        val testObject = RandomVariableFactory.getObject<TestObject>()

        val testString = RandomVariableFactory.getString()

        val testInt = RandomVariableFactory.getInt()

        val testLong = RandomVariableFactory.getLong()

        val testFloat = RandomVariableFactory.getFloat()

        val testDouble = RandomVariableFactory.getDouble()

        val testLocalDate = RandomVariableFactory.getLocalDate()

        val testLocalTime = RandomVariableFactory.getLocalTime()

        val testLocalDateTime = RandomVariableFactory.getLocalDateTime()
    }

    @Test
    fun collectionCreateTest() {
        val testCollection =
            RandomVariableFactory.getCollection<Collection<TestObject>, TestObject>()
        val testList =
            RandomVariableFactory.getCollection<List<TestObject>, TestObject>()
        val testSet =
            RandomVariableFactory.getCollection<Set<TestObject>, TestObject>()
        val mutableTestList =
            RandomVariableFactory.getCollection<MutableList<TestObject>, TestObject>()
        val mutableTestSet =
            RandomVariableFactory.getCollection<MutableSet<TestObject>, TestObject>()
    }

    @Test
    fun mapCreateTest() {
        val testMap =
            RandomVariableFactory.getMap<Map<String, TestObject>, String, TestObject>()

        val mutableTestMap =
            RandomVariableFactory.getMap<MutableMap<String, TestObject>, String, TestObject>()

        val testHashMap =
            RandomVariableFactory.getMap<HashMap<String, TestObject>, String, TestObject>()

        val testConcurrentMap =
            RandomVariableFactory.getMap<ConcurrentMap<String, TestObject>, String, TestObject>()

        val testConcurrentHashMap =
            RandomVariableFactory.getMap<ConcurrentHashMap<String, TestObject>, String, TestObject>()
    }

    @Test
    fun mapInterfaceNowAllowedTest() {
        assertThrows<RuntimeException> {
            val testMap =
                RandomVariableFactory.getMap<Map<Iterable<String>, TestObject>, Iterable<String>, TestObject>()
        }
    }
}
