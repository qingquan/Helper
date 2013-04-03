package yl.demo.pathHelper.xml.parse;

import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;  
import yl.demo.pathHelper.xml.model.Context;
import yl.demo.pathHelper.xml.model.DBData;
import yl.demo.pathHelper.xml.model.SysConfig;
import android.util.Xml;  

public class XmlPullParserService {
	
	public static SysConfig getConfigInfo(InputStream inputStream) throws Exception {
        SysConfig sysConfig = new SysConfig();
        Context context = null;
        DBData dbData = null;
        
		XmlPullParser parser = Xml.newPullParser();  
        parser.setInput(inputStream, "UTF-8");  
          
        int event = parser.getEventType();//产生第一个事件  
        while(event!=XmlPullParser.END_DOCUMENT){  
            switch(event){  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
            	String tag = parser.getName();
                if ( SysConfig.TAG_VERSION.equals(tag) )
                	sysConfig.setVersion(parser.nextText());
                else if ( Context.TAG_CONTEXT.equals(tag) )
                	context = new Context();
                else if ( Context.TAG_MAP.equals(tag) )
                	context.setMap(Integer.valueOf(parser.nextText()));
                else if ( Context.TAG_REF_DATA.equals(tag) ) 
                	context.setRefData(parser.nextText());
                else if ( Context.TAG_DB_DATA.equals(tag) )
                	dbData = new DBData();
                else if ( DBData.TAG_DB_BUILDING_PATH.equals(tag) )
                	dbData.buildingDBPath = parser.nextText();
                else if ( DBData.TAG_DB_CORNER_PATH.equals(tag) )
                	dbData.cornerDBPath = parser.nextText();
                else if ( DBData.TAG_DB_FACILITY_PATH.equals(tag) )
                	dbData.facilityDBPath = parser.nextText();
                else if ( DBData.TAG_DB_FLOOR_PATH.equals(tag) )
                	dbData.floorDBPath = parser.nextText();
                else if ( DBData.TAG_DB_MAP_PATH.equals(tag) )
                	dbData.mapDBPath = parser.nextText();
                else if ( DBData.TAG_DB_PATH_PATH.equals(tag) )
                	dbData.pathDBPath = parser.nextText();
                	
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件  
            	String tag2 = parser.getName();
            	if ( Context.TAG_DB_DATA.equals(tag2) )
            		context.setDbData(dbData);
            	else if ( Context.TAG_CONTEXT.equals(tag2) )
            		sysConfig.addContext(context);
                break; 
            }  
            event = parser.next();//进入下一个元素并触发相应事件  
        } 
        
        return sysConfig;
	}
	
	public static String getConfigVersion(InputStream inputStream) throws Exception {
		XmlPullParser parser = Xml.newPullParser();  
        parser.setInput(inputStream, "UTF-8");  
        int event = parser.getEventType();//产生第一个事件 
        
        while(event!=XmlPullParser.END_DOCUMENT){  
            switch(event){  
            case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件  
                break;  
            case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件  
                if ( SysConfig.TAG_VERSION.equals(parser.getName()) ) {
                	return parser.nextText();
                }
                break;  
            case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件    
                break; 
            }  
            event = parser.next();//进入下一个元素并触发相应事件  
        }//end while 
        
		return null;
	}

}
