package yl.demo.pathHelper.db.parser;

import org.json.JSONObject;

import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.model.Corner;



public class CornerParser extends BaseParser {

	/**
	 * @param jsonFilePath
	 */
	public CornerParser(String jsonFilePath) {
		super(jsonFilePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parserAndInsert() {
		try {
			JSONObject object = null;
			Corner corner = null;
			for(int i = 0; i < length; i++) {
				object = wholeArray.getJSONObject(i);
				corner = new Corner();
				corner.setId(object.getInt("_id"));
				corner.setFloorId(object.getInt("floor_id"));
				corner.setX(object.getDouble("x"));
				corner.setY(object.getDouble("y"));
				DBFacade.save(corner);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
