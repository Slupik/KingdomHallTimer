package jw.kingdom.hall.kingdomtimer.javafx.custom;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeFieldUtilsTest {

    @Test
    public void getFormattedText() {
        assertEquals("00:00:00", TimeFieldUtils.getFormattedText("00:00:00", "00:00:00"));
        assertEquals("43:02:43", TimeFieldUtils.getFormattedText("00:00:00", "43:020:43"));
        assertEquals("43:02:43", TimeFieldUtils.getFormattedText("00:00:00", "4$^3:02gbe0000:4gfsd3"));
        assertEquals("43:02:43", TimeFieldUtils.getFormattedText("00:00:00", "43:2000:43"));
    }

    @Test
    public void getFormattedElement() {
        assertEquals("23", TimeFieldUtils.getFormattedElement("23"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("230"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("023"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("0230"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("23a"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("2a3"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("2a30"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("0000ada2a30gfr000"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("234"));
    }

    @Test
    public void removeNonDigitChars() {
        assertEquals("2340000", TimeFieldUtils.removeNonDigitChars("2gef3few4gas0^^#&*^*#@#00/.,//[0"));
    }

    @Test
    public void trimZeros() {
        assertEquals("234", TimeFieldUtils.trimZeros("2340000"));
        assertEquals("234", TimeFieldUtils.trimZeros("0000234"));
        assertEquals("234", TimeFieldUtils.trimZeros("0000234000"));
        assertEquals("2304", TimeFieldUtils.trimZeros("00002304000"));
    }

    @Test
    public void trimZeroOnRight() {
        assertEquals("234", TimeFieldUtils.trimZeroOnRight("2340000"));
        assertEquals("000234", TimeFieldUtils.trimZeroOnRight("0002340000"));
        assertEquals("0002034", TimeFieldUtils.trimZeroOnRight("00020340000"));
    }

    @Test
    public void trimZeroOnLeft() {
        assertEquals("234", TimeFieldUtils.trimZeroOnLeft("0000234"));
        assertEquals("23400", TimeFieldUtils.trimZeroOnLeft("000023400"));
        assertEquals("230400", TimeFieldUtils.trimZeroOnLeft("0000230400"));
    }

    @Test
    public void isTextNormal() {
        assertTrue(TimeFieldUtils.isTextNormal("00:00:00"));
        assertTrue(TimeFieldUtils.isTextNormal("00:01:00"));
        assertTrue(TimeFieldUtils.isTextNormal("43:23:43"));
        assertTrue(TimeFieldUtils.isTextNormal("43:23:4"));
        assertFalse(TimeFieldUtils.isTextNormal("23:24:"));
        assertFalse(TimeFieldUtils.isTextNormal("23:24:423"));
        assertFalse(TimeFieldUtils.isTextNormal("23:234:42"));
        assertFalse(TimeFieldUtils.isTextNormal("233:24:42"));
    }

    @Test
    public void getAllSeconds() {
        int time = 12*60*60+23*60+41;
        String asText = Integer.toString(time/(60*60))+":"+
                        Integer.toString((time%3600)/(60))+":"+
                        Integer.toString(time%(60));
        int returnTime = TimeFieldUtils.getAllSeconds(asText);
        assertEquals(time, returnTime);
    }

    @Test
    public void isNormalInt() {
        assertTrue(TimeFieldUtils.isNormalInt("0"));
        assertTrue(TimeFieldUtils.isNormalInt("01"));
        assertTrue(TimeFieldUtils.isNormalInt("010"));
        assertTrue(TimeFieldUtils.isNormalInt("-10"));
        assertTrue(TimeFieldUtils.isNormalInt("-010"));

        assertFalse(TimeFieldUtils.isNormalInt("0.1"));//double
        assertFalse(TimeFieldUtils.isNormalInt("0,1"));// , instead of .
        assertFalse(TimeFieldUtils.isNormalInt("0a10"));//wrong character
    }
}