package yl.demo.pathHelper.db.parser;

import org.json.JSONException;
import org.json.JSONObject;

import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.model.Building;

public class BuildingParser extends BaseParser {

	/**
	 * @param jsonFilePath
	 */
	public BuildingParser(String jsonFilePath) {
		super(jsonFilePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parserAndInsert() {
		try {
			JSONObject object = null;
			Building building = null; 
			for(int i = 0; i < length; i++) {
				object = wholeArray.getJSONObject(i);
				building = new Building();
				building.setId(object.getInt("_id"));
				building.setLatitude(object.getDouble("latitude"));
				building.setLongtitude(object.getDouble("longitude"));
				building.setName(object.getString("name"));
				DBFacade.save(building);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
