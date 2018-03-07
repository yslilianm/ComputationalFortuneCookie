package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.MeshView;
import javafx.scene.PerspectiveCamera;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class Form extends Application {

    //User-defined parameters
    static String name;
    static int cameraAngle = 60;
    static int colorSeed = 1;
    static int pullNum = 0;
    static int pullHeight = 0;

/*   //For testing
   private int cameraAngle;
    private int colorSeed;
    private int pullNum;
    private int pullHeight;*/

/*   //For testing
   public Form (int cameraAngle, int colorSeed, int pullNum, int pullHeight){
        this.cameraAngle = cameraAngle;
        this.colorSeed = colorSeed;
        this.pullNum = pullNum;
        this.pullHeight = pullHeight;
    }*/

    public void init() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //Create mesh
        TriangleMesh formMesh = new TriangleMesh();


        //create an empty texture coordinate group
        formMesh.getTexCoords().addAll(0, 0);

        //predefined parameters
        int radius = 150;
        int angleUnit = 1;
        int theta;
        int sceneWidth = 720;
        int sceneHeight = 600;
        final int CAMERAXPOSITION = 50;
        final int CAMERAYPOSITION = 100;
        final int CAMERAZPOSITION = 100;


        //Rounder for float
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        //Create the fortune cookie lies on the x-z plane
        formMesh.getPoints().addAll(0, 0, 0);
        for (theta = 0; theta <= 360; theta += angleUnit) {
            /*System.out.println(theta);
            System.out.println("*"+df.format(Math.cos(Math.toRadians(theta))));
            System.out.println("*"+df.format(Math.sin(Math.toRadians(theta))));*/


            //Create variable for (x,y)
            //Math.toRadians(theta) means theta in the radians
            //Math.cos(Math.toRadians(theta)) for generating right cos with theta
            //df.format()for round float to 2 decimal
            //Float.valueOf() for converting string to float
            float xPosition = Float.valueOf(df.format(radius * Math.cos(Math.toRadians(theta))));
            float zPosition = (-1) * Float.valueOf(df.format(radius * Math.sin(Math.toRadians(theta))));
            float yPosition = Float.valueOf(df.format(pullHeight * Math.cos(Math.toRadians(theta) * pullNum)));


            formMesh.getPoints().addAll(xPosition, yPosition, zPosition);
        }

/*   //For testing
        System.out.println(formMesh.getPoints());
*/


        for (int fPoint = 1; fPoint <= 360 / angleUnit; fPoint++) {
            int sPoint = fPoint + 1;
            formMesh.getFaces().addAll(0, 0, fPoint, 0, sPoint, 0);
        }

/*   //For testing
        System.out.println(formMesh.getFaces());
        System.out.println(formMesh.getFaces().size());
        System.out.println(formMesh.getFaceElementSize());
*/


        MeshView form = new MeshView(formMesh);
        form.setDrawMode(DrawMode.LINE);
        //Do not perform any face culling
        form.setCullFace(CullFace.NONE);


        //Create a random number for r, g, b for material
        Random generator = new Random(colorSeed);


        //Create material
        PhongMaterial Material = new PhongMaterial();
        Material.setDiffuseColor(Color.rgb(generator.nextInt(256), generator.nextInt(256), generator.nextInt(256)));
        form.setMaterial(Material);


        //Setup the position of form
        form.setTranslateX(sceneWidth / 2);
        form.setTranslateY(sceneHeight / 2);
        form.setTranslateZ(0);


        Group group = new Group();
        group.getChildren().add(form);


        //Setup scene as the canvas
        Scene scene = new Scene(group, sceneWidth, sceneHeight);
        //scene.setFill(Color.GREY);


        //Setup a perspective camera
        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(CAMERAXPOSITION);
        camera.setTranslateY(CAMERAYPOSITION);
        camera.setTranslateZ(CAMERAZPOSITION);
        //value in setFieldView method from 1(flat) to 90(perspective),
        //good range is from 30 to 75 with the fix position of the camera
        camera.setFieldOfView(cameraAngle);
        scene.setCamera(camera);

        //Setup a light
        PointLight pLWhite = new PointLight(Color.WHITE);
        pLWhite.setTranslateX(100);
        pLWhite.setTranslateY(60);
        pLWhite.setTranslateZ(-100);
        AmbientLight aLWhite = new AmbientLight(Color.WHITE);
        aLWhite.setTranslateX(100);
        aLWhite.setTranslateY(100);
        aLWhite.setTranslateZ(-100);


        //Setup primaryStage as a window/space
        primaryStage.setTitle("Fortune Cookie");
        primaryStage.setScene(scene);
        primaryStage.show();


        //Snapshot a png image
        WritableImage writableImage = new WritableImage((int) scene.getWidth() * 2, (int) scene.getHeight() * 2);
        scene.snapshot(writableImage);

        File file = new File(name + ".png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("snapshot saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Problem writing the file. Please make sure the path is correct.");
        }

    }


    public void setCameraAngle(int cameraAngle) {
        this.cameraAngle = cameraAngle;
    }

    public void setColorSeed(int colorSeed) {
        this.colorSeed = colorSeed;
    }

    public void setPullNum(int pullNum) {
        this.pullNum = pullNum;
    }

    public void setPullHeight(int setPullHeight) {
        this.pullHeight = pullHeight;
    }

    public int getCameraAngle(int cameraAngle) {
        return cameraAngle;
    }

    public int getColorSeed(int colorSeed) {
        return colorSeed;
    }

    public int getPullNum(int pullNum) {
        return pullNum;
    }

    public int getPullHeight(int setPullHeight) {
        return pullHeight = pullHeight;
    }


  /*  public static void main(String[] args) {
        System.out.println("Fortune Cookie");
    }*/


}
