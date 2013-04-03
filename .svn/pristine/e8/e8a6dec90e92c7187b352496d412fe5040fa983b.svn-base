package yl.demo.pathHelper.util;

import yl.demo.pathHelper.Network.HttpClientHandler;
import yl.demo.pathHelper.Network.SocketClientHandler;
import yl.demo.pathHelper.db.DBFacade;
import yl.demo.pathHelper.db.parser.BuildingParser;
import yl.demo.pathHelper.db.parser.CornerParser;
import yl.demo.pathHelper.db.parser.FacilityParser;
import yl.demo.pathHelper.db.parser.FloorParser;
import yl.demo.pathHelper.db.parser.MapParser;
import yl.demo.pathHelper.db.parser.PathParser;
import yl.demo.pathHelper.xml.parse.XmlPullParserService;

public class VersionUtil {
	
	public static boolean autoRun() {
		if ( needUpdates() ) {
			clearData();
			updateConfigXml();
			requestFileFromConfigXml();
			loadDataToDataBase();
		}
		
		return true;
	}

	private static void loadDataToDataBase() {
		// TODO Auto-generated method stub
		BuildingParser buildingParser = new BuildingParser(FileUtil.getStringFromFile(FileUtil.FILE_DB_BUILDING));
		buildingParser.parserAndInsert();
		CornerParser cornerParser = new CornerParser(FileUtil.getStringFromFile(FileUtil.FILE_DB_CORNER));
		cornerParser.parserAndInsert();
		FacilityParser facilityParser = new FacilityParser(FileUtil.getStringFromFile(FileUtil.FILE_DB_FACILITY));
		facilityParser.parserAndInsert();
		FloorParser floorParser = new FloorParser(FileUtil.getStringFromFile(FileUtil.FILE_DB_FLOOR));
		floorParser.parserAndInsert();
		MapParser mapParser = new MapParser(FileUtil.getStringFromFile(FileUtil.FILE_DB_MAP));
		mapParser.parserAndInsert();
		PathParser pathParser = new PathParser(FileUtil.getStringFromFile(FileUtil.FILE_DB_PATH));
		pathParser.parserAndInsert();
	}

	private static void clearData() {
		// TODO Auto-generated method stub
		DBFacade.dropAllTables();
	}

	private static void requestFileFromConfigXml() {
		// TODO Auto-generated method stub
		HttpClientHandler httpClientHandler = new HttpClientHandler(FileUtil.FILE_DB_BUILDING);
		httpClientHandler.execute();
		httpClientHandler = new HttpClientHandler(FileUtil.FILE_DB_CORNER);
		httpClientHandler.execute();
		httpClientHandler = new HttpClientHandler(FileUtil.FILE_DB_FLOOR);
		httpClientHandler.execute();
		httpClientHandler = new HttpClientHandler(FileUtil.FILE_DB_MAP);
		httpClientHandler.execute();
		httpClientHandler = new HttpClientHandler(FileUtil.FILE_DB_PATH);
		httpClientHandler.execute();
		httpClientHandler = new HttpClientHandler(FileUtil.FILE_DB_FACILITY);
		httpClientHandler.execute();
	}

	private static void updateConfigXml() {
		// TODO Auto-generated method stub
		HttpClientHandler httpClientHandler = new HttpClientHandler(FileUtil.FILE_CONFIG);
		httpClientHandler.execute();
	}

	private static boolean needUpdates() {
		// TODO Auto-generated method stub
		String serverVersion = SocketClientHandler.getInstance().getConfigVersion();
		String clientVersion = null;
		try {
			clientVersion = XmlPullParserService.getConfigVersion(FileUtil.getInstreamFromFile("Sysconfig.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( serverVersion.equals(clientVersion) ) 
			return false;
	
		return true;
	}
}
