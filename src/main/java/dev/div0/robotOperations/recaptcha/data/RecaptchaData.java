package dev.div0.robotOperations.recaptcha.data;

import org.openqa.selenium.Point;

public class RecaptchaData {
	private static Point coordinates = new Point(0, 0);
	private static String instructions;
	private static String imagePath;
	private static String imagesFolder;
	private static String imageName = "recaptchaImage";
	private static int[] clicks;
	private static int imagesTableRowsTotal;
	private static int imagesTableColumnsTotal;

	public static void init(){
		coordinates = new Point(0, 0);
	}
	
	public static Point getCoordinates() {
		return coordinates;
	}

	public static void setCoordinates(Point coordinates) {
		RecaptchaData.coordinates = coordinates;
	}
	
	public static void moveBy(int xOffset, int yOffset){
		coordinates.moveBy(xOffset, yOffset);
	}

	public static String getImagePath() {
		return imagePath;
	}

	public static void setImagePath(String imagePath) {
		RecaptchaData.imagePath = imagePath;
	}

	public static String getInstructions() {
		return instructions;
	}

	public static void setInstructions(String instructions) {
		RecaptchaData.instructions = instructions;
	}

	public static String getImagesFolder() {
		return imagesFolder;
	}

	public static void setImagesFolder(String imagesFolder) {
		RecaptchaData.imagesFolder = imagesFolder;
	}

	public static String getImageName() {
		return imageName;
	}

	public static void setImageName(String imageName) {
		RecaptchaData.imageName = imageName;
	}

	public static int[] getClicks() {
		return clicks;
	}

	public static void setClicks(int[] clicks) {
		RecaptchaData.clicks = clicks;
	}

	public static int getImagesTableRowsTotal() {
		return imagesTableRowsTotal;
	}

	public static void setImagesTableRowsTotal(int imagesTableRowsTotal) {
		RecaptchaData.imagesTableRowsTotal = imagesTableRowsTotal;
	}

	public static int getImagesTableColumnsTotal() {
		return imagesTableColumnsTotal;
	}

	public static void setImagesTableColumnsTotal(int imagesTableColumnsTotal) {
		RecaptchaData.imagesTableColumnsTotal = imagesTableColumnsTotal;
	}
}
