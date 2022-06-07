package cz.muni.fi.pb162.hw03.impl;

import cz.muni.fi.pb162.hw02.HasLabels;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Matus Jakab
 */
public class LabelSet implements HasLabels {
    private final Set<String> labels;

    @Override
    public Set<String> getLabels() {
        return labels;
    }

    /**
     *
     * @param labels String of labels
     */
    public LabelSet(@NotNull String labels){
        Objects.requireNonNull(labels, "Labels should not be null");
        this.labels = new HashSet<>(Arrays.asList(labels.split(" ")));
    }
}
