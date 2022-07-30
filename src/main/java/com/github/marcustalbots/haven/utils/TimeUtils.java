package com.github.marcustalbots.haven.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Marcus Talbot (1041464)
 */
public final class TimeUtils {

    /**
     * Generates a number between min (inclusive) and max (inclusive), using a Gaussian
     * distribution.<br>
     * <br>
     * The Gaussian distribution uses {@code  (min + max) / 2} as the mean.<br>
     * <br>
     * Rather than using the proper standard deviation, we use the standard deviation divided by
     * three, to ensure that the generated number will naturally fall between the given bound 99.7%
     * of the time. The remaining 0.3%, we manually set the appropriate bound as the returning value.
     *
     * @param min The minimum value this method should return.
     * @param max The maximum value this method should return.
     *
     * @return A value between min and max, generated using a Gaussian distribution.
     */
    public static long getDelayUsingGaussianDistribution(final long min, final long max) {
        var mean = (min + max) / 2.0;
        var sigmaSquare = Math.pow(min - mean, 2.0) + Math.pow(max - mean, 2.0);
        var stdDev = Math.sqrt(sigmaSquare);
        var thirdOfStdDev = stdDev / 3.0;
        var potentialDelay = (long) ThreadLocalRandom.current().nextGaussian(mean, thirdOfStdDev);
        return Math.max(min, Math.min(potentialDelay, max));
    }


}
