package yl.demo.pathHelper.db.model;

import yl.demo.pathHelper.db.util.Column;
import yl.demo.pathHelper.db.util.Table;
import yl.demo.pathHelper.db.util.Column.DataType;

@Table(name="floor")
public class Floor extends Model{
	@Column(name="building_id",type=DataType.INTEGER)
	private Integer buildingId;

	@Column(name="number",type=DataType.INTEGER)
	private Integer number;

	public Floor(Integer id, Integer buildingId, Integer number) {
		super(id);
		this.buildingId = buildingId;
		this.number = number;
	}

	public Floor() {
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	
}
