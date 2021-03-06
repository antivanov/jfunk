package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.common.jfunk.test.Functions.falseValue;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Enumerables;
import org.common.jfunk.Predicate;
import org.junit.Test;

public class AllTest {

    @Test
    public void ifAllCalledWithEmptyCollectionThenTrue() {
        assertTrue(Enumerables.all(Collections.<Object>emptyList(), falseValue));
    }
    
    @Test
    public void ifAllCalledWithNullArgumentsThenExceptionIsRaised() {
        try {
            Enumerables.all(Collections.emptyList(), null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            Enumerables.all(null, falseValue);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
    }
    
    @Test
    public void ifAllCalledAllElementsSatisfyConditionThenTrue() {
        assertTrue(Enumerables.all(Arrays.asList("one", "two", "three"), new Predicate<String> () {
            public Boolean call(String input) {
                return input.length() > 0;
            }
        }));
    }
    
    @Test
    public void ifAllCalledNotAllCollectionElementsSatisfyConditionThenFalse() {
        assertFalse(Enumerables.all(Arrays.asList("one", "", "three"), new Predicate<String> () {
            public Boolean call(String input) {
                return input.length() > 0;
            }
        }));
    }
}