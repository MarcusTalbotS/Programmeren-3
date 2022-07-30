package com.github.marcustalbots.haven.models.vehicles;

import com.github.marcustalbots.haven.dock.Dock;
import com.github.marcustalbots.haven.interfaces.Identifiable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Provides an interface for various types of vehicles with common fields. Every vehicle contains a {@link String}
 * identifier, and a {@link Dock}.
 *
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractVehicle implements Identifiable<String> {

    /**
     * (Preferably unique) Identifier of this object.
     */
    private String identifier;

    /**
     * Dock this object should associate with.
     */
    private Dock dock;

    /**
     * Simple constructor used to construct a new object with the given configuration.
     *
     * @param dock       {@link Dock} this object should associate with.
     * @param identifier {@link String} identifier of this object.
     * @author Marcus Talbot (1041464)
     */
    public AbstractVehicle(final Dock dock, final String identifier) {
        this.identifier = identifier;
        this.dock = dock;
    }

    /**
     * Implemented to comply with the constraints in {@link Identifiable}. Returns a reference to the
     * {@link #identifier}-field.
     *
     * @return A reference to the {@link #identifier}-field.
     * @author Marcus Talbot (1041464)
     */
    @Contract(pure = true)
    @Override
    public @NotNull String getIdentifier() {
        return this.identifier;
    }

    /**
     * Sets the value of the {@link #identifier}-field to the specified value.
     *
     * @param identifier The new value of the {@link #identifier}-field.
     * @author Marcus Talbot (1041464)
     */
    public void setIdentifier(@NotNull final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets a reference to the {@link Dock} this object currently associates with.
     *
     * @return A reference to {@link #dock}.
     * @author Marcus Talbot (1041464)
     */
    @Contract(pure = true)
    public @NotNull Dock getDock() {
        return this.dock;
    }

    /**
     * Sets a new value to the {@link #dock}-field.
     *
     * @param dock The new value of the {@link #dock}-field.
     * @author Marcus Talbot (1041464)
     */
    public void setDock(@NotNull final Dock dock) {
        this.dock = dock;
    }

    /**
     * Provides a convenient, human-readable {@link String}-representation of this object.
     *
     * @return A {@link String} representation of this object.
     * @author Marcus Talbot (1041464)
     */
    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + "-" + this.identifier;
    }
}
