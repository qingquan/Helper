package yl.demo.pathHelper.db.model;

import yl.demo.pathHelper.db.util.Column;
import yl.demo.pathHelper.db.util.Table;
import yl.demo.pathHelper.db.util.Column.DataType;

@Table(name = "corner")
public class Corner extends Model {
	@Column(name = "floor_id", type = DataType.INTEGER)
	private Integer floorId;

	@Column(name = "x", type = DataType.REAL)
	private Double x;

	@Column(name = "y", type = DataType.REAL)
	private Double y;


	public Corner() {
	}

	public Corner(Integer id, Integer floorId, Double x, Double y) {
		super(id);
		this.floorId = floorId;
		this.x = x;
		this.y = y;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

}
