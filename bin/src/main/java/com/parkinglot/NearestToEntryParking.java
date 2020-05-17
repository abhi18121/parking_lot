/**
 *
 */
package com.parkinglot;

import java.util.TreeSet;

public class NearestToEntryParking {
    private TreeSet<Integer> slots;

    public TreeSet<Integer> getSlots() {
        TreeSet treeSet = new TreeSet(slots);
        return treeSet;
    }

    public NearestToEntryParking(int slots) {
        this.slots = new TreeSet<>();
        for (int slot = 0; slot < slots; slot++) {
            this.slots.add(slot);
        }
    }

    public int getSlot() {
        if (slots.size() != 0) {
            Integer first = slots.first();
            slots.remove(first);
            return first;
        } else {
            throw new SlotsNotAvailableException();
        }
    }

    public void removeSlot(int slotToRemove) {
        slots.remove(slotToRemove);
        slots.add(slotToRemove);
    }
}
