/* Copyright (c) 2001 - 2012 TOPP - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.opengeo.gsr.core.geometry;

/**
 * 
 * @author Juan Marin - OpenGeo
 * 
 */
public class Multipoint extends Geometry {

    private double[] points;

    private SpatialReference spatialReference;

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    public void setSpatialReference(SpatialReference spatialReference) {
        this.spatialReference = spatialReference;
    }

    public Multipoint(double[] coords, SpatialReference spatialReference) {
        this.points = coords;
        this.spatialReference = spatialReference;
        this.geometryType = GeometryType.MULTIPOINT;
    }

}