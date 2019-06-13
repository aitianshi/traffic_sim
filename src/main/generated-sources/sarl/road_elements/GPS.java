package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.arakhne.afc.gis.road.path.astar.RoadAStar;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.Point2D;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class GPS {
  private ArrayList<Road> listRoads = new ArrayList<Road>();
  
  private ArrayList<Point2D> listPoints = new ArrayList<Point2D>();
  
  public GPS(final Point2D current, final Point2D end, final RoadNetwork network) {
    RoadAStar aStar = new RoadAStar();
    RoadPath path = aStar.solve(current, end, network);
    int count = 0;
    for (final RoadSegment roadSegment : path) {
      {
        Road r = ((Road) roadSegment);
        this.listRoads.add(r);
      }
    }
    this.transform(current);
  }
  
  private void transform(final Point2D current) {
    this.listPoints.add(current);
    Point2D lastPointAdded = current;
    for (final Road road : this.listRoads) {
      boolean _equals = road.getBegin().equals(lastPointAdded);
      if (_equals) {
        this.listPoints.add(road.getEnd());
        lastPointAdded = road.getEnd();
      } else {
        boolean _equals_1 = road.getEnd().equals(lastPointAdded);
        if (_equals_1) {
          this.listPoints.add(road.getBegin());
          lastPointAdded = road.getBegin();
        }
      }
    }
  }
  
  @Pure
  public Road getNextRoad() {
    boolean _hasNextRoad = this.hasNextRoad();
    if (_hasNextRoad) {
      Road r = this.listRoads.get(0);
      this.listRoads.remove(0);
      return r;
    }
    return null;
  }
  
  @Pure
  public Point2D getNextPoint() {
    int _size = this.listPoints.size();
    if ((_size == 0)) {
      return null;
    }
    Point2D p = this.listPoints.get(0);
    this.listPoints.remove(p);
    return p;
  }
  
  @Pure
  public boolean hasNextRoad() {
    int _size = this.listRoads.size();
    return (_size != 0);
  }
  
  @Override
  @Pure
  public String toString() {
    return this.listRoads.toString();
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
