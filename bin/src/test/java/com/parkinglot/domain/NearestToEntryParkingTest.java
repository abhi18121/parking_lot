package com.parkinglot.domain;

import com.parkinglot.domain.NearestToEntryParking;
import com.parkinglot.exception.SlotsNotAvailableException;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;

public class NearestToEntryParkingTest {

    @Test(expected = SlotsNotAvailableException.class)
    public void shouldGetSlotsNotAvailableException() {
        //given
        NearestToEntryParking nearestToEntryParking = new NearestToEntryParking(2);
        //when
        nearestToEntryParking.getSlot();
        nearestToEntryParking.getSlot();
        nearestToEntryParking.getSlot();
        //then exception
    }

    @Test
    public void shouldGetSlot() {
        //given
        NearestToEntryParking nearestToEntryParking = new NearestToEntryParking(2);
        //when
        Integer slot = nearestToEntryParking.getSlot();
        //then
        TreeSet<Integer> slots = nearestToEntryParking.getSlots();
        Assert.assertEquals(1, slots.size());
    }

    @Test
    public void shouldRemoveSlot() {
        //given
        NearestToEntryParking nearestToEntryParking = new NearestToEntryParking(2);
        Integer slot = nearestToEntryParking.getSlot();
        //when
        nearestToEntryParking.removeSlot(slot);
        //then
        TreeSet<Integer> slots = nearestToEntryParking.getSlots();
        Assert.assertEquals(2, slots.size());
    }
}