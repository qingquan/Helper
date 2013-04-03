package yl.demo.pathHelper.db.parser;

import org.json.JSONObject;

import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.model.Path;


public class PathParser extends BaseParser {

	/**
	 * @param jsonFilePath
	 */
	public PathParser(String jsonFilePath) {
		super(jsonFilePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parserAndInsert() {
		try {
			JSONObject object = null;
			Path path = null;
			for(int i = 0; i < length; i++) {
				object = wholeArray.getJSONObject(i);
				path = new Path();
				path.setId(object.getInt("_id"));
				path.setCornerIdFrom(object.getInt("corner_id_from"));
				path.setCornerIdTo(object.getInt("corner_id_to"));
				path.setLength(object.getDouble("length"));
				DBFacade.save(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
