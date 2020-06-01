package org.spm.coworking.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SomeUtils {
    public static boolean isAnyEmpty(Set<Long>... ids){
        return Arrays.stream(ids).anyMatch(Set::isEmpty);
    }
}
