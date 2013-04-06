package yl.demo.pathHelper.algrorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lbs.wifiparticlefilter.data.Measure;
import lbs.wifiparticlefilter.data.Particle;
import lbs.wifiparticlefilter.data.Point;
import lbs.wifiparticlefilter.dbmanagement.dbHandler;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import yl.demo.pathHelper.model.Location;
import yl.demo.pathHelpler.filter.Filter;
import yl.demo.pathHelper.data.Point;
import yl.demo.pathHelper.data.Particle;
import yl.demo.pathHelper.data.Measure;

public class IndoorLocationAlgorithm {
	private HashMap<Location, HashMap<String, Double>> mFingerPrintingHashMap;
	private HashMap<String, Set<String>> mMacHashMap;
	private String mPath;
	private String mCurrentFileName = "";
	private Filter mFilter;
	public final static int PARTICLES = 30;
	
	public IndoorLocationAlgorithm(String dataPath) {
		// TODO Auto-generated constructor stub
		mPath = dataPath;
		mFingerPrintingHashMap = new HashMap<Location, HashMap<String,Double>>();
		createMacHashMap();
	}
	
	/**
	 * ����mac�͵�ͼ��ӳ��?����ͨ��macȷ�������ĸ���ͼ
	 */
	private void createMacHashMap() {
		// TODO Auto-generated method stub
		mMacHashMap = new HashMap<String, Set<String>>();
		File directory = new File(mPath);
		if (directory.isDirectory()) {
			String[] fileNames = directory.list();
			for (String string : fileNames) {
				File file = new File(mPath + string);
				if ( file.exists() ) {
					addMacToMacHashMap(file);
				}
			}
		}
	}

