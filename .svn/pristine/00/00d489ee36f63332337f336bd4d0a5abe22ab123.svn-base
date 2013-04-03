package yl.demo.pathHelper.db.parser;

import org.json.JSONObject;

import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.model.Facility;


public class FacilityParser extends BaseParser {

	/**
	 * @param jsonFilePath
	 */
	public FacilityParser(String jsonFilePath) {
		super(jsonFilePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parserAndInsert() {
		try {
			JSONObject object = null;
			Facility facility = null;
			for(int i = 0; i < length; i++) {
				object = wholeArray.getJSONObject(i);
				facility = new Facility();
				facility.setId(object.getInt("_id"));
				facility.setFloorId(object.getInt("floor_id"));
				facility.setType(object.getInt("type"));
				facility.setLogo(object.getString("logo"));
				facility.setName(object.getString("name"));
				facility.setNameEn(object.getString("name_en"));
				facility.setX(object.getDouble("x"));
				facility.setY(object.getDouble("y"));
				DBFacade.save(facility);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
