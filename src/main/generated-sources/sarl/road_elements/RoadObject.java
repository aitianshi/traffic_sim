package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadObject extends MapCircle {
  private double pos1D;
  
  private final UUID uuid;
  
  private Road road;
  
  @DefaultValueSource
  public RoadObject(@DefaultValue("road_elements.RoadObject#NEW_0") final double pos1D, final Road road) {
    super(0, 0, 10);
    this.pos1D = pos1D;
    this.uuid = UUID.randomUUID();
    this.road = road;
  }
  
  /**
   * Default value for the parameter pos1D
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private static final double $DEFAULT_VALUE$NEW_0 = 0;
  
  @Pure
  public Road getRoad() {
    return this.road;
  }
  
  public void setRoad(final Road road) {
    this.road = road;
  }
  
  @Pure
  public double getPos1D() {
    return this.pos1D;
  }
  
  public void setPos1D(final double pos1D) {
    this.pos1D = pos1D;
  }
  
  @Pure
  public Point2d getCoordinates() {
    double _distanceKilometers = this.road.getDistanceKilometers();
    double t = (this.pos1D / _distanceKilometers);
    int _beginX = this.road.getBeginX();
    int _endX = this.road.getEndX();
    double x = (((1 - t) * _beginX) + (t * _endX));
    int _beginY = this.road.getBeginY();
    int _endY = this.road.getEndY();
    double y = (((1 - t) * _beginY) + (t * _endY));
    return new Point2d(x, y);
  }
  
  @DefaultValueUse("double,road_elements.Road")
  @SyntheticMember
  public RoadObject(final Road road) {
    this($DEFAULT_VALUE$NEW_0, road);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RoadObject other = (RoadObject) obj;
    if (Double.doubleToLongBits(other.pos1D) != Double.doubleToLongBits(this.pos1D))
      return false;
    if (!Objects.equals(this.uuid, other.uuid)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.pos1D) ^ (Double.doubleToLongBits(this.pos1D) >>> 32));
    result = prime * result + Objects.hashCode(this.uuid);
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public RoadObject clone() {
    try {
      return (RoadObject) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -5085379509L;
}
