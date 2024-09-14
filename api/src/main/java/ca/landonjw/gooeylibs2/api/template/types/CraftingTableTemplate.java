/*
 * GooeyLibs
 * Copyright (C) 201x - 2024 landonjw
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package ca.landonjw.gooeylibs2.api.template.types;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.helpers.TemplateHelper;
import ca.landonjw.gooeylibs2.api.template.Template;
import ca.landonjw.gooeylibs2.api.template.TemplateType;
import ca.landonjw.gooeylibs2.api.template.slot.TemplateSlotDelegate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class CraftingTableTemplate extends Template {

    protected CraftingTableTemplate(@NotNull TemplateSlotDelegate[] slots) {
        super(TemplateType.CRAFTING_TABLE, slots);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CraftingTableTemplate set(int index, @Nullable Button button) {
        getSlot(index).setButton(button);
        return this;
    }

    public CraftingTableTemplate setGrid(int row, int col, @Nullable Button button) {
        getSlot(row * 3 + col + 1).setButton(button);
        return this;
    }

    public CraftingTableTemplate setResultItem(@Nullable Button button) {
        getSlot(0).setButton(button);
        return this;
    }

    public CraftingTableTemplate fill(@Nullable Button button) {
        for (int i = 0; i < getSize(); i++) {
            if (!getSlot(i).getButton().isPresent()) {
                getSlot(i).setButton(button);
            }
        }
        return this;
    }

    public CraftingTableTemplate fillFromList(@NotNull List<Button> buttons) {
        Iterator<Button> iterator = buttons.iterator();
        for (int i = 0; i < getSize(); i++) {
            if (!getSlot(i).getButton().isPresent()) {
                getSlot(i).setButton((iterator.hasNext()) ? iterator.next() : null);
            }
        }
        return this;
    }

    public CraftingTableTemplate fillGrid(@Nullable Button button) {
        for (int i = 1; i < 10; i++) {
            if (!getSlot(i).getButton().isPresent()) {
                getSlot(i).setButton(button);
            }
        }
        return this;
    }

    public CraftingTableTemplate fillGridFromList(@NotNull List<Button> buttons) {
        Iterator<Button> iterator = buttons.iterator();
        for (int i = 1; i < 10; i++) {
            if (!getSlot(i).getButton().isPresent()) {
                getSlot(i).setButton((iterator.hasNext()) ? iterator.next() : null);
            }
        }
        return this;
    }

    public CraftingTableTemplate clear() {
        for (int slotIndex = 0; slotIndex < getSize(); slotIndex++) {
            getSlot(slotIndex).setButton(null);
        }
        return this;
    }

    @Override
    public CraftingTableTemplate clone() {
        TemplateSlotDelegate[] clonedSlots = new TemplateSlotDelegate[getSize()];
        for (int i = 0; i < getSize(); i++) {
            Button button = getSlot(i).getButton().orElse(null);
            clonedSlots[i] = new TemplateSlotDelegate(button, i);
        }
        return new CraftingTableTemplate(clonedSlots);
    }

    public static class Builder {

        /**
         * Instance of the template being built.
         * <p>
         * In 2.1.0, all template builders were moved from storing data instances (ie. button arrays)
         * of templates and constructing them on build, to simply delegating to an instance of the template.
         * <p>
         * This was done in order to shift all convenience methods (ie. row, column, border, etc.) into the
         * corresponding Template classes themselves to allow for easier modification of button state after
         * the Template was built.
         * <p>
         * Since we assign a new template instance at the end of each {@link #build()},
         * this should not have any side effects and thus be backwards compatible.
         * <p>
         * Yay for abstraction!
         */
        private CraftingTableTemplate templateInstance;

        public Builder() {
            this.templateInstance = new CraftingTableTemplate(TemplateHelper.slotsOf(10));
        }

        public Builder set(int index, @Nullable Button button) {
            templateInstance.set(index, button);
            return this;
        }

        public Builder setGrid(int row, int col, @Nullable Button button) {
            templateInstance.setGrid(row, col, button);
            return this;
        }

        public Builder setResultItem(@Nullable Button button) {
            templateInstance.setResultItem(button);
            return this;
        }

        public Builder fill(@Nullable Button button) {
            templateInstance.fill(button);
            return this;
        }

        public Builder fillFromList(@NotNull List<Button> buttons) {
            templateInstance.fillFromList(buttons);
            return this;
        }

        public Builder fillGrid(@Nullable Button button) {
            templateInstance.fillGrid(button);
            return this;
        }

        public Builder fillGridFromList(@NotNull List<Button> buttons) {
            templateInstance.fillGridFromList(buttons);
            return this;
        }

        public CraftingTableTemplate build() {
            CraftingTableTemplate templateToReturn = templateInstance;
            templateInstance = new CraftingTableTemplate(TemplateHelper.slotsOf(10));
            return templateToReturn;
        }

    }

}