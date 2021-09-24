package com.abdul.philips

import junit.framework.TestCase

class CalculatorTest : TestCase(){

    fun test_Add(){
        var calc = Calculator()
        var expected = 40
        var actual = calc.add(10,20)
        assertEquals(expected,actual)
    }
}