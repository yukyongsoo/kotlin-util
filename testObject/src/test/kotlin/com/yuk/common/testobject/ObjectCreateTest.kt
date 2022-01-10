package com.yuk.common.testobject

import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class ObjectCreateTest {
    @Test
    fun objectCreateTest() {
        val testObject = RandomVariableFactory.getObject<TestObject>()

        val testString = RandomVariableFactory.getObject<String>()

        val testInt = RandomVariableFactory.getObject<Int>()

        val testLong = RandomVariableFactory.getObject<Long>()

        val testFloat = RandomVariableFactory.getObject<Float>()

        val testDouble = RandomVariableFactory.getObject<Double>()
    }

    @Test
    fun collectionCreateTest() {
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
}
