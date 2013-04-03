package yl.demo.pathHelper.db.parser;

import org.json.JSONObject;

import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.model.Floor;


public class FloorParser extends BaseParser {

	/**
	 * @param jsonFilePath
	 */
	public FloorParser(String jsonFilePath) {
		super(jsonFilePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parserAndInsert() {
		try {
			JSONObject object = null;
			Floor floor = null;
			for(int i = 0; i < length; i++) {
				object = wholeArray.getJSONObject(i);
				floor = new Floor();
				floor.setId(object.getInt("_id"));
				floor.setBuildingId(object.getInt("building_id"));
				floor.setNumber(object.getInt("number"));
				DBFacade.save(floor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
