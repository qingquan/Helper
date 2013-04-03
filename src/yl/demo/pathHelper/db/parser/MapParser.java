package yl.demo.pathHelper.db.parser;

import org.json.JSONObject;

import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.model.Map;



public class MapParser extends BaseParser {

	/**
	 * @param jsonFilePath
	 */
	public MapParser(String jsonFilePath) {
		super(jsonFilePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parserAndInsert() {
		try {
			JSONObject object = null;
			Map map = null;
			for(int i = 0; i < length; i++) {
				object = wholeArray.getJSONObject(i);
				map = new Map();
				map.setId(object.getInt("_id"));
				map.setFloorId(object.getInt("floor_id"));
				map.setPathName(object.getString("path_name"));
				map.setBlockHeight(object.getInt("block_width"));
				map.setBlockWidth(object.getInt("block_height"));
				map.setVerticalNumber(object.getInt("vertical_number"));
				map.setHorizontalNumber(object.getInt("horizontal_number"));
				
				
				DBFacade.save(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
