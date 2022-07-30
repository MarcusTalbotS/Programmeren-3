package com.github.marcustalbots.haven.models.containers;

import com.github.marcustalbots.haven.interfaces.Identifiable;

import java.util.UUID;

/**
 * Interface for all Containers used in this program. Every subclass is identifiable by a unique identifier
 * ({@link UUID}). While a UUID is not as readable as a simple {@link String} name, this approach makes more sense, in
 * that a {@link UUID} is (practically) guaranteed to be unique.
 *
 * @author Marcus Talbot (1041464)
 */
public abstract class AbstractContainer implements Identifiable<UUID> {

    /**
     * Unique identifier, with a separate, immutable value per container.
     */
    private final UUID identifier;

    /**
     * Simple constructor, used to create a new container, with a randomly created {@link UUID} for its identifier.
     *
     * @author Marcus Talbot (1041464)
     */
    public AbstractContainer() {
        this.identifier = UUID.randomUUID();
    }

    /**
     * Method used to comply with the constraints set in {@link Identifiable}. Returns the Identifier of this object.
     *
     * @return The UUID of this object.
     * @author Marcus Talbot (1041464)
     */
    public UUID getIdentifier() {
        return this.identifier;
    }

    /**
     * Provides an easy way to get a {@link String}-representation of Containers.
     *
     * @return A {@link String}-representation of this object.
     * @author Marcus Talbot (1041464)
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + this.identifier;
    }

}
