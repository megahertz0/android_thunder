package com.inmobi.rendering;

public class RenderingProperties {
    private PlacementType a;

    public enum PlacementType {
        FULL_SCREEN,
        INLINE
    }

    public RenderingProperties(PlacementType placementType) {
        this.a = placementType;
    }

    public PlacementType a() {
        return this.a;
    }
}
