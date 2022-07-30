package com.github.marcustalbots.haven.interfaces;

/**
 * Interface that denotes that an Object is identifiable through a field of type T. Every implementation of
 * {@link Identifiable} must contain a field of type T, which holds an identifier. Furthermore, every implementation
 * must implement the {@link Identifiable#getIdentifier()}-method.
 *
 * @param <T> Type of the identifier.
 * @author Marcus Talbot (1041464)
 */
@FunctionalInterface
public interface Identifiable<T> {

    /**
     * Gets the identifier of the object.
     *
     * @return Identifier of the given type.
     * @author Marcus Talbot (1041464)
     */
    T getIdentifier();

}
