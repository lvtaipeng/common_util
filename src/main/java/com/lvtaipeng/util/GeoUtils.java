package com.lvtaipeng.util;

public class GeoUtils {

	/**
	 * 
	 * ��������γ�ȵ�֮��ľ��루��λ���ף�
	 * @param lng1 ����
	 * @param lat1 γ��
	 * @param lng2
	 * @param lat2
	 * @return
	 */

	public static double getDistance(double lng1, double lat1, double lng2, double lat2) {

		/** toRadians() �������ڽ��Ƕ�ת��Ϊ���� **/

		double radLat1 = Math.toRadians(lat1);

		double radLat2 = Math.toRadians(lat2);

		double a = radLat1 - radLat2;

		double b = Math.toRadians(lng1) - Math.toRadians(lng2);

		/** asin() ��������һ����ֵ�ķ�����(��λΪ����) **/
		/** pow() �������ػ���(base)��ָ��(exponent)���� **/
		/** sin(x) ����x������ֵ **/
		/** cos(x) ����x������ֵ **/

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)

				* Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));

		s = s * 6378137.0;// ȡWGS84��׼�ο������еĵ��򳤰뾶(��λ:m)

		s = Math.round(s * 10000) / 10000;

		return s;

	}

	/**
	 * 
	 * ����TPֵ
	 * @param curPoint
	 *            ��ǰ��
	 * @param relatedPoint
	 *            ƫ�Ƶ�
	 * @param isGeography
	 *            �Ƿ��ǵ������� falseΪ2d����
	 * @return tpֵ
	 * 
	 */

	public static double getDirAngle(Point curPoint, Point relatedPoint, boolean isGeography) {

		double result = 0;

		if (isGeography) {

			double y2 = Math.toRadians(relatedPoint.getLat());

			double y1 = Math.toRadians(curPoint.getLat());

			double alpha = Math.atan2(relatedPoint.getLat() - curPoint.getLat(),
					(relatedPoint.getLng() - curPoint.getLng()) * Math.cos((y2 - y1) / 2));// γ�ȷ������cos(y2-y1/2)

			double delta = alpha < 0 ? (2 * Math.PI + alpha) : alpha;

			result = Math.toDegrees(delta);

		} else {

			double alpha = Math.atan2(relatedPoint.getLat() - curPoint.getLat(),
					relatedPoint.getLng() - curPoint.getLng());

			double delta = alpha < 0 ? (2 * Math.PI + alpha) : alpha;

			result = Math.toDegrees(delta);

		}
		return result;
	}

	public class Point {

		private double lat;

		private double lng;

		public double getLat() {

			return lat;

		}
		public void setLat(double lat) {

			this.lat = lat;

		}
		public double getLng() {

			return lng;

		}
		public void setLng(double lng) {

			this.lng = lng;

		}
	}
	 public static void main(String[] args) {

	        System.out.println(getDistance(121.446014,31.215937,121.446028464238,31.2158502442799));

	    }
}
