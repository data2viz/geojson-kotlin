package org.geojson

import org.junit.Assert
import org.junit.Test

class LngLatAltTest {

    @Test
    fun should_LngLatAlt_equals_without_alt() {
        val first = LngLatAlt(14.0, 13.0)
        val second = LngLatAlt(14.0, 13.0)
        Assert.assertEquals(second, first)
    }

    @Test
    fun should_LngLatAlt_equals_with_alt() {
        val first = LngLatAlt(14.0, 13.0, 15.0)
        val second = LngLatAlt(14.0, 13.0, 15.0)
        Assert.assertEquals(second, first)
    }

    @Test
    fun should_not_LngLatAlt_equals_with_alt() {
        val first = LngLatAlt(14.0, 13.0, 15.0)
        val second = LngLatAlt(14.0, 13.0, 16.0)
        Assert.assertNotEquals(second, first)
    }

    @Test
    fun should_not_LngLatAlt_equals_without_alt() {
        val first = LngLatAlt(14.0, 14.0, 15.0)
        val second = LngLatAlt(14.0, 13.0, 16.0)
        Assert.assertNotEquals(second, first)
    }

    @Test
    fun should_LngLatAlt_equals_with_additional_elements() {
        val first = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        val second = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        Assert.assertEquals(second, first)
        Assert.assertEquals(second.hashCode().toLong(), first.hashCode().toLong())
    }

    @Test
    fun should_LngLatAlt_equals_with_additional_elements_and_null() {
        val first = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        val second = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        Assert.assertEquals(second, first)
        Assert.assertEquals(second.hashCode().toLong(), first.hashCode().toLong())
    }

    @Test
    fun should_not_LngLatAlt_equals_without_additional_elements() {
        val first = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        val second = LngLatAlt(14.0, 14.0, 15.0)
        Assert.assertNotEquals(second, first)
        Assert.assertNotEquals(second.hashCode().toLong(), first.hashCode().toLong())
    }

    @Test
    fun should_not_LngLatAlt_equals_with_additional_elements_in_different_order() {
        val first = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        val second = LngLatAlt(14.0, 14.0, 15.0, 17.0, 16.0)
        Assert.assertNotEquals(second, first)
        Assert.assertNotEquals(second.hashCode().toLong(), first.hashCode().toLong())
    }

    @Test
    fun should_not_LngLatAlt_equals_with_additional_elements_and_different_size() {
        val first = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)
        val second = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0, 18.0)
        Assert.assertNotEquals(second, first)
        Assert.assertNotEquals(second.hashCode().toLong(), first.hashCode().toLong())
    }

    @Test
    fun should_LngLatAlt_throw_if_alt_not_specified_in_constructor() {
        try {
            LngLatAlt(14.0, 14.0, java.lang.Double.NaN, 16.0, 17.0)
            Assert.fail("Additional elements are not allowed if altitude is Nan.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_alt_set_to_Nan_with_additional_elements() {
        val lngLatAlt = LngLatAlt(14.0, 14.0, 15.0, 16.0, 17.0)

        try {
            lngLatAlt.setAltitude(java.lang.Double.NaN)
            Assert.fail("Additional elements are not allowed if altitude is Nan.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_additional_elements_set_with_missing_alt() {
        val lngLatAlt = LngLatAlt(14.0, 14.0)

        try {
            lngLatAlt.setAdditionalElements(42.0)
            Assert.fail("Additional elements are not allowed if altitude is Nan.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_additional_elements_set_with_Nan_alt() {
        val lngLatAlt = LngLatAlt(14.0, 14.0, java.lang.Double.NaN)

        try {
            lngLatAlt.setAdditionalElements(42.0)
            Assert.fail("Additional elements are not allowed if altitude is Nan.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_any_additional_elements_constructed_to_Nan() {
        try {
            LngLatAlt(14.0, 14.0, 15.0, 16.0, java.lang.Double.NaN, 17.0)
            Assert.fail("Additional elements are not allowed to be Nan.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_any_additional_elements_constructed_to_Positive_Infinity() {
        try {
            LngLatAlt(14.0, 14.0, 15.0, 16.0, java.lang.Double.POSITIVE_INFINITY, 17.0)
            Assert.fail("Additional elements are not allowed to be positive infinity.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_any_additional_elements_constructed_to_Negative_Infinity() {
        try {
            LngLatAlt(14.0, 14.0, 15.0, 16.0, java.lang.Double.NEGATIVE_INFINITY, 17.0)
            Assert.fail("Additional elements are not allowed to be positive infinity.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_any_additional_elements_set_to_Nan() {
        val lngLatAlt = LngLatAlt(14.0, 14.0, 15.0)
        try {
            lngLatAlt.setAdditionalElements(16.0, java.lang.Double.NaN, 17.0)
            Assert.fail("Additional elements are not allowed to be Nan.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_any_additional_elements_set_to_Positive_Infinity() {
        val lngLatAlt = LngLatAlt(14.0, 14.0, 15.0)
        try {
            lngLatAlt.setAdditionalElements(16.0, java.lang.Double.POSITIVE_INFINITY, 17.0)
            Assert.fail("Additional elements are not allowed to be positive infinity.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }

    @Test
    fun should_LngLatAlt_throw_if_any_additional_elements_set_to_Negative_Infinity() {
        val lngLatAlt = LngLatAlt(14.0, 14.0, 15.0)
        try {
            lngLatAlt.setAdditionalElements(16.0, java.lang.Double.NEGATIVE_INFINITY, 17.0)
            Assert.fail("Additional elements are not allowed to be positive infinity.")
        } catch (e: IllegalArgumentException) {
            Assert.assertTrue("Expected exception.", true)
        }

    }
}