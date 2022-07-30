package com.github.marcustalbots.haven.impl.containers;

import com.github.marcustalbots.haven.models.containers.AbstractFreightContainer;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link AbstractFreightContainer}. Used to differentiate between te different types of
 * {@link AbstractFreightContainer}-types. This is useful wherever some {@link AbstractFreightContainer}-objects need to
 * be addressed with a higher priority than others.
 * <br><br>
 * This is also why this class provides an implementation of the
 * {@link Comparable#compareTo(Object)}-method.
 *
 * @author Marcus Talbot (1041464)
 */
public final class DefaultFreightContainer extends AbstractFreightContainer {

    /**
     * Used to compare various implementations of the {@link AbstractFreightContainer}-class. Implemented to allow all
     * subclasses of {@link AbstractFreightContainer} to be inserted into {@link java.util.PriorityQueue}-objects. This
     * is especially convenient where there are various types of {@link AbstractFreightContainer}-objects that need to
     * be handled with different priority.
     *
     * @param o The {@link AbstractFreightContainer}-implementation that needs to be compared to the current object.
     * @return 0 if the argument is an instance of {@link DefaultFreightContainer}, 1 otherwise.
     * @author Marcus Talbot (1041464)
     * @see HeatedFreightContainer#compareTo(AbstractFreightContainer)
     * @see CooledFreightContainer#compareTo(AbstractFreightContainer)
     */
    @Override
    public int compareTo(@NotNull AbstractFreightContainer o) {
        return o instanceof DefaultFreightContainer ? 0 : 1;
    }
}
