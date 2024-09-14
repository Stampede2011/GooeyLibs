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

public class BrewingStandTemplate extends Template {

    protected BrewingStandTemplate(@NotNull TemplateSlotDelegate[] slots) {
        super(TemplateType.BREWING_STAND, slots);
    }

    public TemplateSlotDelegate getFuel() {
        return getSlot(0);
    }

    public TemplateSlotDelegate getBottle(int index) {
        if (index < 0 || index > 3) return null;
        return getSlot(index + 1);
    }

    public TemplateSlotDelegate getIngredient() {
        return getSlot(4);
    }

    public BrewingStandTemplate fuel(@Nullable Button button) {
        getSlot(4).setButton(button);
        return this;
    }

    public BrewingStandTemplate ingredient(@Nullable Button button) {
        getSlot(3).setButton(button);
        return this;
    }

    public BrewingStandTemplate bottle(int index, @Nullable Button button) {
        if (index >= 0 && index < 3) getSlot(index).setButton(button);
        return this;
    }

    public BrewingStandTemplate bottles(@Nullable Button button) {
        for (int i = 0; i < 3; i++) bottle(i, button);
        return this;
    }

    public BrewingStandTemplate bottlesFromList(@NotNull List<Button> buttons) {
        Iterator<Button> iterator = buttons.iterator();
        for (int i = 0; i < 3; i++) bottle(i, (iterator.hasNext()) ? iterator.next() : null);
        return this;
    }

    public BrewingStandTemplate clear() {
        for (int slotIndex = 0; slotIndex < getSize(); slotIndex++) {
            getSlot(slotIndex).setButton(null);
        }
        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public BrewingStandTemplate clone() {
        TemplateSlotDelegate[] clonedSlots = new TemplateSlotDelegate[getSize()];
        for (int i = 0; i < getSize(); i++) {
            Button button = getSlot(i).getButton().orElse(null);
            clonedSlots[i] = new TemplateSlotDelegate(button, i);
        }
        return new BrewingStandTemplate(clonedSlots);
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
        private BrewingStandTemplate templateInstance;

        public Builder() {
            this.templateInstance = new BrewingStandTemplate(TemplateHelper.slotsOf(5));
        }

        public Builder fuel(@Nullable Button button) {
            templateInstance.fuel(button);
            return this;
        }

        public Builder ingredient(@Nullable Button button) {
            templateInstance.ingredient(button);
            return this;
        }

        public Builder bottle(int index, @Nullable Button button) {
            templateInstance.bottle(index, button);
            return this;
        }

        public Builder bottles(@Nullable Button button) {
            templateInstance.bottles(button);
            return this;
        }

        public Builder bottlesFromList(@NotNull List<Button> buttons) {
            templateInstance.bottlesFromList(buttons);
            return this;
        }

        public BrewingStandTemplate build() {
            BrewingStandTemplate templateToReturn = templateInstance;
            templateInstance = new BrewingStandTemplate(TemplateHelper.slotsOf(5));
            return templateToReturn;
        }

    }

}
