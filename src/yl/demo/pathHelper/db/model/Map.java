package yl.demo.pathHelper.db.model;

import yl.demo.pathHelper.db.util.Column;
import yl.demo.pathHelper.db.util.Table;
import yl.demo.pathHelper.db.util.Column.DataType;

@Table(name="map")
public class Map extends Model {
	@Column(name="floor_id",type=DataType.INTEGER)
	private Integer floorId;

	@Column(name="path_name",type=DataType.TEXT)
	private String pathName;

	@Column(name="block_width",type=DataType.INTEGER)
	private Integer blockWidth;
	
	@Column(name="block_height",type=DataType.INTEGER)
	private Integer blockHeight;
	
	@Column(name="vertical_number",type=DataType.INTEGER)
	private Integer verticalNumber;
	
	@Column(name="horizontal_number",type=DataType.INTEGER)
	private Integer horizontalNumber;
	
	public Map(Integer id, Integer floorId, String pathName, Integer blockWidth, Integer blockHeight, Integer verticalNumber, Integer horizontalNumber) {
		// TODO Auto-generated constructor stub
		super();
		this.floorId = floorId;
		this.pathName = pathName;
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
		this.verticalNumber = verticalNumber;
		this.horizontalNumber = horizontalNumber;
	}
	
	public Map() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getBlockWidth() {
		return blockWidth;
	}

	public void setBlockWidth(Integer blockWidth) {
		this.blockWidth = blockWidth;
	}

	public Integer getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(Integer blockHeight) {
		this.blockHeight = blockHeight;
	}

	public Integer getVerticalNumber() {
		return verticalNumber;
	}

	public void setVerticalNumber(Integer verticalNumber) {
		this.verticalNumber = verticalNumber;
	}

	public Integer getHorizontalNumber() {
		return horizontalNumber;
	}

	public void setHorizontalNumber(Integer horizontalNumber) {
		this.horizontalNumber = horizontalNumber;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
	public String getPathName() {
		return pathName;
	}

}