	/**
	 * ���ļ��е�mac��Ϣ���뵽MacHashMap��
	 * @param file
	 */
	private void addMacToMacHashMap(File file) {
		// TODO Auto-generated method stub
		String fileName = file.getName();
		String line = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ( ( line = bufferedReader.readLine()) != null ) {
				String[] sections = line.split(" ");
				for (String string : sections) {
					if (string.indexOf(":") != -1) {
						String mac = string.substring(0, string.indexOf(":"));
						if ( mMacHashMap.containsKey(mac) && mMacHashMap.get(mac).contains(fileName) )
							continue;
						if ( !mMacHashMap.containsKey(mac) )
							mMacHashMap.put(mac, new HashSet<String>() );
						if ( !mMacHashMap.get(mac).contains(file) ) 
							mMacHashMap.get(mac).add(fileName);
					}		
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param fileName: file storing the fingerprint data
	 * @return: the whole particle map of fingerprinting data
	 */
	//construct the who map of <location, <RSSI value>>
	//for calls of getting <RSSI value> by <location>
	public List<Particle> constructFilterList(String fileName)
	{
		mFingerPrintingHashMap.clear();
		
		List<Particle> list = new ArrayList<Particle>();
		
		File file = new File(mPath + fileName);//get the ref_data name
		String line = null;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			line = bufferedReader.readLine();
			
			while ( ( line = bufferedReader.readLine() ) != null ) {
				String[] sections = line.split(" ");
				int middle = sections[0].indexOf(",");
				
				float x = Float.parseFloat(sections[0].substring(0,middle));
				float y = Float.parseFloat(sections[0].substring(middle+1));
				
				Point point = new Point(x, y);
				ArrayList<Measure> measureList = new ArrayList<Measure>();

				for ( int i = 1; i < sections.length; i++ ) {
					int mark = sections[i].indexOf(":");
					String mac = sections[i].substring(0, mark);
					double rss = Double.parseDouble(sections[i].substring(mark+1));
					
					rss = Math.pow(10, rss/10);
					measureList.add(new Measure(mac, rss));
				}
				//the measurelist may dupliate
			    list.add(new Particle(point, measureList));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	/**
	 * ���ض���ͼ��Fingerprinting��ݼ��ص��ڴ�
	 * @param fileName
	 */
	public void loadFingerPrintingData(String fileName) {
		mFingerPrintingHashMap.clear();
		File file = new File(mPath+fileName);
		int floorId, buildingId;
		String line = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			line = bufferedReader.readLine();
			String[] fragments = line.split(" ");
			buildingId = Integer.parseInt(fragments[0]);
			floorId = Integer.parseInt(fragments[1]);
			while ( ( line = bufferedReader.readLine() ) != null ) {
				String[] sections = line.split(" ");
				int middle = sections[0].indexOf(",");
				float x = Float.parseFloat(sections[0].substring(0,middle));
				float y = Float.parseFloat(sections[0].substring(middle+1));
				Location location = new Location(buildingId, floorId, x, y);
				HashMap<String, Double> hashMap = new HashMap<String, Double>();
				
				for ( int i = 1; i < sections.length; i++ ) {
					int mark = sections[i].indexOf(":");
					String mac = sections[i].substring(0, mark);
					double rss = Double.parseDouble(sections[i].substring(mark+1));
					rss = Math.pow(10, rss/10);
					hashMap.put(mac, rss);
				}
				mFingerPrintingHashMap.put(location, hashMap);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ͨ��rssVec���ж�λ������¥����Ϣ����ϸλ�õ�Location
	 * @param rssVec ���е��ź�ǿ��Ϊpower�����dbm
	 * @return
	 */
	public Location predictLocation(HashMap<String, Double> rssVec) {		
		String fileName = predictFingerPrintingFile(rssVec);
		if ( !fileName.equals(mCurrentFileName) ) {
			mCurrentFileName = fileName;
			loadFingerPrintingData(mCurrentFileName);
		}
		List<EstimatedLocation> locations = new ArrayList<EstimatedLocation>();
		
		Iterator iterator = mFingerPrintingHashMap.entrySet().iterator();
		
		while ( iterator.hasNext() ) {
			Map.Entry<Location, HashMap<String, Double>> entry = (Map.Entry<Location, HashMap<String, Double>>)iterator.next();
			double estimate = cosineEstimate(entry.getValue(), rssVec);
			locations.add(new EstimatedLocation(estimate, entry.getKey()));
		}
		
		Collections.sort(locations);
		
		//add particle filters into this algorithm
		//get initial location
		Location finalLocation = finalSelection(locations.subList(locations.size()-10, locations.size()));
		
		//init filter
		filter = new Filter(PARTICLES, dbHandler.getData());
		filter.initFilter();
		//the mFingerPrinting Hash map equals the data from db
		if(!initialParticle){
			mFilter = new Filter(PARTICLES, mFingerPrintingHashMap);
		}
		return ;
	}
	
	private Location finalSelection(List<EstimatedLocation> subList) {
		// TODO Auto-generated method stub
		double mean = calculateMeanValue(subList);
		double std = calculateStdValue(subList, mean);
		double threshold = mean + 0.55 * std;
		List<EstimatedLocation> locations = calculateRequireList(subList, threshold);
		float solu = 0;
		float x = 0,y = 0;
		int floorId = subList.get(0).location.floorId;
		int buildingId = subList.get(0).location.buildingId;
		for (EstimatedLocation estimatedLocation : locations) {
			solu += estimatedLocation.estimate;
		}
		
		if ( solu == 0 ) {
			return new Location(buildingId, floorId, 0, 0);
		}
		
		for (EstimatedLocation estimatedLocation : locations) {
			x += estimatedLocation.estimate / solu * estimatedLocation.location.x;
			y += estimatedLocation.estimate / solu * estimatedLocation.location.y;
		}
		return new Location(buildingId, floorId, x, y);
	}

	private List<EstimatedLocation> calculateRequireList(List<EstimatedLocation> subList, double threshold) {
		// TODO Auto-generated method stub
		List<EstimatedLocation> list = new ArrayList<EstimatedLocation>();
		for (EstimatedLocation estimatedLocation : subList) {
			if ( estimatedLocation.estimate >= threshold )
				list.add(estimatedLocation);
		}
		return list;
	}

	private double calculateStdValue(List<EstimatedLocation> subList, double meanValue) {
		// TODO Auto-generated method stub
		double solu = 0;
		for (EstimatedLocation estimatedLocation : subList) {
			solu = Math.pow(estimatedLocation.estimate-meanValue, 2);
		}
		
		return Math.sqrt(solu / subList.size());
	}

	private double calculateMeanValue(List<EstimatedLocation> subList) {
		// TODO Auto-generated method stub
		double solu = 0;
		for (EstimatedLocation estimatedLocation : subList) {
			solu += estimatedLocation.estimate;
		}
		return solu / subList.size();
	}

	/**
	 * �����ж��������ŵ�ͼ��Ȼ�󷵻ص�ͼ������Fingerprinting�ļ���
	 * @param rssVec
	 * @return
	 */
	private String predictFingerPrintingFile(HashMap<String, Double> rssVec) {
		HashMap<String, Integer> mapCountHashMap = new HashMap<String, Integer>();
		Iterator iterator = rssVec.entrySet().iterator();
		while ( iterator.hasNext() ) {
			Map.Entry<String, Double> entry = (Map.Entry<String, Double>)iterator.next();
			String mac = entry.getKey();
			Set<String> set = mMacHashMap.get(mac);
			if ( set == null )
				continue;
			Iterator setIterator = set.iterator();
			for ( ;setIterator.hasNext(); ) {
				String fileName = (String)setIterator.next();
				if ( !mapCountHashMap.containsKey(fileName) )
					mapCountHashMap.put(fileName, 1);
				else 
					mapCountHashMap.put(fileName, mapCountHashMap.get(fileName)+1);
			}
		}
		
		iterator = mapCountHashMap.entrySet().iterator();
		String fileName = null;
		int max = -1;
		while ( iterator.hasNext() ) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)iterator.next();
			int count = entry.getValue();
			if ( count > max ) {
				max = count;
				fileName = (String)entry.getKey();
			}
		}
		return fileName;
		//return "hex_pm_ref.txt";
	}
	
	private double cosineEstimate(HashMap<String, Double> sampleHashMap, HashMap<String, Double> testHashMap) {
		double estimate = 0;
		double sampleNorm = 0;
		double testNorm = 0;
		Set<String> set = new HashSet<String>();
		set.addAll(sampleHashMap.keySet());
		set.addAll(testHashMap.keySet());
		double[] samples = new double[set.size()];
		double[] test = new double[set.size()];
		int i = 0;
		for (String string : set) {
			samples[i] = sampleHashMap.containsKey(string) ? sampleHashMap.get(string) : 0;
			test[i] = testHashMap.containsKey(string) ? testHashMap.get(string) : 0;
			i++;
		}
		
		for (i = 0; i < test.length; i++) {
			estimate += samples[i] * test[i];
			sampleNorm += samples[i] * samples[i];
			testNorm += test[i] * test[i];
		}
		
		return estimate / Math.sqrt(sampleNorm) / Math.sqrt(testNorm);
	}
	
	public class EstimatedLocation implements Comparable<EstimatedLocation> {
		public double estimate;
		public Location location;
		public EstimatedLocation(double estimate, Location location) {
			// TODO Auto-generated constructor stub
			this.estimate = estimate;
			this.location = location;
		}
		@Override
		public int compareTo(EstimatedLocation another) {
			// TODO Auto-generated method stub
			EstimatedLocation estimatedLocation = (EstimatedLocation)another;
			return estimate - estimatedLocation.estimate > 0 ? 1 : -1;
		}
	
	};

}
