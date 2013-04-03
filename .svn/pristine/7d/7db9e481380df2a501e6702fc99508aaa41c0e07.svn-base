package yl.demo.pathHelper.db.model;

import yl.demo.pathHelper.db.util.Column;
import yl.demo.pathHelper.db.util.Table;
import yl.demo.pathHelper.db.util.Column.DataType;

@Table(name = "facility")
public class Facility extends Model {
	@Column(name = "floor_id", type = DataType.INTEGER)
	private Integer floorId;

	@Column(name = "name", type = DataType.TEXT)
	private String name;

	@Column(name = "name_en", type = DataType.TEXT)
	private String nameEn;

	@Column(name = "type", type = DataType.INTEGER)
	private Integer type;

	@Column(name = "logo", type = DataType.TEXT)
	private String logo;

	@Column(name = "x", type = DataType.REAL)
	private Double x;

	@Column(name = "y", type = DataType.REAL)
	private Double y;

	public Facility(Integer id, Integer floorId, String name, String nameEn,
			Integer type, String logo, Double x, Double y) {
		// TODO Auto-generated constructor stub
		super(id);
		this.floorId = floorId;
		this.name = name;
		this.nameEn = nameEn;
		this.type = type;
		this.logo = logo;
		this.x = x;
		this.y = y;
	}

	public Facility() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

}
