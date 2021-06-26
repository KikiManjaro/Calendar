package com.kikimanjaro.calendar.activity.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ActivityStatusTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenPendingToString_shouldReturnRightString() {
        Assertions.assertEquals("En définition", ActivityStatus.PENDING.toString());
    }

    @Test
    void whenTestToString_shouldReturnRightString() {
        Assertions.assertEquals("En test", ActivityStatus.TEST.toString());
    }

    @Test
    void whenInProgressToString_shouldReturnRightString() {
        Assertions.assertEquals("En cours", ActivityStatus.IN_PROGRESS.toString());
    }

    @Test
    void whenDoneToString_shouldReturnRightString() {
        Assertions.assertEquals("Terminé", ActivityStatus.DONE.toString());
    }

    @Test
    void whenNotDefinedToString_shouldReturnRightString() {
        Assertions.assertEquals("Non défini", ActivityStatus.NOT_DEFINED.toString());
    }

    @Test
    void whenEnDefinitionFromString_shouldReturnPending() {
        Assertions.assertEquals(ActivityStatus.PENDING, ActivityStatus.fromString("En définition"));
    }

    @Test
    void whenEnTestFromString_shouldReturnTest() {
        Assertions.assertEquals(ActivityStatus.TEST, ActivityStatus.fromString("En test"));
    }

    @Test
    void whenEnCoursFromString_shouldReturnInProgress() {
        Assertions.assertEquals(ActivityStatus.IN_PROGRESS, ActivityStatus.fromString("En cours"));
    }

    @Test
    void whenTermineFromString_shouldReturnDone() {
        Assertions.assertEquals(ActivityStatus.DONE, ActivityStatus.fromString("Terminé"));
    }

    @Test
    void whenNonDefiniFromString_shouldReturnNotDefined() {
        Assertions.assertEquals(ActivityStatus.NOT_DEFINED, ActivityStatus.fromString("Non défini"));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0}")
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "\r", "toto", "\"&é²(-è_çà)/.?§!:;,", "123"})
    void whenFromStringWithNonDefinedValue_shouldReturnNotDefined(String fromString) {
        Assertions.assertEquals(ActivityStatus.NOT_DEFINED, ActivityStatus.fromString(fromString));
    }
}