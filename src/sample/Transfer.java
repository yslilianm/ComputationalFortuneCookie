package sample;
/**
 * Transfer get the length of the longest word from user's input
 * @return longestWordLen get the length of the longest word
 */
public class Transfer {
    public static int getLongestLen(String[] list) {
        int longestWordLen = 0;
        for (int i = 0; i < (list.length - 1); i++) {
            if (list[i].length() < list[i + 1].length()) {
                longestWordLen = list[i + 1].length();
            } else {
                longestWordLen = list[i].length();
            }
        }
//        for (int i = 2; i < list.length - 2; i++){
//            longestWordLen = list[i-1].length();
//            if (longestWordLen < list[i].length()){
//                longestWordLen = list[i].length();
//            }
//        }
        return longestWordLen;
    }

    /**
     * Transfer use's input into the camera's angle
     * @param num the length of the longest word*
     * @return cameraAngle the camera's angle
     */
    public static int transferForCamera(int num) {
        int unit = 6;
        int cameraAngle = num * unit;
        if (cameraAngle > 90) {
        } else if (cameraAngle < 30) {
            cameraAngle = 30;
        }
        return cameraAngle;
    }

    /**
     * Transfer use's input into the model's color
     * @param num the length of the longest word*
     * @return colorSeed the random seed for rendering model's color
     */
    public static int transferForColor(int num) {
        int unit = 77;
        int colorSeed = num * unit;
        return colorSeed;
    }
}

